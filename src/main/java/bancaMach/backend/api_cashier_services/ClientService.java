package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.ClientRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Client;
import bancaMach.backend.utils.QRGenerator.QRGenerator;
import bancaMach.backend.utils.data_validation.DNIValidator;
import bancaMach.backend.utils.data_validation.RegexValidator;
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
    public Client createOrUpdateClient(Client c) {
        if(DNIValidator.DNIValidator(c.getDni()) &&
                RegexValidator.validatePasswordFormat(c.getPassword()) &&
                RegexValidator.validateAccountFormat(c.getAccount())){
            if (c.getId() != null) {
                Optional<Client> client = clientRepository.findById(c.getId());
                if (client.isPresent()) {
                    c.setPassword(QRGenerator.sha256(c.getPassword()));
                    c = clientRepository.save(c);
                } else {
                    throw new RecordNotFoundException("Client not found.", c);
                }
            } else {
                c.setPassword(QRGenerator.sha256(c.getPassword()));
                c = clientRepository.save(c);
            }
        }
        return c;
    }

    /**
     * Method that get all the clients from the database
     *
     * @return
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Method that get a client by the id
     *
     * @param id
     * @return
     */
    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new RecordNotFoundException("Client not found.", id);
        }
    }

    /**
     * Method that get a client by the DNI
     *
     * @param dni
     * @return
     */
    public Client getClientByDNI(String dni) {
        if(DNIValidator.DNIValidator(dni)){
            Optional<Client> client = clientRepository.getClientByDNI(dni);
            if (client.isPresent()) {
                return client.get();
            }
        }
        return null;
    }

    /**
     * Method that deletes a client by the id
     */
    public void deleteClient(Long id) {
        Optional<Client> c = clientRepository.findById(id);
        if (c.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Client not found.", id);
        }
    }
}
