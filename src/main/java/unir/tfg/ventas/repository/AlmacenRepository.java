package unir.tfg.ventas.repository;

import org.springframework.data.repository.CrudRepository;
import unir.tfg.ventas.model.Almacen;

/**
 * Extending Spring CrudRepository, we will use the methods implemented: existsById(), findAll(),...
 */
public interface AlmacenRepository extends CrudRepository <Almacen, Long> { }
