package unir.tfg.ventas.services;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import unir.tfg.ventas.authentication.ApplicationAuthenticationProvider;
import unir.tfg.ventas.contract.LegacyRolesServiceClient;

/**
 * The same object that provides the microservice
 *
 * Object that contains the role of the user.
 *
 * It's roles is added to fuxpin-applicacionventas
 *
 * @author Xavier Rodríguez
 *
 * EnableFeignClients -> Injects the implementation Rest of the microservice
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter
{
    @Autowired
    LegacyRolesServiceClient legacyRolesServiceClient;

    protected ApplicationAuthenticationProvider applicationKeycloakAuthenticationProvider() {
        return new ApplicationAuthenticationProvider();
    }

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        ApplicationAuthenticationProvider keycloakAuthenticationProvider = applicationKeycloakAuthenticationProvider();

        // The service is passed to the provider
        keycloakAuthenticationProvider.setLegacyRolesServiceClient(legacyRolesServiceClient);
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    /**
     * Rules to protect the application
     *
     * @param http
     *
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/error*").authenticated()
                .antMatchers("/user-profile*", "/manager-sales*").hasRole("USER")
                .antMatchers("/clients-admin*").hasRole("ADMIN") // This role is provided by the legacy microservice (legacy role)
                .and()
                .exceptionHandling().accessDeniedPage("/page-denied");
    }
}