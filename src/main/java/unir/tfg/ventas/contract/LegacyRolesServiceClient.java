package unir.tfg.ventas.contract;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import unir.tfg.ventas.model.microservice.Alive;
import unir.tfg.ventas.model.microservice.Role;

import java.util.List;

/**
 * Contract method with the Microservice Layer
 *
 * @author Xavier Rodríguez
 */
@FeignClient(name="fuxpin-role-microservice")
public interface LegacyRolesServiceClient {

    @GetMapping("/imAlive")
    ResponseEntity<Alive> imAlive();

    @GetMapping("/getRoles/{id}")
    ResponseEntity<List<Role>> getRoles(@PathVariable String id);

}