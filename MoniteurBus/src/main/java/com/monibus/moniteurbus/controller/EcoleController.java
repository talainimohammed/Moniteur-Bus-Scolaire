package com.monibus.moniteurbus.controller;

import com.monibus.moniteurbus.dto.EcoleDTO;
import com.monibus.moniteurbus.service.IEcole;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/v1/etudiant", produces = "application/json")
public class EcoleController {

    private IEcole iEcole;

    public  EcoleController(IEcole iEcole){
        this.iEcole=iEcole;
    }

    @GetMapping
    public ResponseEntity<List<EcoleDTO>> tousEcoles(){
        List<EcoleDTO> ecoleDTOS=this.iEcole.afficherEcoles();
        return new ResponseEntity<>(ecoleDTOS, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EcoleDTO> getEcole(@PathVariable(value = "id") long id){
        EcoleDTO ecoleDTO=this.iEcole.afficherEcoleById(id);
        return new ResponseEntity<>(ecoleDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EcoleDTO> ajouterEcole(@RequestBody @Valid EcoleDTO ecoleDTO){
        EcoleDTO ecoleDTO1=this.iEcole.addEcole(ecoleDTO);
        return new ResponseEntity<>(ecoleDTO1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EcoleDTO> modEcole(@RequestBody @Valid EcoleDTO ecoleDTO){
        EcoleDTO ecoleDTO1=this.iEcole.modEcole(ecoleDTO);
        return new ResponseEntity<>(ecoleDTO1, HttpStatus.OK);
    }


}
