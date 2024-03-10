package com.monibus.locationmicroservice.service.serviceImp;

import com.monibus.locationmicroservice.dto.LocationDTO;
import com.monibus.locationmicroservice.entity.Location;
import com.monibus.locationmicroservice.repository.LocationRepository;
import com.monibus.locationmicroservice.service.ILocation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocation {

    private LocationRepository locationRepository;
    private ModelMapper modelMapper;
    public LocationService(LocationRepository locationRepository,ModelMapper modelMapper){
        this.locationRepository=locationRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public LocationDTO addLocation(LocationDTO locationDTO) {
        if(locationDTO==null)throw new NullPointerException();
        return this.modelMapper.map(this.locationRepository.save(this.modelMapper.map(locationDTO, Location.class)), LocationDTO.class);
    }

    @Override
    public LocationDTO modLocation(LocationDTO locationDTO,long id) {
        if(locationDTO==null)throw new NullPointerException();
        return this.modelMapper.map(this.locationRepository.save(this.modelMapper.map(locationDTO, Location.class)), LocationDTO.class);

    }

    @Override
    public List<LocationDTO> afficherLocations() {
        return this.locationRepository.findAll().stream().map(l->this.modelMapper.map(l, LocationDTO.class)).toList();
    }

    @Override
    public LocationDTO afficherLocationById(long id) {
        Location location=this.locationRepository.findById(id).orElse(null);
        if(location==null)throw new NullPointerException();
        return this.modelMapper.map(location, LocationDTO.class);
    }

    @Override
    public boolean delLocation(long id) {
        Location location=this.locationRepository.findById(id).orElse(null);
        if(location==null)throw new NullPointerException();
        location.setDeleted(true);
        return this.locationRepository.save(location).isDeleted();
    }
}
