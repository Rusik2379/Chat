package Sber.Sber.Controllers;

import Sber.Sber.auth.request.CreateClientRequest;
import Sber.Sber.auth.request.CreateRealtyRequest;
import Sber.Sber.auth.request.ImageRequest;
import Sber.Sber.auth.service.ClientService;
import Sber.Sber.auth.service.RealtyService;
import Sber.Sber.models.Realty;
import Sber.Sber.repositories.ClientRepository;
import Sber.Sber.repositories.RealtyRepository;
import Sber.Sber.common.ApiResponse;
import Sber.Sber.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class ClientController {

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createClient")
    public ResponseEntity<String> createClient(@Valid @RequestBody CreateClientRequest createClientRequest){
        clientService.createClient(createClientRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Invitation sent to " + createClientRequest.getId_company()+ " set company name: " + createClientRequest.getId_user()) + " successfully");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/byCompanyClient/{id}")
    public ResponseEntity<List<ClientRepository.ClientProjection>> findByIdClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findByCompanyId(id));
    }
}
