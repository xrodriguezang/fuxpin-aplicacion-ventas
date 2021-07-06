package unir.tfg.ventas;

import lombok.extern.log4j.Log4j2;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import unir.tfg.ventas.contract.LegacyClientsServiceClient;
import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.model.Client;
import unir.tfg.ventas.model.Product;
import unir.tfg.ventas.services.IAlmacenService;
import unir.tfg.ventas.services.IClientService;
import unir.tfg.ventas.services.IProductService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Actions - application. Implements pattern MVC.
 *
 * @author Xavier Rodríguez
 *
 */
@Controller
@EnableFeignClients   // It's necessary to use it in the SecurityConfig too. It makes singleton instance.
@Log4j2
public class VentasController {

    @Autowired
    LegacyClientsServiceClient legacyClientsServiceClient;

    @Autowired
    private IAlmacenService almacenService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IClientService clientService;

    // Home page application
    @GetMapping("/")
    public String homePage (Model model, Principal principal) {
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        log.info("Usuario accede a la página del perfil: {}", accessToken.getPreferredUsername());


        model.addAttribute("username", accessToken.getGivenName());
        model.addAttribute("nameSurnames", accessToken.getName());
        model.addAttribute("email", accessToken.getEmail());
        model.addAttribute("user", accessToken.getPreferredUsername());

        Map<String, Object> attributes = accessToken.getOtherClaims();

        // Return all the customized-attributes of Keycloak
        model.addAttribute("personalAttributes", attributes);

        return "user-profile";
    }

    /**
     * Action to show the manager sales
     * Get the user properties from AccessToken -> Keycloak
     * products, almaxcenes from database -> from
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/manager-sales")
    public String manageSales(Model model, Principal principal) {

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        model.addAttribute("username", accessToken.getGivenName());
        model.addAttribute("nameSurnames", accessToken.getName());
        model.addAttribute("email", accessToken.getEmail());
        model.addAttribute("user", accessToken.getPreferredUsername());

        Map<String, Object> attributes = accessToken.getOtherClaims();
        //user.setCustomAttributes(otherClaims);

        List<Almacen> almacenes = almacenService.findAll();
        List<Product> products = productService.getAllProducts();

        // Data from database
        model.addAttribute("almacenes", almacenes);
        model.addAttribute("products", products);

        model.addAttribute("personalAttributes", attributes);

        log.info("Almacenes total: {}, productos totales: {}", almacenes.size(), products.size());

        return "manager-sales";
    }

    /**
     * Show the user Properties
     *
     * All roles have acces to this action
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/user-profile")
    public String userProfile(Model model, Principal principal) {

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        log.info("Usuario accede a la página del perfil: {}", accessToken.getPreferredUsername());


        model.addAttribute("username", accessToken.getGivenName());
        model.addAttribute("nameSurnames", accessToken.getName());
        model.addAttribute("email", accessToken.getEmail());
        model.addAttribute("user", accessToken.getPreferredUsername());

        Map<String, Object> attributes = accessToken.getOtherClaims();

        // Return all the customized-attributes of Keycloak
        model.addAttribute("personalAttributes", attributes);

        return "user-profile";
    }

    /**
     * Clients - admin action
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/clients-admin")
    public String adminClients (Model model, Principal principal) {

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        model.addAttribute("username", accessToken.getGivenName());
        model.addAttribute("nameSurnames", accessToken.getName());

        List<Client> clients = clientService.findAll();

        // Data from database
        model.addAttribute("clientes", clients);

        return "clients-admin";
    }

    /**
     * Legacy Client action
     *
     * Get the clients - legacy thanks to a microservice
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/clients-legacy-admin")
    public String adminClients2 (Model model, Principal principal) {

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        model.addAttribute("username", accessToken.getGivenName());
        model.addAttribute("nameSurnames", accessToken.getName());

        ResponseEntity<List<unir.tfg.ventas.model.legacy.microservice.Client>> responseLegacyClients = legacyClientsServiceClient.getClients();

        if (responseLegacyClients.getStatusCode() == HttpStatus.OK) {
            log.debug("Legacy roles from user: {} response: {}", accessToken.getPreferredUsername(), responseLegacyClients.getBody());
        }

        // Data from database
        model.addAttribute("clientes", responseLegacyClients.getBody());

        return "clients-legacy-admin";
    }

    /**
     * Error 403 - Page
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/page-denied")
    public String deniedAccess (Model model, Principal principal) {

        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken accessToken = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        log.info("Usuario {} sin permisos para acceder a la página" , accessToken.getPreferredUsername());

        model.addAttribute("username", accessToken.getGivenName());
        model.addAttribute("nameSurnames", accessToken.getName());

        List<Client> clients = clientService.findAll();

        // Data from database
        model.addAttribute("clientes", clients);

        return "page-denied";
    }

}
