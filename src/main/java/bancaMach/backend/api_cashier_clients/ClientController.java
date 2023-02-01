package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOClient;
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

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/client")
    @Operation(summary = "Muestra todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Clientes no válidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Clientes no encontrados", content = @Content)
    })
    public ResponseEntity<List<DTOClient>> getAllClients(){
        List<DTOClient> result = clientService.getAllClients();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "Muestra un cliente dada su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Cliente no válido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    public ResponseEntity<DTOClient> getClientById(@PathVariable("id") Long id) throws RecordNotFoundException{
        DTOClient client = clientService.getClientById(id);
        return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<DTOClient> createClient(@RequestBody DTOClient client) throws RecordNotFoundException{
        DTOClient clientCreated = clientService.createOrUpdateClient(client);
        return new ResponseEntity<>(clientCreated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<DTOClient> updateClient(@RequestBody DTOClient client, @PathVariable("id") Long id) throws RecordNotFoundException{
        client.setId(id);
        client = clientService.createOrUpdateClient(client);
        return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public HttpStatus deleteClient(@PathVariable("id") Long id) throws RecordNotFoundException{
        clientService.deleteClient(id);
        return HttpStatus.ACCEPTED;
    }
}
