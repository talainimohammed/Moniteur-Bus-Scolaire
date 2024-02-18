package com.monibus.moniteurbus.dto;

import lombok.Data;

@Data
public class BusDTO {
    private long idBus;
    private String matricule;
    private long idChauffeur;
    private long idLocation;
    private long idEcole;
    private boolean isDeleted;
}
