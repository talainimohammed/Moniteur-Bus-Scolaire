package com.monibus.etudiantmicroservice.feignclients.etudiant;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ETUDIANT")
public interface Etudiantclient {

    @GetMapping("/api/v1/etudiant")
    public ResponseEntity<List<EtudiantDTO>> tousEtudiants();

    @GetMapping("api/v1/etudiant/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiant(@PathVariable(value = "id") long id);

    @PostMapping("/api/v1/etudiant")
    public ResponseEntity<EtudiantDTO> addEtudiant(@RequestBody @Valid EtudiantDTO etudiantDTO);
    @PutMapping("/api/v1/etudiant")
    public ResponseEntity<EtudiantDTO> modEtudiant(@RequestBody @Valid EtudiantDTO etudiantDTO);
    @DeleteMapping("/api/v1/etudiant/{id}")
    public ResponseEntity<Map<String,Boolean>> delEtudiant(@PathVariable(value = "id") long id);
}
