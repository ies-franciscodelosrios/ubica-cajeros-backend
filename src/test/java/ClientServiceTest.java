import bancaMach.backend.api_cahier_repositories.ClientRepository;
import bancaMach.backend.api_cashier_models.DTOClient;
import bancaMach.backend.api_cashier_services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
class ClientServiceTest {

    @Autowired
    ClientRepository clientRepository;

    ClientService clientService;

    MockMvc mvc;
    @Test
    void createOrUpdateClient(DTOClient c) {

    }

    @Test
    void getAllClients() {

    }

    @Test
    void getClientById() {
    }

    @Test
    void deleteClient() {
    }
}