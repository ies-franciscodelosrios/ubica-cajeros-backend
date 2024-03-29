package bancaMach.backend.api_cashiers_controllers;

import bancaMach.backend.api_cashier_dto.atm.ATMResponseDTO;
import bancaMach.backend.api_cashier_dto.atm.ATMSaveDTO;
import bancaMach.backend.api_cashier_dto.atm.AtmDTO;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Cashier;
import bancaMach.backend.api_cashier_services.CashierService;
import bancaMach.backend.api_cashier_services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping("/cashiers")
    @Operation(summary = "Creates a cashier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not created", content = @Content),
    })
    public ResponseEntity<Cashier> createCashier(@RequestBody Cashier cashier){
        Cashier created = cashierService.createOrUpdateCashier(cashier);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/cashiers")
    @Operation(summary = "Shows all the cashiers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashiers found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashiers not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashiers not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiers(){
        List<Cashier> result = cashierService.getAllCashiers();
        setCoordenates(result);
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashiers/{id}")
    @Operation(summary = "Shows a cashier by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Is not found", content = @Content)
    })
    public ResponseEntity<Cashier> getCashiertById(@PathVariable Long id){
        Cashier cashier = cashierService.getCashierById(id);
        List<Cashier> aux = new ArrayList<>();
        aux.add(cashier);
        setCoordenates(aux);
        return new ResponseEntity<>(cashier,new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/cashiers/{id}")
    @Operation(summary = "Edits the cashier information by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier edited", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not edited", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Id not found", content = @Content)
    })
    public ResponseEntity<Cashier> UpdateCashier(@RequestBody Cashier cashier, @PathVariable(value = "id") Long id){
        cashier.setId(id);
        Cashier update = cashierService.createOrUpdateCashier(cashier);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/cashiers/{id}")
    @Operation(summary = "Deletes a cashier by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Id not found", content = @Content)
    })
    public HttpStatus deleteCashierById(@PathVariable("id") Long id) throws RecordNotFoundException {
        cashierService.deleteCashierById(id);
        return HttpStatus.ACCEPTED;
    }

    /**
     * GEOLOC ENDPOINTS
     */

    @PostMapping("/cashiers/distancedefault")
    @Operation(summary = "Get cashiers by default rasius distance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Postcode not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiersByLoc(@RequestBody AtmDTO georeq) {
        List<Cashier> result = cashierService.getAllCashiersByLoc(georeq.getLat(), georeq.getLng());
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/cashiers/distance")
    @Operation(summary = "Shows all the cashier by his distance between it and the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashiers found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashiers not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Postcode not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiersByDistance(@RequestBody AtmDTO cashierRequest){
        List<Cashier> result = cashierService.getAllCashiersByDistance(cashierRequest.getLat(), cashierRequest.getLng(), cashierRequest.getDistance());
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashiers/cp/{cp}")
    @Operation(summary = "Shows a cashier by his postcode near the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Postcode not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiersByLoc(@PathVariable("cp") String cp){
        List<Cashier> result = cashierService.getAllCashiersByCP(cp);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashiers/address/{address}")
    @Operation(summary = "Get cashiers by his address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Postcode not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiersByAddress(@PathVariable("address") String address){
        List<Cashier> result = cashierService.getAllCashiersByAddress(address);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashiers/{lat}/{lng}")
    @Operation(summary = "Shows a cashier by his location in 500 meters range with the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashier found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashier not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's location not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiersByLoc(
            @PathVariable Double lat,
            @PathVariable Double lng) {
        List<Cashier> result = cashierService.getAllCashiersByLoc(lat, lng);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cashiers/{lat}/{lng}/{distanceM}")
    @Operation(summary = "Shows all the cashier by his distance between it and the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cashiers found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "Cashiers not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cashier's Postcode not found", content = @Content)
    })
    public ResponseEntity<List<Cashier>> getAllCashiersByDistance(
            @PathVariable Double lat,
            @PathVariable Double lng,
            @PathVariable Integer distanceM){
        List<Cashier> result = cashierService.getAllCashiersByDistance(lat, lng, distanceM);
        setCoordenates(result);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    private void setCoordenates(List<Cashier> cashiers) {
        for (Cashier c : cashiers) {
            c.setLattitude(c.getPosition().getX());
            c.setLongitude(c.getPosition().getY());
        }
    }

    @PostMapping("/atm")
    @Operation(summary = "Insert or update an ATM")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ATM created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Cashier.class))}),
            @ApiResponse(responseCode = "400", description = "ATM not created", content = @Content),
    })
    public ResponseEntity<ATMResponseDTO> InsertOrUpdateATM(@RequestBody ATMSaveDTO atm){
        ATMResponseDTO result = new ATMResponseDTO();
        result.setResponse(cashierService.insertOrUpdateATM(atm));
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.CREATED);
    }
}