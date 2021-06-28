package unir.tfg.ventas.model.microservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The same object that provides the microservice
 *
 * Object that contains the role of the user.
 *
 * It's roles is added to fuxpin-applicacionventas
 *
 * @author Xavier Rodríguez
 */

@Getter @Setter @AllArgsConstructor
public class Role {

    String roleId;
    String roleDescription;

}
