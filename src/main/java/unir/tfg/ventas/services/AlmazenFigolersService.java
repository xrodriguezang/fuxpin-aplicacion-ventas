package unir.tfg.ventas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unir.tfg.ventas.model.AlmazenFigolers;
import unir.tfg.ventas.repository.AlmazenFigolersRepository;

import java.util.List;

@Service
public class AlmazenFigolersService implements IAlmazenFigolersServices {

    @Autowired
    private AlmazenFigolersRepository almazenFigolersRepository;

    @Override
    public List<AlmazenFigolers> findAll() {

        List<AlmazenFigolers> almazenes = (List<AlmazenFigolers>) almazenFigolersRepository.findAll();

        return almazenes;

    }
}
