package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_models.DTORequestGeoCashier;
import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins="url")
@RestController
@RequestMapping("/api")
public class CashierController {

    private CashierService cashierService;
    private ClientService clientService;

    @Autowired
    public CashierController(CashierService cashierService, ClientService clientService) {
        this.clientService = clientService;
        this.cashierService = cashierService;
    }

    @PostMapping("/cashier")
    public ResponseEntity<DTOCashier> createCashier(@RequestBody DTOCashier cashier){
        DTOCashier created = cashierService.createOrUpdateCashier(cashier);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/cashiers")
    public ResponseEntity<List<DTOCashier>> getAllCashiers(){
        List<DTOCashier> result = cashierService.getAllCashiers();
        setCoordenates(result);
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<DTOCashier> getCashiertById(@PathVariable Long id){
        DTOCashier cashier = cashierService.getCashierById(id);
        List<DTOCashier> aux = new ArrayList<>();
        aux.add(cashier);
        setCoordenates(aux);
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

    /**
     * GEOLOC ENDPOINTS
     */

    @GetMapping("/geocashiers")
    public ResponseEntity<List<DTOCashier>> getAllCashiersByLoc(@RequestBody DTORequestGeoCashier georeq) {
        List<DTOCashier> result = cashierService.getAllCashiersByLoc(georeq.getLat(), georeq.getLng());
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/geocashiers/{distanceM}")
    public ResponseEntity<List<DTOCashier>> getAllCashiersByDistance(
            @RequestBody DTORequestGeoCashier georeq,
            @PathVariable Integer distanceM){
        List<DTOCashier> result = cashierService.getAllCashiersByDistance(georeq.getLat(), georeq.getLng(), distanceM);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/geocashiers/{lat}/{lng}")
    public ResponseEntity<List<DTOCashier>> getAllCashiersByLoc(
            @PathVariable Double lat,
            @PathVariable Double lng) {
        List<DTOCashier> result = cashierService.getAllCashiersByLoc(lat, lng);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/geocashiers/{cp}")
    public ResponseEntity<List<DTOCashier>> getAllCashiersByLoc(@RequestBody DTORequestGeoCashier georeq, @PathVariable("cp") Integer cp){
        List<DTOCashier> result = cashierService.getAllCashiersByCP(cp);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/geocashiers/{lat}/{lng}/{distanceM}")
    public ResponseEntity<List<DTOCashier>> getAllCashiersByDistance(
            @PathVariable Double lat,
            @PathVariable Double lng,
            @PathVariable Integer distanceM){
        List<DTOCashier> result = cashierService.getAllCashiersByDistance(lat, lng, distanceM);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    private void setCoordenates(List<DTOCashier> cashiers) {
        for (DTOCashier c : cashiers) {
            c.setLattitude(c.getPosition().getX());
            c.setLongitude(c.getPosition().getY());
        }
    }
}
