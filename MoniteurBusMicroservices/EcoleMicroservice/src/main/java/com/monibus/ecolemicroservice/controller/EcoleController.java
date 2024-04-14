package com.monibus.ecolemicroservice.controller;

import com.monibus.ecolemicroservice.dto.EcoleDTO;
import com.monibus.ecolemicroservice.service.IEcole;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value ="/api/v1/ecole", produces = "application/json")
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

    @PutMapping("{id}")
    public ResponseEntity<EcoleDTO> modEcole(@RequestBody @Valid EcoleDTO ecoleDTO, @PathVariable(value = "id") long id){
        EcoleDTO ecoleDTO1=this.iEcole.modEcole(ecoleDTO,id);
        return new ResponseEntity<>(ecoleDTO1, HttpStatus.OK);
    }


}
