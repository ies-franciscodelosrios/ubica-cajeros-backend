package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.TransactionRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_models.DTOTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public DTOTransaction createOrUpdateTransaction(DTOTransaction transaction){
        if (transaction.getId()!=null){
            Optional<DTOTransaction> t = transactionRepository.findById(transaction.getId());
            if (t.isPresent()){
                transaction = transactionRepository.save(transaction);
            }else{
                throw new RecordNotFoundException("No transaction record exist for given id", transaction);
            }
        }else{
            transaction = transactionRepository.save(transaction);
        }
        return transaction;
    }

    public List<DTOTransaction> getAllTransactions() { return transactionRepository.findAll(); }

    public DTOTransaction getTransantionById(Long id){
        Optional<DTOTransaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            return transaction.get();
        }else{
            throw new RecordNotFoundException("No transaction record exist for given id", id);
        }
    }

    public void deleteTransactionById(Long id){
        Optional<DTOTransaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            transactionRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No transaction record exist for given id", id);
        }
    }
}
