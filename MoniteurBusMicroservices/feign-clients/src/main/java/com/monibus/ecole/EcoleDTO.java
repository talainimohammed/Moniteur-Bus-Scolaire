package com.monibus.ecole;

import lombok.Data;

@Data
public class EcoleDTO {
    private long idEcole;
    private String nomEcole;
    private long locationId;
    private String adresse;
    private boolean isDeleted;
}
