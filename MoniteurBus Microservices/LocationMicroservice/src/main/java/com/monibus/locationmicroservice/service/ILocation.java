package com.monibus.locationmicroservice.service;

import com.monibus.locationmicroservice.dto.LocationDTO;

import java.util.List;

public interface ILocation {

    public LocationDTO addLocation(LocationDTO locationDTO);
    public LocationDTO modLocation(LocationDTO locationDTO,long id);
    public List<LocationDTO> afficherLocations();
    public LocationDTO afficherLocationById(long id);
    public boolean delLocation(long id);

}
