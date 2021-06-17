package unir.tfg.ventas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.model.Client;
import unir.tfg.ventas.repository.AlmacenRepository;
import unir.tfg.ventas.repository.ClientRepository;

import java.util.List;

/**
 * Service that provides the interation with de database - object Client
 *
 * @author Xavier Rodríguez
 */
@Service
public class ClientService implements IClientService {

    // Injects ClientRepository
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Get all elements client
     *
     * @return list client
     */
    @Override
    public List<Client> findAll() {

        List<Client> clients = (List<Client>) clientRepository.findAll();

        return clients;

    }

}
