package unir.tfg.ventas.authentication;

import org.keycloak.adapters.springsecurity.account.KeycloakRole;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Extends authentication on a {@link KeycloakAuthenticationToken}.
 *
 * @author <a href="mailto:amgrill@gmail.com">Xavier Rodríguez</a>
 * @version $Revision: 1 $
 */
public class ApplicationAuthenticationProvider extends KeycloakAuthenticationProvider {

    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

    public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.addAll(addUserSpecificAuthoritiesFromLegacy(grantedAuthorities));


        // token.setDetails("ddd");

        for (String role : token.getAccount().getRoles()) {
            grantedAuthorities.add(new KeycloakRole(role));
        }

        return new KeycloakAuthenticationToken(token.getAccount(), token.isInteractive(), mapAuthorities(grantedAuthorities));
    }

    protected Collection<? extends GrantedAuthority> addUserSpecificAuthoritiesFromLegacy(List<GrantedAuthority> authorities ) {

        // potentially add user specific authentication, lookup from internal database
        // etc...

        List<GrantedAuthority> result = new ArrayList<>();

        result.add(new KeycloakRole("pepito"));

        return result;
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        return grantedAuthoritiesMapper != null
                ? grantedAuthoritiesMapper.mapAuthorities(authorities)
                : authorities;
    }




}
