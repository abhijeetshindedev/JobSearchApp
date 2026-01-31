package com.abdev.jobsearch.user_service.Controller;

import com.abdev.jobsearch.user_service.DTO.AuthResponse;
import com.abdev.jobsearch.user_service.DTO.GoogleLoginRequest;
import com.abdev.jobsearch.user_service.DTO.LoginRequest;
import com.abdev.jobsearch.user_service.DTO.RegisterRequest;
import com.abdev.jobsearch.user_service.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {
        System.out.println("In Login API");
        return ResponseEntity.ok(authService.login(request));
    }

    // @PostMapping("/google")
    // public ResponseEntity<AuthResponse> googleLogin(
    //         @RequestBody GoogleLoginRequest request) {

    //     return ResponseEntity.ok(
    //             authService.googleLogin(request.getIdToken())
    //     );
    // }

}
