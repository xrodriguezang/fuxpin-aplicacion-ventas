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

    List<Almacen> findAll();
}
