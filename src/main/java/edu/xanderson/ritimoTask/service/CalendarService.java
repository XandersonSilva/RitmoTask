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
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.repository.UserRepository;

import java.io.IOException;
import java.security.GeneralSecurityException;


@Service
public class CalendarService {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoogleCredentialService googleCredentialService;
   
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;
    
    // Bloco para inicializar o HTTP_TRANSPORT
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
    

    public Calendar getCalendarServiceForUser(long userId) throws GeneralSecurityException, IOException {
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

        Credential credential;

        // Cria a credencial do Google a partir do token
        // TODO: Criar uma exceção para quando não há um refresh token ou access token valido
        String refreshToken = authorizedClient.getRefreshToken().getTokenValue();
        if (refreshToken != null) {
            credential = googleCredentialService.getCredentials(refreshToken);
        }else{
            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            credential = new GoogleCredential().setAccessToken(accessToken);
        }
        
        
        // Constrói o serviço do Calendar
        return new Calendar.Builder(
            HTTP_TRANSPORT, 
            JSON_FACTORY,   
            credential)
            .setApplicationName("RitmoTask")
                .build();
    }

    public void createEvent(long userId) throws IOException, GeneralSecurityException{
        Calendar service = getCalendarServiceForUser(userId);
        Event event = new Event()
                        .setSummary("Teste")
                        .setLocation(null)
                        .setDescription("Teste");
                        
        //TODO: Adicoinar campo na tabela user_entity para guardar o timezone
        DateTime startDateTime = new DateTime("2025-07-28T18:07:00");
        EventDateTime start = new EventDateTime()
        .setDateTime(startDateTime);
        event.setStart(start);

        DateTime endDateTime = new DateTime("2025-07-28T18:07:00");
        EventDateTime end = new EventDateTime()
        .setDateTime(endDateTime);
    event.setEnd(end);

    //TODO: Adicionar campo na tabela user_entity para guardar o calendario preferido pelo user
    String calendarId = "primary";
    event = service.events().insert(calendarId, event).execute();
    }
}

