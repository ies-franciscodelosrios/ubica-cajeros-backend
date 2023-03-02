package bancaMach.backend.api_cashiers_controllers;

import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Transaction;
import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import bancaMach.backend.api_cashier_services.TransactionService;
import bancaMarch.dto.transactions.TransactionRequestDTO;
import bancaMarch.dto.transactions.TransactionDTO;
import bancaMarch.dto.transactions.TransactionResponseDTO;
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
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transaction) throws RecordNotFoundException{
        return new ResponseEntity<>(transactionService.createOrUpdateTransaction(transaction), new HttpHeaders(), HttpStatus.CREATED);
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


    @GetMapping("/transactions/status")
    public ResponseEntity<TransactionResponseDTO> getTransactionStatus(@RequestBody TransactionRequestDTO requestDTO){
        TransactionResponseDTO responseDTO = transactionService.getTransactionStatus(requestDTO);
        return new ResponseEntity<>(responseDTO, new HttpHeaders(), HttpStatus.OK);
    }
}
