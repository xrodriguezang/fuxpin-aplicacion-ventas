package unir.tfg.ventas.services;

import unir.tfg.ventas.model.Product;

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
public interface IProductService {

    List<Product> getProductByColour (String color);

    List<Product> getAllProducts();

    List<Product> getById();

}
