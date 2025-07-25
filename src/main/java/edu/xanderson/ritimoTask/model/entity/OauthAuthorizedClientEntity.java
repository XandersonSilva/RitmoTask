package edu.xanderson.ritimoTask.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


@Entity
@Table(name = "oauth2_authorized_client")
@IdClass(OauthAuthorizedClientEntity.AuthorizedClientId.class)
public class OauthAuthorizedClientEntity {

    @Id
    @Column(nullable = false, length = 100)
    private String clientRegistrationId;

    @Id
    @Column(nullable = false, length = 200)
    private String principalName;

    @Column(nullable = false, length = 100)
    private String accessTokenType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String accessTokenValue;

    @Column(nullable = false)
    private Instant accessTokenIssuedAt;

    @Column(nullable = false)
    private Instant accessTokenExpiresAt;

    @Column(columnDefinition = "TEXT")
    private String accessTokenScopes;

    @Column(columnDefinition = "TEXT")
    private String refreshTokenValue;

    @Column
    private Instant refreshTokenIssuedAt;

    public String getClientRegistrationId() {
        return clientRegistrationId;
    }

    public void setClientRegistrationId(String clientRegistrationId) {
        this.clientRegistrationId = clientRegistrationId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getAccessTokenType() {
        return accessTokenType;
    }

    public void setAccessTokenType(String accessTokenType) {
        this.accessTokenType = accessTokenType;
    }

    public String getAccessTokenValue() {
        return accessTokenValue;
    }

    public void setAccessTokenValue(String accessTokenValue) {
        this.accessTokenValue = accessTokenValue;
    }

    public Instant getAccessTokenIssuedAt() {
        return accessTokenIssuedAt;
    }

    public void setAccessTokenIssuedAt(Instant accessTokenIssuedAt) {
        this.accessTokenIssuedAt = accessTokenIssuedAt;
    }

    public Instant getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public String getAccessTokenScopes() {
        return accessTokenScopes;
    }

    public void setAccessTokenScopes(String accessTokenScopes) {
        this.accessTokenScopes = accessTokenScopes;
    }

    public String getRefreshTokenValue() {
        return refreshTokenValue;
    }

    public void setRefreshTokenValue(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }

    public Instant getRefreshTokenIssuedAt() {
        return refreshTokenIssuedAt;
    }

    public void setRefreshTokenIssuedAt(Instant refreshTokenIssuedAt) {
        this.refreshTokenIssuedAt = refreshTokenIssuedAt;
    }

    
    public static class AuthorizedClientId implements Serializable {
        private String clientRegistrationId;
        private String principalName;
        
        public AuthorizedClientId() {
        }

        public AuthorizedClientId(String clientRegistrationId, String principalName) {
            this.clientRegistrationId = clientRegistrationId;
            this.principalName = principalName;
        }

        
        public String getClientRegistrationId() {
            return clientRegistrationId;
        }

        public void setClientRegistrationId(String clientRegistrationId) {
            this.clientRegistrationId = clientRegistrationId;
        }

        public String getPrincipalName() {
            return principalName;
        }

        public void setPrincipalName(String principalName) {
            this.principalName = principalName;
        }

        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthorizedClientId that = (AuthorizedClientId) o;
            return Objects.equals(clientRegistrationId, that.clientRegistrationId) && 
                   Objects.equals(principalName, that.principalName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clientRegistrationId, principalName);
        }
    }
}