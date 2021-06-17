package unir.tfg.ventas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * JPA - Entity - Clients
 *
 * @author Xavier Rodríguez
 */
@Entity
@Table(name = "CLIENT")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private String direction;
    private String country;
    private String telephone;

}
