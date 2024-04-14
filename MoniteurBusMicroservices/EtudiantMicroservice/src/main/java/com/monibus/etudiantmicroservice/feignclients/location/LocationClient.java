package com.monibus.etudiantmicroservice.feignclients.location;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "LOCATION")
public interface LocationClient {

    @GetMapping("api/v1/location/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable(value = "id") long id);

    @PostMapping("api/v1/location")
    public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO);

    @PutMapping("api/v1/location/{id}")
    public ResponseEntity<LocationDTO> modLocation(@PathVariable(value = "id") long id, @RequestBody LocationDTO locationDTO);
    @DeleteMapping("api/v1/location/{id}")
    public ResponseEntity<Boolean> delLocation(@PathVariable(value = "id") long id);
}
