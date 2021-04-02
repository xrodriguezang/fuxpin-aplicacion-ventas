package unir.tfg.ventas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.repository.AlmacenRepository;

import java.util.List;

@Service
public class AlmacenService implements IAlmacenService {

    // Injects AlmacenRepository
    @Autowired
    private AlmacenRepository almacenRepository;

    /**
     * Get all elements almacen
     *
     * @return list almacen
     */
    @Override
    public List<Almacen> findAll() {

        List<Almacen> almacenes = (List<Almacen>) almacenRepository.findAll();

        return almacenes;

    }
}
