package com.monibus.moniteurbus.service;

import com.monibus.moniteurbus.dto.LocationDTO;
import com.monibus.moniteurbus.entity.Location;

import java.util.List;

public interface ILocation {

    public LocationDTO addLocation(LocationDTO locationDTO);
    public LocationDTO modLocation(LocationDTO locationDTO);
    public List<LocationDTO> afficherLocations();
    public LocationDTO afficherLocationById(long id);
    public boolean delLocation(long id);

}
