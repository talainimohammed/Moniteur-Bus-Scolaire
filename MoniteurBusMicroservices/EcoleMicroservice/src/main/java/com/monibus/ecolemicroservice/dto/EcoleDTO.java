package com.monibus.ecolemicroservice.dto;

import lombok.Data;

@Data
public class EcoleDTO {
    private long idEcole;
    private String nomEcole;
    private String adresse;
    private String email;
    private String telephone;
    private long locationId;
    private double latitude;
    private double longtitude;
    private boolean isDeleted;
}
