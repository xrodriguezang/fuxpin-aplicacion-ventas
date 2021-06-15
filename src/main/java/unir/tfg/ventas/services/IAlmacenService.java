package unir.tfg.ventas.services;

import unir.tfg.ventas.model.Almacen;

import java.util.List;

/**
 *
 * This interface provides the contract methods.
 *
 * Filter the CRUD methods.
 *
 * @author Xavier Rodríguez
 *
 */
public interface IAlmacenService {

    // It's no necessay implements this method. Spring Data do it for us
    List<Almacen> findAll();
}
