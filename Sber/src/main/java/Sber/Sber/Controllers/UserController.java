package Sber.Sber.Controllers;

import Sber.Sber.auth.request.DeleteUserRequest;
import Sber.Sber.auth.request.InviteRequest;
import Sber.Sber.repositories.UserRepository;
import Sber.Sber.auth.service.UserService;
import Sber.Sber.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/byCompany/{companyname}")
    public ResponseEntity<List<UserRepository.UserProjection>> userListByCompany(@PathVariable String companyname) {
        return ResponseEntity.ok(userService.listUserByCompany(companyname));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/company/delete")
    public ResponseEntity<ApiResponse> deleteUser(@Valid @RequestBody DeleteUserRequest deleteUserRequest) {
        userService.deleteUser(deleteUserRequest);
        return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully. ID: " + deleteUserRequest.getId()));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/company/invite")
    public ResponseEntity<ApiResponse> inviteUser(@Valid @RequestBody InviteRequest inviteRequest) {
        userService.inviteUser(inviteRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Invitation sent to " + inviteRequest.getEmail()+ " set company name: " + inviteRequest.getCompanyname() + " successfully"));
    }
}