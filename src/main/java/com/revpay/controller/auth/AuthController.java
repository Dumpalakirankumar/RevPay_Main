package com.revpay.controller.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.revpay.dto.request.LoginRequest;
import com.revpay.dto.request.RegisterRequest;
import com.revpay.dto.response.ApiResponse;
import com.revpay.security.JwtUtil;
import com.revpay.security.CustomUserDetails;
import com.revpay.service.interfaces.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return new ApiResponse<>(true, "User Registered Successfully", null);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // ⭐ Get authenticated user (contains role)
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // ⭐ Generate token using authenticated username
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new ApiResponse<>(
                true,
                "Login successful",
                Map.of(
                        "token", token,
                        "type", "Bearer"
                )
        );
    }
}