package com.monibus.utilisateurmicroservice.controller;

import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;
import com.monibus.utilisateurmicroservice.service.IUtilisateur;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
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
    @GetMapping("/role/{role}/ecole/{id}")
    public ResponseEntity<List<UtilisateurDTO>> tousUtilisateursparRole(@PathVariable(value = "role") int role,@PathVariable(value = "id") long id){
        List<UtilisateurDTO> utilisateurDTOS=this.iUtilisateur.afficherUtilisateurByRole(role,id);
        return new ResponseEntity<>(utilisateurDTOS, HttpStatus.OK);
    }

   /* @GetMapping("/ecole/{id}")
    public ResponseEntity<List<UtilisateurDTO>> tousUtilisateursparEcole(@PathVariable(value = "id") long id){
        List<UtilisateurDTO> utilisateurDTOS=this.iUtilisateur.afficherUtilisateursByEcoleId(id);
        return new ResponseEntity<>(utilisateurDTOS, HttpStatus.OK);
    }*/
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
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> modUtilisateur(@RequestBody @Valid UtilisateurDTO utilisateurDTO,@PathVariable(value = "id") long id){
        UtilisateurDTO utilisateurDTO1=this.iUtilisateur.modUtilisateur(utilisateurDTO,id);
        return new ResponseEntity<>(utilisateurDTO1, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean>> delUtilisateur(@PathVariable(value = "id") long id){
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",this.iUtilisateur.delUtilisateur(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
