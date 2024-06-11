package Sber.Sber.Controllers;

import Sber.Sber.auth.request.CreateRealtyRequest;
import Sber.Sber.auth.request.ImageRequest;
import Sber.Sber.auth.request.SellRealtyRequest;
import Sber.Sber.auth.service.RealtyService;
import Sber.Sber.models.Image;
import Sber.Sber.models.Realty;
import Sber.Sber.repositories.ImageRepository;
import Sber.Sber.repositories.RealtyRepository;
import Sber.Sber.common.ApiResponse;
import Sber.Sber.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")


public class RealtyController {

    private final RealtyRepository realtyRepository;
    private final RealtyService realtyService;
    private final ImageRepository imageRepository;

//    @CrossOrigin(origins = "http://localhost:3000")
//    @PostMapping("/realtycreate")
//    public ResponseEntity<String> createRealty(@Valid @RequestBody CreateRealtyRequest createRealtyRequest){
//        realtyService.createRealty(createRealtyRequest);
//        return ResponseEntity.ok(new ApiResponse(true, "Invitation sent to " + createRealtyRequest.getCompanyname()+ " set company name: " + createRealtyRequest.getRealtyname()) + " successfully");
//    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/realtycreate", consumes = "multipart/form-data")
    public ResponseEntity<String> createRealty(@ModelAttribute CreateRealtyRequest createRealtyRequest, @RequestParam("images") List<MultipartFile> images) throws IOException {
        realtyService.createRealty(createRealtyRequest, images);
        return ResponseEntity.ok(new ApiResponse(true, "Invitation sent to " + createRealtyRequest.getCompanyname() + " set company name: " + createRealtyRequest.getRealtyname()) + " successfully");
    }

//    @CrossOrigin(origins = "http://localhost:3000")
//    @PostMapping(value = "/sellrealty", consumes = "multipart/form-data")
//    public ResponseEntity<String> sellRealty(@ModelAttribute SellRealtyRequest sellRealtyRequest){
//        realtyService.sellRealty(sellRealtyRequest);
//        return ResponseEntity.ok(new ApiResponse(true, "Invitation sent to " + sellRealtyRequest.getId_realty() + " set company name: " + sellRealtyRequest.getId_client()) + " successfully");
//    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/realtys/{companyname}")
    public ResponseEntity<List<RealtyRepository.RealtyProjection>> f(@PathVariable("companyname") String companyname) {
        return ResponseEntity.ok(realtyService.listRealtyCompany(companyname));
    }
}
