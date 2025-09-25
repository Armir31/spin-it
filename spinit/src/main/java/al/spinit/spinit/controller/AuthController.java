package al.spinit.spinit.controller;

import al.spinit.spinit.dto.LogInRequest;
import al.spinit.spinit.dto.SignUpRequest;
import al.spinit.spinit.entity.Role;
import al.spinit.spinit.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup/customer")
    public ResponseEntity<String> signUpCustomer(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.customerRegistration(signUpRequest, Role.CUSTOMER));
    }
    @PostMapping("/signup/admin")
    public ResponseEntity<String> signUpAdmin(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.adminRegistration(signUpRequest, Role.ADMIN));
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LogInRequest logInRequest) {
        return ResponseEntity.ok(authService.loginUser(logInRequest).getToken());
    }
}
