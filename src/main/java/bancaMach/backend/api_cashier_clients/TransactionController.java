package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.QRGenerator.QRGenerator;
import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_models.DTORequestTransaction;
import bancaMach.backend.api_cashier_models.DTOTransaction;
import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import bancaMach.backend.api_cashier_services.TransactionService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins="url")
@RestController
@RequestMapping("/api")
public class TransactionController {

    private CashierService cashierService;
    private ClientService clientService;
    private TransactionService transactionService;

    @Autowired
    public TransactionController(CashierService cashierService,
                                 ClientService clientService,
                                 TransactionService transactionService) {
        this.cashierService = cashierService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<DTOTransaction> createTransaction(@RequestBody DTORequestTransaction transaction){
        DTOTransaction created = new DTOTransaction();
        DTOCashier cashier = cashierService.getCashierById(transaction.getCashier());
        created.setClient(clientService.getClientById(transaction.getUser()));
        created.setCashier(cashier);
        created.setInit_date(LocalDateTime.now());
        created.setEnd_date(LocalDateTime.now().plusHours(12));
        created.setType(transaction.getType());
        created.setAmount(transaction.getAmount());
        if(transaction.getType()){
            cashier.setBalance(cashier.getBalance()+transaction.getAmount()); //enter amount
        }
        else {
            cashier.setBalance(cashier.getBalance()-transaction.getAmount()); //extract amount
        }
        try {
            String qrCodeText = ""+transaction.getUser()+transaction.getCashier()+transaction.getType()+transaction.getAmount();
            created.setSecutityCode(QRGenerator.generateQRCodeImageAsBase64(qrCodeText,300,300));
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        created = transactionService.createOrUpdateTransaction(created);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<DTOTransaction>> getAllTransactions(){
        List<DTOTransaction> result = transactionService.getAllTransactions();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<DTOTransaction> getTransactionById(@PathVariable Long id) throws IOException {
        DTOTransaction transaction = transactionService.getTransantionById(id);
        List<DTOTransaction> aux = new ArrayList<>();
        aux.add(transaction);
        return new ResponseEntity<>(transaction,new HttpHeaders(), HttpStatus.OK);
    }
}
