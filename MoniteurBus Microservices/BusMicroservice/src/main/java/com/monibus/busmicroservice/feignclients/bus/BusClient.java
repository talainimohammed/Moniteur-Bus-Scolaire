package com.monibus.busmicroservice.feignclients.bus;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("BUS")
public interface BusClient {
    @GetMapping("/api/v1/bus")
    public ResponseEntity<List<BusDTO>> tousBus();
    @GetMapping("/api/v1/bus/{id}")
    public ResponseEntity<BusDTO> getBus(@PathVariable(value = "id") long id);
    @PostMapping("/api/v1/bus")
    public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO);
    @PutMapping("/api/v1/bus")
    public ResponseEntity<BusDTO> modBus(@RequestBody BusDTO busDTO);
    @DeleteMapping("/api/v1/bus/{id}")
    public ResponseEntity<Map<String,Boolean>> delBus(@PathVariable(value = "id") long id);
}
