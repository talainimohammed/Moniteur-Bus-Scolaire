package com.monibus.ecolemicroservice.feignclients.location;

import lombok.Data;

@Data
public class LocationDTO {
    private  long idLocation;
    private double latitude;
    private double longtitude;
    private boolean isDeleted;
}
