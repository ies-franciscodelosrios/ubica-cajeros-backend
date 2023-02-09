package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOCashier;
import bancaMach.backend.api_cashier_models.DTOIncidence;
import bancaMach.backend.api_cashier_services.IncidenceService;
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
public class IncidenceController {

    private IncidenceService incidenceService;

    @Autowired
    public IncidenceController(IncidenceService incidenceService) {
        this.incidenceService = incidenceService;
    }

    @PostMapping("/incidence")
    @Operation(summary = "Creates an incidence")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidence created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOCashier.class))}),
            @ApiResponse(responseCode = "400", description = "Incidence not created", content = @Content),
    })
    public ResponseEntity<DTOIncidence> createIncidence(@RequestBody DTOIncidence incidence){
        DTOIncidence created = incidenceService.createOrUpdateIncidence(incidence);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/incidences")
    @Operation(summary = "Shows all the incidences")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidences found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOCashier.class))}),
            @ApiResponse(responseCode = "400", description = "Incidences not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Incidences not found", content = @Content)
    })
    public ResponseEntity<List<DTOIncidence>> getAllIncidences(){
        List<DTOIncidence> result = incidenceService.getAllIncidences();
        return new ResponseEntity<>(result,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/incidence/{id}")
    @Operation(summary = "Shows an incidence by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidence found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOCashier.class))}),
            @ApiResponse(responseCode = "400", description = "Incidence not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Incidence's Is not found", content = @Content)
    })
    public ResponseEntity<DTOIncidence> getIncidenceById(@PathVariable Long id){
        DTOIncidence incidence = incidenceService.getIncidenceById(id);
        List<DTOIncidence> aux = new ArrayList<>();
        aux.add(incidence);
        return new ResponseEntity<>(incidence,new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/incidence/{id}")
    @Operation(summary = "Edits the incidence information by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidence edited", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOCashier.class))}),
            @ApiResponse(responseCode = "400", description = "Incidence not edited", content = @Content),
            @ApiResponse(responseCode = "404", description = "Incidence's Id not found", content = @Content)
    })
    public ResponseEntity<DTOIncidence> UpdateIncidence(@RequestBody DTOIncidence incidence, Long id,@PathVariable(value = "id") Long incidenceId){
        incidence.setId(id);
        DTOIncidence update = incidenceService.createOrUpdateIncidence(incidence);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/incidence/{id}")
    @Operation(summary = "Deletes an incidence by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidence deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOCashier.class))}),
            @ApiResponse(responseCode = "400", description = "Incidence not deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Incidence's Id not found", content = @Content)
    })
    public HttpStatus deleteIncidenceById(@PathVariable("id") Long id) throws RecordNotFoundException {
        incidenceService.deleteIncidence(id);
        return HttpStatus.ACCEPTED;
    }
}
