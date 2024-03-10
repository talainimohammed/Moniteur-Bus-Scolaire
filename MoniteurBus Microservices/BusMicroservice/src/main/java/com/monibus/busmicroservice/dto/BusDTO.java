package com.monibus.busmicroservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BusDTO {
    private long idBus;
    @NotNull(message = "Matricule null")
    private String matricule;
    @NotNull(message = "nb places null")
    private long nbplaces;
    @NotNull(message = "Chauffeur null")
    private long idchauffeur;
    //@NotNull(message = "Location null")
    //private long idlocation;
    @NotNull(message = "Ecole null")
    private long idecole;
    private boolean deleted;
}
