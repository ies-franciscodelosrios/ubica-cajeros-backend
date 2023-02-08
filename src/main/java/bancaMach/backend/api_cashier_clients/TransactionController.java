package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.QRGenerator.QRGenerator;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import bancaMach.backend.api_cashier_models.DTO.DTOTransaction;
import bancaMach.backend.api_cashier_models.dataobject.Client;
import bancaMach.backend.api_cashier_models.dataobject.Transaction;
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
    public ResponseEntity<Transaction> createTransaction(@RequestBody DTOTransaction transaction) throws RecordNotFoundException{
        Cashier cashier = cashierService.getCashierById(transaction.getCashier());
        Client client = clientService.getClientById(transaction.getClient());
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        Transaction created = null;
        if(cashier.getAvailable() && transaction.getAmount()>0) {
            if(transaction.getType()){ //enter amount
                cashier.setBalance(cashier.getBalance()+transaction.getAmount());
            }
            else { //extract amount
                if(cashier.getBalance()>0 && cashier.getBalance()-transaction.getAmount()>=0) {
                    cashier.setBalance(cashier.getBalance()-transaction.getAmount());
                }
                else {
                    return new ResponseEntity<>(created, new HttpHeaders(), status);
                }
            }
            try {
                String codeText = ""+transaction.getClient()+transaction.getCashier()+transaction.getType()+transaction.getAmount();
                String qrCode = QRGenerator.generateQRCodeImageAsBase64(codeText,300,300);
                created = new Transaction(client,cashier,qrCode,LocalDateTime.now(),LocalDateTime.now().plusHours(12),
                        transaction.getAmount(),transaction.getType());
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            created = transactionService.createOrUpdateTransaction(created);
            status=HttpStatus.CREATED;
        }
        return new ResponseEntity<>(created, new HttpHeaders(), status);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> result = transactionService.getAllTransactions();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) throws IOException {
        Transaction transaction = transactionService.getTransantionById(id);
        List<Transaction> aux = new ArrayList<>();
        aux.add(transaction);
        return new ResponseEntity<>(transaction,new HttpHeaders(), HttpStatus.OK);
    }
}
