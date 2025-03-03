package com.zacharia.applicatiofybe.controller;

import com.zacharia.applicatiofybe.dto.JwtResponseDTO;
import com.zacharia.applicatiofybe.dto.LoginRequestDTO;
import com.zacharia.applicatiofybe.dto.SignUpRequestDTO;
import com.zacharia.applicatiofybe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        JwtResponseDTO jwtResponseDTO = authService.authenticateUser(loginRequestDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @PostMapping("/signup")
    private ResponseEntity<?> registerUser(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        JwtResponseDTO jwtResponseDTO = authService.registerUser(signUpRequestDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }
}
