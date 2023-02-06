package bancaMach.backend.api_cashier_clients;

import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.DTOClient;
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

//@CrossOrigin(origins="url")
@RestController
@RequestMapping("/api")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/client")
    @Operation(summary = "Shows all the clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Clients not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Clients not found", content = @Content)
    })
    public ResponseEntity<List<DTOClient>> getAllClients(){
        List<DTOClient> result = clientService.getAllClients();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "Shows a client by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Client not valid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client's Id not found", content = @Content)
    })
    public ResponseEntity<DTOClient> getClientById(@PathVariable("id") Long id) throws RecordNotFoundException{
        DTOClient client = clientService.getClientById(id);
        return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/client")
    @Operation(summary = "Creates a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Client not created", content = @Content),
    })
    public ResponseEntity<DTOClient> createClient(@RequestBody DTOClient client) throws RecordNotFoundException{
        DTOClient clientCreated = clientService.createOrUpdateClient(client);
        return new ResponseEntity<>(clientCreated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    @Operation(summary = "Edits the client information by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client edited", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Client not edited", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client's Id not found", content = @Content)
    })
    public ResponseEntity<DTOClient> updateClient(@RequestBody DTOClient client, @PathVariable("id") Long id) throws RecordNotFoundException{
        client.setId(id);
        client = clientService.createOrUpdateClient(client);
        return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    @Operation(summary = "Deletes a client by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DTOClient.class))}),
            @ApiResponse(responseCode = "400", description = "Client not deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client's Id not found", content = @Content)
    })
    public HttpStatus deleteClient(@PathVariable("id") Long id) throws RecordNotFoundException{
        clientService.deleteClient(id);
        return HttpStatus.ACCEPTED;
    }
}
