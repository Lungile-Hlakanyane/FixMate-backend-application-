package com.fixmate.backend_application.controller;
import com.fixmate.backend_application.dto.UserRegistrationRequest;
import com.fixmate.backend_application.dto.UserResponse;
import com.fixmate.backend_application.enums.Role;
import com.fixmate.backend_application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRegistrationRequest request){
        return new ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<UserResponse> toggleActive(@PathVariable Long id){
        return ResponseEntity.ok(service.toggleActive(id));
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<UserResponse> verifyPro(@PathVariable Long id){
        return ResponseEntity.ok(service.verifyPro(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body){
        service.login(body.get("email"), body.get("password"));
        return ResponseEntity.ok(Map.of("message","User successfully logged in"));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/total/{role}")
    public ResponseEntity<Long> getTotalByRole(@PathVariable Role role){
        return ResponseEntity.ok(service.countByRole(role));
    }

    @GetMapping("/overallTotal")
    public ResponseEntity<Long> getTotalUsers(){
        return ResponseEntity.ok(service.getTotalUsers());
    }

    @PutMapping("/banUser/{id}")
    public ResponseEntity<UserResponse> banUser(@PathVariable Long id){
        return ResponseEntity.ok(service.banUser(id));
    }

    @PutMapping("/unBanUser/{id}")
    public ResponseEntity<UserResponse> unBanUser(@PathVariable Long id){
        return ResponseEntity.ok(service.unbanUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetailsById(@PathVariable Long id){
        return ResponseEntity.ok(service.getUserDetailsById(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserdetailsByUserId(@PathVariable Long userId,
                                                                  @RequestBody UserRegistrationRequest userRequest){
        return ResponseEntity.ok(service.updateUserDetailsById(userId, userRequest));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponse>> getUsersByRole(@PathVariable Role role){
        return ResponseEntity.ok(service.getUsersByRole(role));
    }
}
