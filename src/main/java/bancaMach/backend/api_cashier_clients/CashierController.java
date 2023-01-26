package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_services.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CashierController {

    CashierService cashierService;

    @Autowired
    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping("/cashiers")
    public ResponseEntity<List<DTOCashier>> getAllCashiers(){
        List<DTOCashier> result = cashierService.getAllCashiers();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }
}
