package unir.tfg.ventas.authentication;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.account.KeycloakRole;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import unir.tfg.ventas.contract.LegacyRolesServiceClient;
import unir.tfg.ventas.model.legacy.microservice.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Extends authentication on a {@link KeycloakAuthenticationProvider}.
 *
 * This authentications provider extends the keycloak provider to add a microservice call that injects he roles of the legacy
 * application.
 *
 * @author Xavier Rodríguez
 */
@Slf4j
public class ApplicationAuthenticationProvider extends KeycloakAuthenticationProvider {

    // This Service is
    private LegacyRolesServiceClient legacyRolesServiceClient;

    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

    public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
    }


    /**
     * Method that add the microservice call. It's provided by Fuxpin Eureka server.
     *
     *
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.addAll(addUserSpecificAuthoritiesFromLegacy(token));

        for (String role : token.getAccount().getRoles()) {
            grantedAuthorities.add(new KeycloakRole(role.toUpperCase()));
        }

        return new KeycloakAuthenticationToken(token.getAccount(), token.isInteractive(), mapAuthorities(grantedAuthorities));
    }

    /**
     * Method that add the legacy roles
     *
     * @param token with the principal
     *
     * @return all the user roles
     */
    protected Collection<? extends GrantedAuthority> addUserSpecificAuthoritiesFromLegacy(KeycloakAuthenticationToken token ) {

        List<GrantedAuthority> result = new ArrayList<>();

        try {
            AccessToken accessToken = token.getAccount().getKeycloakSecurityContext().getToken();

            ResponseEntity<List<Role>> responseLegacyRole = legacyRolesServiceClient.getRoles(accessToken.getPreferredUsername());

            if (responseLegacyRole.getStatusCode() == HttpStatus.OK) {
                log.debug("Legacy roles from user: {} response: {}", accessToken.getPreferredUsername(), responseLegacyRole.getBody());
            }

            // Although it recieves the role with pattern: ROLE_XXXXXXX. When it is saved to the Granted Authority, all the roles are provided with this prefix.
            // If it is recieved without this prefix, then Granted Authority adds it.
            for (Role role : responseLegacyRole.getBody()) {
                log.debug("Role injected to user: {}, KeycloackRole: {}", accessToken.getPreferredUsername(), role.getRoleId());
                result.add(new KeycloakRole(role.getRoleId()));
            }

        } catch (Exception e) {
            log.error("Problems with the legacy microservice!", e);
        }

        return result;
    }

    /**
     * Method that provides the GrantedAuthority to the SecurityContext.
     *
     * It's filled with user roles.
     *
     * @param authorities
     *
     * @return user roles
     */
    private Collection<? extends GrantedAuthority> mapAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        return grantedAuthoritiesMapper != null
                ? grantedAuthoritiesMapper.mapAuthorities(authorities)
                : authorities;
    }

    public void setLegacyRolesServiceClient(LegacyRolesServiceClient legacyRolesServiceClient) {
        this.legacyRolesServiceClient = legacyRolesServiceClient;
    }
}
