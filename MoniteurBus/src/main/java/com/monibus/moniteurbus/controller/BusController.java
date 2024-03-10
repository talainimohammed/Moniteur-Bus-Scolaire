package com.monibus.moniteurbus.controller;

import com.monibus.moniteurbus.dto.BusDTO;
import com.monibus.moniteurbus.service.IBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/v1/bus", produces = "application/json")
public class BusController {

    private IBus ibus;

    public BusController(IBus bus){
        this.ibus=bus;
    }

    @GetMapping
    public ResponseEntity<List<BusDTO>> tousBus(){
        List<BusDTO> busDTOS=this.ibus.afficherBuses();
        return new ResponseEntity<>(busDTOS, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<BusDTO> getBus(@PathVariable(value = "id") long id){
        BusDTO busDTO=this.ibus.afficherBus(id);
        return new ResponseEntity<>(busDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO){
        BusDTO busDTO1=this.ibus.addBus(busDTO);
        return new ResponseEntity<>(busDTO1, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<BusDTO> modBus(@RequestBody BusDTO busDTO){
        BusDTO busDTO1=this.ibus.addBus(busDTO);
        return new ResponseEntity<>(busDTO1, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Map<String,Boolean>> delBus(@PathVariable(value = "id") long id){
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",this.ibus.delBus(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
