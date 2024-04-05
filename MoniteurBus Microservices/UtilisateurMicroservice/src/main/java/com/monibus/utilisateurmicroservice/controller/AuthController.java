package com.monibus.utilisateurmicroservice.controller;

import com.monibus.utilisateurmicroservice.dto.LoginDto;
import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;
import com.monibus.utilisateurmicroservice.security.CustomUserDetails;
import com.monibus.utilisateurmicroservice.service.IUtilisateur;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/utilisateur/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final IUtilisateur userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public String getToken(@RequestBody LoginDto LoginDto)
    {
        log.info("Authenticating user {} ", LoginDto.getEmail());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(LoginDto.getEmail(), LoginDto.getPassword()));
        log.info("Authenticated user {} ", authenticate);
        if (authenticate.isAuthenticated())
        {
            log.info("Generating token for user {} ", LoginDto.getEmail());
            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
            Long idUser = userDetails.getId();
            String email = userDetails.getEmail();
            String role = userDetails.getRole();
            return userService.generateToken(idUser,email, role);

        } else {
            throw new RuntimeException("invalid access !");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<UtilisateurDTO> createUser(@RequestBody UtilisateurDTO utilisateurDTO)
    {
        UtilisateurDTO utilisateurDTO1=this.userService.addUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(utilisateurDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token)
    {
        userService.validateToken(token);
        return "Token is valid";
    }
}
