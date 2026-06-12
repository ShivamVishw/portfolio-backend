package com.shivam.portfolio.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shivam.portfolio.dto.LoginRequest;
import com.shivam.portfolio.dto.LoginResponse;
import com.shivam.portfolio.repository.AdminRepository;
import com.shivam.portfolio.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;
	
    
    private final JwtUtil jwtUtil;
    
    public AuthController(
            JwtUtil jwtUtil) {

 
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

//        Optional<Admin> admin =
//                adminRepository.findByEmail(request.getEmail());
//
//        if (admin.isEmpty()) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Invalid Email");
//        }
//
//        if (!admin.get().getPassword()
//                .equals(request.getPassword())) {
//
//            return ResponseEntity
//                    .badRequest()
//                    .body("Invalid Password");
//        }
    	
    	if (!request.getEmail().equals(adminEmail)) {

    	    return ResponseEntity
    	            .badRequest()
    	            .body("Invalid Email");
    	}

    	if (!request.getPassword().equals(adminPassword)) {

    	    return ResponseEntity
    	            .badRequest()
    	            .body("Invalid Password");
    	}

    	String token =
    	        jwtUtil.generateToken(adminEmail);

        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}