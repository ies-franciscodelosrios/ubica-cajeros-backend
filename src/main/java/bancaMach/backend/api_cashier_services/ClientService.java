package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.ClientRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * Method that creates or updates a client
     * @param c
     */
    public DTOClient createOrUpdateClient(DTOClient c) {
        if (c.getId() != null) {
            Optional<DTOClient> client = clientRepository.findById(c.getId());
            if (client.isPresent()) {
                c = clientRepository.save(c);
            } else {
                throw new RecordNotFoundException("Client nor found.", c);
            }
        } else {
            c = clientRepository.save(c);
        }
        return c;
    }

    /**
     * Method that get all the clients from the database
     *
     * @return
     */
    public List<DTOClient> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Method that get a client by the id
     *
     * @param id
     * @return
     */
    public DTOClient getClientById(Long id) {
        Optional<DTOClient> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new RecordNotFoundException("Client not found.", id);
        }
    }

    /**
     * Method that deletes a client by the id
     */
    public void deleteClient(Long id) {
        Optional<DTOClient> c = clientRepository.findById(id);
        if (c.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Client not found.", id);
        }
    }
}
