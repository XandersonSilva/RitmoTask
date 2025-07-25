package edu.xanderson.ritimoTask.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import edu.xanderson.ritimoTask.model.entity.OauthAuthorizedClientEntity;
import edu.xanderson.ritimoTask.model.repository.OauthAuthorizedClientRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final OauthAuthorizedClientRepository authorizedClientRepository;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public AuthorizedClientService(OauthAuthorizedClientRepository authorizedClientRepository,
                                                  ClientRegistrationRepository clientRegistrationRepository) {
        this.authorizedClientRepository = authorizedClientRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    @Transactional
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (clientRegistration == null) {
            return null;
        }

        OauthAuthorizedClientEntity.AuthorizedClientId id = new OauthAuthorizedClientEntity.AuthorizedClientId();
        id.setClientRegistrationId(clientRegistrationId);
        id.setPrincipalName(principalName);

        Optional<OauthAuthorizedClientEntity> entityOptional = authorizedClientRepository.findById(id);
        if (entityOptional.isEmpty()) {
            return null;
        }

        OauthAuthorizedClientEntity entity = entityOptional.get();

        // Recria os objetos de token
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                entity.getAccessTokenValue(),
                entity.getAccessTokenIssuedAt(),
                entity.getAccessTokenExpiresAt(),
                parseScopes(entity.getAccessTokenScopes()));
        
        OAuth2RefreshToken refreshToken = null;
        if (entity.getRefreshTokenValue() != null) {
            refreshToken = new OAuth2RefreshToken(
                    entity.getRefreshTokenValue(),
                    entity.getRefreshTokenIssuedAt());
        }

        return (T) new OAuth2AuthorizedClient(clientRegistration, principalName, accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        OauthAuthorizedClientEntity.AuthorizedClientId id = new OauthAuthorizedClientEntity.AuthorizedClientId();
        id.setClientRegistrationId(authorizedClient.getClientRegistration().getRegistrationId());
        id.setPrincipalName(principal.getName());
        
        OauthAuthorizedClientEntity entity = authorizedClientRepository.findById(id).orElse(new OauthAuthorizedClientEntity());

        entity.setClientRegistrationId(authorizedClient.getClientRegistration().getRegistrationId());
        entity.setPrincipalName(principal.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        entity.setAccessTokenType(accessToken.getTokenType().getValue());
        entity.setAccessTokenValue(accessToken.getTokenValue());
        entity.setAccessTokenIssuedAt(accessToken.getIssuedAt());
        entity.setAccessTokenExpiresAt(accessToken.getExpiresAt());
        entity.setAccessTokenScopes(StringUtils.collectionToDelimitedString(accessToken.getScopes(), ","));

        if (authorizedClient.getRefreshToken() != null) {
            entity.setRefreshTokenValue(authorizedClient.getRefreshToken().getTokenValue());
            entity.setRefreshTokenIssuedAt(authorizedClient.getRefreshToken().getIssuedAt());
        }

        authorizedClientRepository.save(entity);
    }

    @Override
    @Transactional
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        OauthAuthorizedClientEntity.AuthorizedClientId id = new OauthAuthorizedClientEntity.AuthorizedClientId();
        id.setClientRegistrationId(clientRegistrationId);
        id.setPrincipalName(principalName);
        authorizedClientRepository.deleteById(id);
    }
    
    private Set<String> parseScopes(String scopes) {
        if (scopes == null) {
            return new HashSet<>();
        }
        return StringUtils.commaDelimitedListToSet(scopes);
    }
}
