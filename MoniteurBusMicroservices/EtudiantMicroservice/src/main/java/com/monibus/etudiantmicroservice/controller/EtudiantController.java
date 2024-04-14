package com.monibus.etudiantmicroservice.controller;

import com.monibus.etudiantmicroservice.dto.EtudiantDTO;
import com.monibus.etudiantmicroservice.service.IEtudiant;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value ="/api/v1/etudiant", produces = "application/json")
public class EtudiantController {

    private IEtudiant iEtudiant;

    public  EtudiantController(IEtudiant iEtudiant){
        this.iEtudiant=iEtudiant;
    }

    @GetMapping
    public ResponseEntity<List<EtudiantDTO>> tousEtudiants(){
        List<EtudiantDTO> etudiantDTOS=this.iEtudiant.afficherEtudiants();
        return new ResponseEntity<>(etudiantDTOS, HttpStatus.OK);
    }
    @GetMapping("/ecole/{idEcole}")
    public ResponseEntity<List<EtudiantDTO>> tousEtudiantsByEcole(@PathVariable(value = "idEcole") long idEcole){
        List<EtudiantDTO> etudiantDTOS=this.iEtudiant.afficherEtudiantsByEcoleId(idEcole);
        return new ResponseEntity<>(etudiantDTOS, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<EtudiantDTO> getEtudiant(@PathVariable(value = "id") long id){
        EtudiantDTO etudiantDTO=this.iEtudiant.afficherEtudiantById(id);
        return new ResponseEntity<>(etudiantDTO, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EtudiantDTO> getEtudiantByEmail(@PathVariable(value = "email") String email){
        EtudiantDTO etudiantDTO=this.iEtudiant.afficherEtudiantByEmail(email);
        return new ResponseEntity<>(etudiantDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<EtudiantDTO> addEtudiant(@RequestBody @Valid EtudiantDTO etudiantDTO){
        EtudiantDTO etudiantDTO1=this.iEtudiant.addEtudiant(etudiantDTO);
        return new ResponseEntity<>(etudiantDTO1, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<EtudiantDTO> modEtudiant(@RequestBody @Valid EtudiantDTO etudiantDTO,@PathVariable(value = "id") long id){
        EtudiantDTO etudiantDTO1=this.iEtudiant.modEtudiant(etudiantDTO,id);
        return new ResponseEntity<>(etudiantDTO1, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean>> delEtudiant(@PathVariable(value = "id") long id){
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",this.iEtudiant.delEtudiant(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
