package unir.tfg.ventas.repository;

import org.springframework.data.repository.CrudRepository;
import unir.tfg.ventas.model.Almacen;

/**
 * Extending Spring CrudRepository
 *
 * @author Xavier Rodríguez
 */
public interface AlmacenRepository extends CrudRepository <Almacen, Long> { }
