package com.monibus.realtimemicroservice.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private  long idLocation;
    private double latitude;
    private double longtitude;
    private  long idbus;
    private boolean isDeleted;
}
