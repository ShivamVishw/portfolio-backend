package com.shivam.portfolio.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shivam.portfolio.dto.LoginRequest;
import com.shivam.portfolio.dto.LoginResponse;
import com.shivam.portfolio.entity.Admin;
import com.shivam.portfolio.repository.AdminRepository;
import com.shivam.portfolio.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	

    private final AdminRepository adminRepository;
    
    private final JwtUtil jwtUtil;
    
    public AuthController(
            AdminRepository adminRepository,
            JwtUtil jwtUtil) {

        this.adminRepository = adminRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        Optional<Admin> admin =
                adminRepository.findByEmail(request.getEmail());

        if (admin.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid Email");
        }

        if (!admin.get().getPassword()
                .equals(request.getPassword())) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid Password");
        }

        String token =
                jwtUtil.generateToken(
                        admin.get().getEmail()
                );

        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}