package unir.tfg.ventas;

import lombok.extern.log4j.Log4j2;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.model.Client;
import unir.tfg.ventas.model.Product;
import unir.tfg.ventas.services.IAlmacenService;
import unir.tfg.ventas.services.IClientService;
import unir.tfg.ventas.services.IProductService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@EnableFeignClients   // It's necessary to use it in the SecurityConfig. It makes singleton instance.
@Log4j2
public class VentasController {

    @Autowired
    private IAlmacenService almacenService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IClientService clientService;

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
