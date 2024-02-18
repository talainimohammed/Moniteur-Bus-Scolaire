package com.monibus.moniteurbus.dto;

import com.monibus.moniteurbus.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EcoleDTO {
    private long idEcole;
    private String nomEcole;
    private long idLocation;
    private String adresse;
    private boolean isDeleted;
}
