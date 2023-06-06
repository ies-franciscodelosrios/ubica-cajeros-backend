package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.CashierRepository;
import bancaMach.backend.api_cashier_dto.atm.ATMSaveDTO;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CashierService {

    @Autowired
    CashierRepository cashierRepository;

    @Autowired
    ClientService clientService;

    public Cashier createOrUpdateCashier(Cashier cashier) {
        if (cashier.getId() != null) {
            Optional<Cashier> c = cashierRepository.findById(cashier.getId());
            if (c.isPresent()) {
                cashier = cashierRepository.save(cashier);
            } else {
                throw new RecordNotFoundException("No cashier record exist for given id", cashier);
            }
        } else {
            cashier = cashierRepository.save(cashier);
        }
        return cashier;
    }

    public List<Cashier> getAllCashiers() {
        return cashierRepository.findAll();
    }

    public Cashier getCashierById(Long id) {
        Optional<Cashier> cashier = cashierRepository.findById(id);
        if (cashier.isPresent()) {
            return cashier.get();
        } else {
            throw new RecordNotFoundException("No cashier record exist for given id", id);
        }
    }

    public void deleteCashierById(Long id) {
        Optional<Cashier> cashier = cashierRepository.findById(id);
        if (cashier.isPresent()) {
            cashierRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No cashier record exist for given id", id);
        }
    }

    public List<Cashier> getAllCashiersByLoc(Double lat, Double lng) {
        //middleware
        return cashierRepository.getAllCashiersByLoc(lat, lng);
    }

    public List<Cashier> getAllCashiersByCP(String cp) {
        //middleware
        return cashierRepository.getAllCashiersByCP(cp);
    }

    public List<Cashier> getAllCashiersByDistance(Double lat, Double lng, Integer distanceM) {
        //middleware
        return cashierRepository.getAllCashiersByDistance(lat, lng, distanceM);
    }

    public List<Cashier> getAllCashiersByAddress(String address) {
        //middleware
        return cashierRepository.getAllCashiersByAddress(address);
    }

    public int insertOrUpdateATM(ATMSaveDTO atm) {
        try {
            //Update
            if (atm.getId() != null) {
                if (cashierRepository.findATMByIDAndPosition(atm.getId(), atm.getLattitude(), atm.getLongitude()).isPresent()) {
                    if (atm.getPhoto() == null) {
                        cashierRepository.updateWithoutPhoto(atm.getId(), atm.getAddress(), atm.getAvailable(), atm.getBalance(), atm.getCp(),
                                atm.getLocality());
                    } else {
                        cashierRepository.updateWithPhoto(atm.getId(), atm.getAddress(), atm.getAvailable(), atm.getBalance(), atm.getCp(),
                                atm.getLocality(), atm.getPhoto());
                    }
                    return 1;
                } else {
                    return 0;
                }
            } else { //Insert
                if (!cashierRepository.findATMByPosition(atm.getLattitude(), atm.getLongitude()).isPresent()) {
                    if (atm.getPhoto() == null) {
                        cashierRepository.saveWithoutPhoto(atm.getAddress(), atm.getAvailable(), atm.getBalance(), atm.getCp(),
                                atm.getLocality(), atm.getLattitude(), atm.getLongitude());
                    } else {
                        cashierRepository.saveWithPhoto(atm.getAddress(), atm.getAvailable(), atm.getBalance(), atm.getCp(),
                                atm.getLocality(), atm.getPhoto(), atm.getLattitude(), atm.getLongitude());
                    }
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
