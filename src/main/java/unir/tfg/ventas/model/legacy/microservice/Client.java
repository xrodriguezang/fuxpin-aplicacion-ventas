package unir.tfg.ventas.model.legacy.microservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * JPA - Entity - Clients
 *
 * @author Xavier Rodríguez
 */
@Getter @Setter @AllArgsConstructor
public class Client {

    private long id;

    private String name;
    private String description;
    private String direction;
    private String country;
    private String telephone;

}
