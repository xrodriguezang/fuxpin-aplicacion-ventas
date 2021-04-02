package unir.tfg.ventas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "ALMACEN")
@Getter @Setter
public class Almacen {

    @Id
    @GeneratedValue (strategy =  GenerationType.AUTO)
    private long id;

    private String description;

    private int squareMeter;
}
