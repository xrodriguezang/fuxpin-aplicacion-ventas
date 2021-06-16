package unir.tfg.ventas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private float prize;
    private int quantity;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    private List<Almacen> almacenes = new ArrayList<>();

}
