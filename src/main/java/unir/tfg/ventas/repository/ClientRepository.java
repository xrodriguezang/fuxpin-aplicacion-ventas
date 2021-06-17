package unir.tfg.ventas.repository;

import org.springframework.data.repository.CrudRepository;
import unir.tfg.ventas.model.Client;

/**
 * Extending Spring CrudRepository
 *
 * @author Xavier Rodríguez
 */
public interface ClientRepository extends CrudRepository <Client, Long> { }
