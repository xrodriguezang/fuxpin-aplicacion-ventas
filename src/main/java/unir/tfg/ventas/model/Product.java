package unir.tfg.ventas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * JPA - Entity - Product
 *
 * @author Xavier Rodríguez
 */
@Entity
@Table (name = "PRODUCT")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue (strategy =  GenerationType.AUTO)
    private long id;

    private String title;
    private String description;

    private String colour;

}
