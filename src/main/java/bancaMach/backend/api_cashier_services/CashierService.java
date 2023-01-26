package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.CashierRepository;
import bancaMach.backend.api_cashier_models.DTOCashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierService {

    @Autowired
    CashierRepository cashierRepository;

    /**
     * Get all Cashiers
     * @return List od cashiers
     */
    public List<DTOCashier> getAllCashiers() { return cashierRepository.findAll(); }
}
