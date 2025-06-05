package springboot_jwt_demo.controller;

import springboot_jwt_demo.model.AuthRequest;
import springboot_jwt_demo.model.AuthResponse;
import springboot_jwt_demo.util.JwtUtil;
import springboot_jwt_demo.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        try {
            // 1. Authenticate username and password with Spring Security
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // 2. Load user details after successful authentication
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // 3. Generate JWT token
            String token = jwtUtil.generateToken(userDetails.getUsername());

            // 4. Return the token in the response
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            // Wrong username or password
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello! You're authenticated.";
    }
}
