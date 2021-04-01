package unir.tfg.ventas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unir.tfg.ventas.model.AlmazenFigolers;

@Repository
public interface AlmazenFigolersRepository  extends CrudRepository <AlmazenFigolers, Long> { }
