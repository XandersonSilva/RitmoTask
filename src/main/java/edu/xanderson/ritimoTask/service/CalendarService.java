package edu.xanderson.ritimoTask.service;


import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.calendar.Calendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class CalendarService {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
    @Autowired
    private UserRepository userRepository;
   
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;
    
    // Bloco estático para inicializar o HTTP_TRANSPORT
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            // Este é um erro crítico. Se o transporte HTTP não puder ser criado,
            // a aplicação não pode se comunicar com o Google.
            e.printStackTrace();
            System.exit(1);
        }
    }
    

    public Calendar getCalendarServiceForUser(long userId) {
        
    


        UserEntity user = userRepository.getReferenceById(userId);
        String googleId = user.getGoogleId();
        if (googleId == null) {
            throw new RuntimeException("Este usuário não está associado a uma conta Google.");
        }

        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
            "google", // O clientRegistrationId
            googleId    // O principalName (ID do Google)
        );

        if (authorizedClient == null) {
            throw new RuntimeException("Cliente autorizado não encontrado. Usuário talvez precise se logar novamente.");
        }

        
        String accessToken = authorizedClient.getAccessToken().getTokenValue();

        // Cria a credencial do Google a partir do token de acesso gerenciado pelo Spring
        Credential credential = new GoogleCredential().setAccessToken(accessToken);

        // Constrói o serviço do Calendar, pronto para ser usado!
        return new Calendar.Builder(
                HTTP_TRANSPORT, // Sua constante HttpTransport
                JSON_FACTORY,   // Sua constante JsonFactory
                credential)
                .setApplicationName("RitmoTask")
                .build();
    }

    
    public List<Event> listarEventos(long userId) throws IOException {
        Calendar service = getCalendarServiceForUser(userId);

        
        Events events = service.events().list("primary").execute();
        
        List<Event> items = events.getItems();
        
        return items;
    }


}

