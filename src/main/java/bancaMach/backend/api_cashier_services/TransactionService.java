package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.TransactionRepository;
import bancaMach.backend.api_cashier_dto.transactions.TransactionDTO;
import bancaMach.backend.api_cashier_dto.transactions.TransactionRequestDTO;
import bancaMach.backend.api_cashier_dto.transactions.TransactionResponseDTO;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import bancaMach.backend.api_cashier_models.dataobject.Client;
import bancaMach.backend.api_cashier_models.dataobject.Transaction;
import bancaMach.backend.utils.QRGenerator.QRGenerator;
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
        Transaction transaction = new Transaction(dto.getId(), client, cashier, dto.getSecurityCode(),dto.getInit_date(),
                                                    dto.getEnd_date(), dto.getAmount(),dto.getType(), false);
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
                    transaction = new Transaction(client,cashier, null, LocalDateTime.now(),LocalDateTime.now().plusMinutes(10),
                            transaction.getAmount(),transaction.getType(), false);
                    transaction = transactionRepository.save(transaction);
                    String codeText = transaction.getClient().getId()+"_"+transaction.getCashier().getId()+"_"+transaction.getId()+"_"+transaction.getInit_date();
                    String qrCode = QRGenerator.generateQRCodeImageAsBase64(codeText,300,300);
                    transaction.setSecurityCode(QRGenerator.sha256(qrCode));
                    transaction = transactionRepository.save(transaction);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                throw new RecordNotFoundException("The cashier is not available.",-1);
            }
        }
        dto.setId(transaction.getId());
        dto.setClient(transaction.getClient().getId());
        dto.setCashier(transaction.getCashier().getId());
        dto.setSecurityCode(transaction.getSecurityCode());
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
        String status = "Data error, transaction data does not match.";
        Double amount = Double.valueOf(0);
        Transaction transaction = transactionRepository.getTransactionByCode(requestDTO.getSecurityCode());
        Client user = null;
        Cashier atm = null;
        if(transaction!=null){
            user = clientService.getClientById(transaction.getClient().getId());
            atm = cashierService.getCashierById(transaction.getCashier().getId());
        }
        if(transaction!=null && user!=null && atm!=null &&
                transaction.getClient().getId()==user.getId() && transaction.getCashier().getId()==requestDTO.getAtmId()) {
            if(!transaction.getFinished()){
                if(transaction.getEnd_date().isAfter(LocalDateTime.now())) {
                    if (transaction.getType()) { //enter amount
                        atm.setBalance(atm.getBalance() + transaction.getAmount());
                        status="Transaction accepted.";
                        amount=transaction.getAmount();
                    } else { //extract amount
                        if (atm.getBalance() > 0 && atm.getBalance() - transaction.getAmount() >= 0) {
                            atm.setBalance(atm.getBalance() - transaction.getAmount());
                            status="Transaction accepted.";
                            amount=transaction.getAmount()*-1;
                        } else {
                            status="Error, ATM does not have enough balance.";
                        }
                    }
                    cashierService.createOrUpdateCashier(atm);
                    transaction.setFinished(true);
                    transactionRepository.save(transaction);
                }
                else {
                    status="Error, security code has expired.";
                }
            }
            else {
                status="Error, the transaction has already been processed.";
            }
        }
        TransactionResponseDTO responseDTO = new TransactionResponseDTO(status, amount);
        return responseDTO;
    }
}