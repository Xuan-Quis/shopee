package com.actvn.Shopee_BE.controller;

import com.actvn.Shopee_BE.dto.request.LoginRequest;
import com.actvn.Shopee_BE.dto.response.LoginResponse;
import com.actvn.Shopee_BE.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class SpringSecurityDemoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String sayUser() {
        return "Hello user";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String sayAdmin() {
        return "Hello admin";
    }
    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info("Signin application", loginRequest);
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
                            , loginRequest.getPassword())
            );
        }catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message","Bad credentials");
            map.put("status", false);
            return ResponseEntity.
            status(HttpStatus.NOT_FOUND)
            .body(map);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJWTTokenFromUsername(userDetails);
        log.info("token generate: {}", jwtToken);
        List<String> roles = userDetails.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority)
                .toList();
        LoginResponse response = new LoginResponse(userDetails.getUsername(),
                jwtToken, roles);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
