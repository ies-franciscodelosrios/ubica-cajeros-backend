package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CashierController {

    CashierService cashierService;
    ClientService clientService;

    @Autowired
    public CashierController(CashierService cashierService, ClientService clientService) {
        this.clientService = clientService;
        this.cashierService = cashierService;
    }

    @PostMapping("/newCashier")
    public ResponseEntity<DTOCashier> createCashier(@RequestBody DTOCashier cashier){
        DTOCashier created = cashierService.createOrUpdateCashier(cashier);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/cashiers")
    public ResponseEntity<List<DTOCashier>> getAllCashiers(){
        List<DTOCashier> result = cashierService.getAllCashiers();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashiers/{id}")
    public ResponseEntity<DTOCashier> getCashiertById(@PathVariable Long id){
        DTOCashier cashier = cashierService.getCashierById(id);
        return new ResponseEntity<>(cashier,new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/cashier/{id}")
    public ResponseEntity<DTOCashier> UpdateContact(@RequestBody DTOCashier cashier, Long id,@PathVariable(value = "id") Long cashierId){
        cashier.setId(id);
        DTOCashier update = cashierService.createOrUpdateCashier(cashier);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/cashier/{id}")
    public HttpStatus deleteCashierById(@PathVariable("id") Long id) throws RecordNotFoundException {
        cashierService.deleteCashierById(id);
        return HttpStatus.ACCEPTED;
    }
}
