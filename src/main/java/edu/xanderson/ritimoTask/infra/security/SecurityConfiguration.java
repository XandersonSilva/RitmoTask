package edu.xanderson.ritimoTask.infra.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import edu.xanderson.ritimoTask.model.entity.UserEntity;
import edu.xanderson.ritimoTask.model.entity.UserSituation;
import edu.xanderson.ritimoTask.model.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    HttpMethod.GET,
                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui/index.html",
                    "/swagger-ui/index.html/**"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/").permitAll()
                .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/public/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/auth/register/verification/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/authorization/google").permitAll() // Permite acesso à rota Google
                .requestMatchers(HttpMethod.GET, "/auth/login/google").permitAll() // Permite a rota /auth/login/google
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                //.loginPage("/login/oauth2/code/google")
                .defaultSuccessUrl("/auth/oauth2/success", true)
                .successHandler((request, response, authentication) -> {
                    OAuth2AuthenticationToken oauthToken = 
                        (OAuth2AuthenticationToken) authentication;
                    OAuth2User oauth2User = oauthToken.getPrincipal();

                    // Gerando o token com o email que veio do google 
                    
                    String email = oauth2User.getAttribute("email"); // Obtém o email do Google

                    if (email == null) {
                        // Tratar caso onde o email não é fornecido pelo OAuth2 provider
                        response.sendRedirect("/auth/login?error=email_not_found");
                        return;
                    }
                    System.out.println(email);

                    // Procurar ou criar o usuário no seu banco de dados
                    Optional<UserEntity> userOptional = Optional.ofNullable((UserEntity) userRepository.findByEmail(email));
                    UserEntity user;

                    if (userOptional.isPresent()) {
                        user = userOptional.get();
                        // Atualizar dados do usuário se necessário (e.g., nome, foto de perfil)
                    } else {
                        // Criar novo usuário se não existir
                        user = new UserEntity();
                        user.setEmail(email);
                        user.setUsername(email);
                        user.setName(oauth2User.getAttribute("name")); 
                        user.setSituation(UserSituation.ACTIVE);
                        // Salvar o novo usuário no banco de dados
                        userRepository.save(user); 
                    }

                    // Gerar o JWT usando o TokenService
                    String token = tokenService.generateToken(user);

                    System.out.println("Token JWT Gerado: " + token);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"token\": \"" + token + "\"}");
                    response.setStatus(HttpServletResponse.SC_OK);
                })
                .failureUrl("/auth/login?error=oauth2_failure")
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) 
            .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
