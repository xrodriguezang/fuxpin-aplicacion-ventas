package unir.tfg.ventas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * JPA - Entity - Country
 *
 * @author Xavier Rodríguez
 */
@Entity
@Table(name = "COUNTRY")
@Getter @Setter
public class Country {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    private String name;

    private String description;
}

