package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.CashierRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOCashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashierService {

    @Autowired
    CashierRepository cashierRepository;

    public DTOCashier createOrUpdateCashier(DTOCashier cashier){
        if (cashier.getId()!=null){
            Optional<DTOCashier> c = cashierRepository.findById(cashier.getId());
            if (c.isPresent()){
                cashier = cashierRepository.save(cashier);
            }else{
                throw new RecordNotFoundException("No cashier record exist for given id", cashier);
            }
        }else{
            cashier = cashierRepository.save(cashier);
        }
        return cashier;
    }

    public List<DTOCashier> getAllCashiers() { return cashierRepository.findAll(); }

    public DTOCashier getCashierById(Long id){
        Optional<DTOCashier> cashier = cashierRepository.findById(id);
        if (cashier.isPresent()){
            return cashier.get();
        }else{
            throw new RecordNotFoundException("No cashier record exist for given id", id);
        }
    }

    public void deleteCashierById(Long id){
        Optional<DTOCashier> cashier = cashierRepository.findById(id);
        if (cashier.isPresent()){
            cashierRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No cashier record exist for given id", id);
        }
    }
}
