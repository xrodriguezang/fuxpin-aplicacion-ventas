package unir.tfg.ventas.model.microservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The same object that provides the microservice
 *
 * Object that contains the status of the microservice
 *
 * @author Xavier Rodríguez
 */
@Getter @Setter @AllArgsConstructor
public class Alive {

    String appAlive;
    String databaseAlive;
}
