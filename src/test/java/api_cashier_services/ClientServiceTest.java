package api_cashier_services;

import bancaMach.backend.api_cahier_repositories.ClientRepository;
import bancaMach.backend.api_cashier_models.dataobject.Client;
import bancaMach.backend.api_cashier_services.ClientService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;


class ClientServiceTest {

    @Mock
    @Autowired
    ClientRepository clientRepository;
    @InjectMocks
    @Autowired
    ClientService clientService;

    @Test
    void createOrUpdateClient(Client c) {
        clientService = new ClientService();
        assertEquals("manolo@gmail.com", clientService.getClientById(1L).getAccount());
    }

    @Test
    void getAllClients() {
        clientService = new ClientService();
        assertEquals(1L, clientService.getClientById(1L).getId());
    }

    @Test
    void getClientById() {
        clientService = new ClientService();
        assertEquals("manolo@gmail.com", clientService.getClientById(1L).getAccount());
    }

    @Test
    void deleteClient() {
    }
}