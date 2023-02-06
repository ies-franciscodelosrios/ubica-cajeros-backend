package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private CashierService cashierService;
    private ClientService clientService;
    private TransactionController transactionController;

    @Autowired
    public TransactionController(CashierService cashierService,
                                 ClientService clientService,
                                 TransactionController transactionController) {
        this.cashierService = cashierService;
        this.clientService = clientService;
        this.transactionController = transactionController;
    }

    //@PostMapping("/transaction/{idUser}/{idCashier}/")
}
