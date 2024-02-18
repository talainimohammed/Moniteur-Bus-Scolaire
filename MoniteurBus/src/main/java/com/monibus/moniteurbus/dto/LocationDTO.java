package com.monibus.moniteurbus.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LocationDTO {
    private  long idLocation;
    private double latitude;
    private double longtitude;
    private boolean isDeleted;
}
