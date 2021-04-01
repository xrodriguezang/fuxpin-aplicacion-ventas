package unir.tfg.ventas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "ALMAZEN_FIGOLERS")
@Getter @Setter
public class AlmazenFigolers {

    @Id
    @GeneratedValue (strategy =  GenerationType.AUTO)
    private long id;

    private String description;

    private int squareMeter;
}
