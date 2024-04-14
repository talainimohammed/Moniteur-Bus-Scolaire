package com.monibus.realtimemicroservice.service;

import com.monibus.realtimemicroservice.dto.LocationDTO;

import java.util.List;

public interface ILocation {

    public LocationDTO addLocation(LocationDTO locationDTO);
    public LocationDTO modLocation(LocationDTO locationDTO,long id);
    public List<LocationDTO> afficherLocations();
    public List<LocationDTO> afficherLocationsByIdBus(long idbus);
    public LocationDTO afficherLocationById(long id);
    public LocationDTO afficherLocationByIdBus(long id);

    public boolean delLocation(long id);

}
