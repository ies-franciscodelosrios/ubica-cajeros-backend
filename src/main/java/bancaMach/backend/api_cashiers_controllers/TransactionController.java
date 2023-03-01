package bancaMach.backend.api_cashiers_controllers;

import bancaMach.backend.utils.QRGenerator.QRGenerator;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import bancaMach.backend.api_cashier_models.dataobject.Client;
import bancaMach.backend.api_cashier_models.dataobject.Transaction;
import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import bancaMach.backend.api_cashier_services.TransactionService;
import com.google.zxing.WriterException;
import bancaMarch.dto.transactions.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public TransactionController(CashierService cashierService, ClientService clientService, TransactionService transactionService) {
        this.cashierService = cashierService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    @Operation(summary = "Creates a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))}),
            @ApiResponse(responseCode = "400", description = "Transaction not created", content = @Content),
    })
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws RecordNotFoundException{
        Cashier cashier = cashierService.getCashierById(transaction.getCashier());
        Client client = clientService.getClientById(transaction.getClient());
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
                    throw new RecordNotFoundException("The cashier does not have enough credit.",-1);
                }
            }
            try {
                String codeText = ""+transaction.getClient()+transaction.getCashier()+transaction.getType()+transaction.getAmount();
                String qrCode = QRGenerator.generateQRCodeImageAsBase64(codeText,300,300);
                created = new Transaction(client,cashier,qrCode,LocalDateTime.now(),LocalDateTime.now().plusMinutes(2),
                        transaction.getAmount(),transaction.getType());
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            created = transactionService.createOrUpdateTransaction(created);
        }
        else {
            throw new RecordNotFoundException("The cashier is not available.",-1);
        }
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/transactions")
    @Operation(summary = "Get all the transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))}),
            @ApiResponse(responseCode = "400", description = "Transaction not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content)
    })
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> result = transactionService.getAllTransactions();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    @Operation(summary = "Get a transaction by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))}),
            @ApiResponse(responseCode = "400", description = "Transaction not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Transaction's Id not found", content = @Content)
    })
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) throws IOException {
        Transaction transaction = transactionService.getTransantionById(id);
        List<Transaction> aux = new ArrayList<>();
        aux.add(transaction);
        return new ResponseEntity<>(transaction,new HttpHeaders(), HttpStatus.OK);
    }
}
