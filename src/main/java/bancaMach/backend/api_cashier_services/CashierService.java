package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.CashierRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CashierService {

    @Autowired
    CashierRepository cashierRepository;

    @Autowired
    ClientService clientService;

    public Cashier createOrUpdateCashier(Cashier cashier){
        if (cashier.getId()!=null){
            Optional<Cashier> c = cashierRepository.findById(cashier.getId());
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

    public List<Cashier> getAllCashiers() { return cashierRepository.findAll(); }

    public Cashier getCashierById(Long id){
        Optional<Cashier> cashier = cashierRepository.findById(id);
        if (cashier.isPresent()){
            return cashier.get();
        }else{
            throw new RecordNotFoundException("No cashier record exist for given id", id);
        }
    }

    public void deleteCashierById(Long id){
        Optional<Cashier> cashier = cashierRepository.findById(id);
        if (cashier.isPresent()){
            cashierRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No cashier record exist for given id", id);
        }
    }

    public List<Cashier> getAllCashiersByLoc(Double lat, Double lng){
        //middleware
        return cashierRepository.getAllCashiersByLoc(lat, lng);
    }

    public List<Cashier> getAllCashiersByCP(String cp){
        //middleware
        return cashierRepository.getAllCashiersByCP(cp);
    }

    public List<Cashier> getAllCashiersByDistance(Double lat, Double lng, Integer distanceM){
        //middleware
        return cashierRepository.getAllCashiersByDistance(lat,lng, distanceM);
    }
}
