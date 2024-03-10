package com.monibus.moniteurbus.controller;

import com.monibus.moniteurbus.dto.UtilisateurDTO;
import com.monibus.moniteurbus.service.IUtilisateur;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/v1/utilisateur", produces = "application/json")
public class UtilisateurContoller {

    private IUtilisateur iUtilisateur;

    public UtilisateurContoller (IUtilisateur iUtilisateur){
        this.iUtilisateur=iUtilisateur;
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> tousUtilisateurs(){
        List<UtilisateurDTO> utilisateurDTOS=this.iUtilisateur.afficherUtilisateurs();
        return new ResponseEntity<>(utilisateurDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable(value = "id") long id){
        UtilisateurDTO utilisateurDTO=this.iUtilisateur.afficherUtilisateurById(id);
        return new ResponseEntity<>(utilisateurDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> saveUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO){
        UtilisateurDTO utilisateurDTO1=this.iUtilisateur.addUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(utilisateurDTO1, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<UtilisateurDTO> modUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO){
        UtilisateurDTO utilisateurDTO1=this.iUtilisateur.modUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(utilisateurDTO1, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean>> delUtilisateur(@PathVariable(value = "id") long id){
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",this.iUtilisateur.delUtilisateur(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
