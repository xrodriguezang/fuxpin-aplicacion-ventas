package unir.tfg.ventas.repository;

import org.springframework.data.repository.CrudRepository;
import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.model.Product;

import java.util.List;

/**
 * Extending Spring CrudRepository
 *
 * @author Xavier Rodríguez
 */
public interface ProductRepository extends CrudRepository <Product, Long> {

    // Spring Date uses PropertyPath method to extract path to a property for a predicate constructed from method.
    // Do the implementation for us.
    // More info: https://spring.io/projects/spring-data-jpa
    List<Product> findAllByColour ( String colour);

}
