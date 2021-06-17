package unir.tfg.ventas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unir.tfg.ventas.model.Product;
import unir.tfg.ventas.repository.ProductRepository;

import java.util.List;

/**
 * Service that provides the database object interaction - Product Object
 *
 * @author Xavier Rodríguez
 *
 */
@Service
public class ProductService implements IProductService {

    // Injects AlmacenRepository
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductByColour(String color) {
        return productRepository.findAllByColour(color);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> getById() {
        return null;
    }

}
