package com.monibus.ecolemicroservice.feignclients.bus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BusDTO {
    private long idBus;
    @NotNull(message = "Matricule null")
    private String matricule;
    @NotNull(message = "Chauffeur null")
    private long idChauffeur;
    @NotNull(message = "Location null")
    private long idLocation;
    @NotNull(message = "Ecole null")
    private long idEcole;
    private boolean Deleted;
}
