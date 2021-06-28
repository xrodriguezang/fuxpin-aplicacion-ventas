package unir.tfg.ventas.contract;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import unir.tfg.ventas.model.legacy.microservice.Alive;
import unir.tfg.ventas.model.legacy.microservice.Client;

import java.util.List;

/**
 * Contract method with the Microservice Layer
 *
 * @author Xavier Rodríguez
 */
@FeignClient(name="fuxpin-client-microservice")
public interface LegacyClientsServiceClient {

    @GetMapping("/imAlive")
    ResponseEntity<Alive> imAlive();

    @GetMapping("/getClients")
    ResponseEntity<List<Client>> getClients();

}