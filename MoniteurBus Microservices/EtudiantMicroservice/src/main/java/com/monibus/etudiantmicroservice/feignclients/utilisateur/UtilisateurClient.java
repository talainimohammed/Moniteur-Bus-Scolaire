package com.monibus.etudiantmicroservice.feignclients.utilisateur;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "UTILISATEUR")
public interface UtilisateurClient {

    @GetMapping("/api/v1/utilisateur")
    public ResponseEntity<List<UtilisateurDTO>> tousUtilisateurs();
    @GetMapping("/api/v1/utilisateur/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable(value = "id") long id);

    @PostMapping("/api/v1/utilisateur")
    public ResponseEntity<UtilisateurDTO> saveUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO);
    @PutMapping("/api/v1/utilisateur")
    public ResponseEntity<UtilisateurDTO> modUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO);
    @DeleteMapping("/api/v1/utilisateur/{id}")
    public ResponseEntity<Map<String,Boolean>> delUtilisateur(@PathVariable(value = "id") long id);
}
