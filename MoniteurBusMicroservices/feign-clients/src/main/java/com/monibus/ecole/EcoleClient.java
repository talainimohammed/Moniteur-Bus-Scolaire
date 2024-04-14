package com.monibus.ecole;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ECOLE")
public interface EcoleClient {

    @GetMapping("/api/v1/ecole")
    public ResponseEntity<List<EcoleDTO>> tousEcoles();

    @GetMapping("/api/v1/ecole/{id}")
    public ResponseEntity<EcoleDTO> getEcole(@PathVariable(value = "id") long id);

    @PostMapping("/api/v1/ecole")
    public ResponseEntity<EcoleDTO> ajouterEcole(@RequestBody @Valid EcoleDTO ecoleDTO);

    @PutMapping("/api/v1/ecole")
    public ResponseEntity<EcoleDTO> modEcole(@RequestBody @Valid EcoleDTO ecoleDTO);
}
