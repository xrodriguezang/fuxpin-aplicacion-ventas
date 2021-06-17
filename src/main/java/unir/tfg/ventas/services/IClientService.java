package unir.tfg.ventas.services;

import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.model.Client;

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
public interface IClientService {

    List<Client> findAll();
}
