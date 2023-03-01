package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.TransactionRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import bancaMach.backend.api_cashier_models.dataobject.Client;
import bancaMach.backend.api_cashier_models.dataobject.Transaction;
import bancaMach.backend.utils.QRGenerator.QRGenerator;
import bancaMarch.dto.transactions.TransactionDTO;
import bancaMarch.dto.transactions.TransactionRequestDTO;
import bancaMarch.dto.transactions.TransactionResponseDTO;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    CashierService cashierService;

    public TransactionDTO createOrUpdateTransaction(TransactionDTO dto){
        Cashier cashier = cashierService.getCashierById(dto.getCashier());
        Client client = clientService.getClientById(dto.getClient());
        Transaction transaction = new Transaction(dto.getId(),client, cashier, dto.getSecurityCode(),dto.getInit_date(),
                                                    dto.getEnd_date(), dto.getAmount(),dto.getType());
        if (transaction.getId()!=null){
            Optional<Transaction> t = transactionRepository.findById(transaction.getId());
            if (t.isPresent()){
                transaction = transactionRepository.save(transaction);
            }else{
                throw new RecordNotFoundException("No transaction record exist for given id", transaction);
            }
        }else{
            if(cashier.getAvailable() && transaction.getAmount()>0) {
                try {
                    String codeText = ""+transaction.getId();
                    String qrCode = QRGenerator.generateQRCodeImageAsBase64(codeText,300,300);
                    transaction = new Transaction(client,cashier,qrCode, LocalDateTime.now(),LocalDateTime.now().plusMinutes(2),
                            transaction.getAmount(),transaction.getType());
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                transaction = transactionRepository.save(transaction);
            }
            else {
                throw new RecordNotFoundException("The cashier is not available.",-1);
            }
        }
        dto.setId(transaction.getId());
        dto.setClient(transaction.getCashier().getId());
        dto.setCashier(transaction.getCashier().getId());
        dto.setSecurityCode(transaction.getSecutityCode());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        dto.setInit_date(transaction.getInit_date());
        dto.setEnd_date(transaction.getEnd_date());
        return dto;
    }

    public List<Transaction> getAllTransactions() { return transactionRepository.findAll(); }

    public Transaction getTransantionById(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            return transaction.get();
        }else{
            throw new RecordNotFoundException("No transaction record exist for given id", id);
        }
    }

    public void deleteTransactionById(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            transactionRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No transaction record exist for given id", id);
        }
    }

    public TransactionResponseDTO getTransactionStatus(TransactionRequestDTO requestDTO) {
        TransactionResponseDTO responseDTO = null;
        if (transaction.getType()) { //enter amount
            cashier.setBalance(cashier.getBalance() + transaction.getAmount());
        } else { //extract amount
            if (cashier.getBalance() > 0 && cashier.getBalance() - transaction.getAmount() >= 0) {
                cashier.setBalance(cashier.getBalance() - transaction.getAmount());
            } else {
                throw new RecordNotFoundException("The cashier does not have enough credit.", -1);
            }
        }
        return responseDTO;
    }
}