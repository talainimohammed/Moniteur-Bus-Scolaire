package com.monibus.realtimemicroservice.controller;

import com.monibus.realtimemicroservice.dto.LocationDTO;
import com.monibus.realtimemicroservice.service.serviceImp.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/realtimeloc")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(this.locationService.afficherLocationById(id));
    }
    @GetMapping("/bus/{idbus}")
    public ResponseEntity<List<LocationDTO>> getLocationByBus(@PathVariable(value = "idbus") long idbus) {
        return ResponseEntity.ok(this.locationService.afficherLocationsByIdBus(idbus));
    }

    @PostMapping
    public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO) {
        return ResponseEntity.ok(this.locationService.addLocation(locationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> modLocation(@PathVariable(value = "id") long id, @RequestBody LocationDTO locationDTO) {
        return ResponseEntity.ok(this.locationService.modLocation(locationDTO, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delLocation(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(this.locationService.delLocation(id));
    }
}
