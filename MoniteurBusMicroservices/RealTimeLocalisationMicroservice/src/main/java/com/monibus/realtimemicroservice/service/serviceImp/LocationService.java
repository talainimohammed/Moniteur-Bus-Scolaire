package com.monibus.realtimemicroservice.service.serviceImp;

import com.monibus.realtimemicroservice.dto.LocationDTO;
import com.monibus.realtimemicroservice.entity.Location;
import com.monibus.realtimemicroservice.repository.LocationRepository;
import com.monibus.realtimemicroservice.service.ILocation;
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
        if(this.afficherLocationByIdBus(locationDTO.getIdbus())!=null){
            System.out.println("Location already exist for this bus"+this.afficherLocationByIdBus(locationDTO.getIdbus()).getIdLocation());
            return this.modLocation(locationDTO,this.afficherLocationByIdBus(locationDTO.getIdbus()).getIdLocation());
        }
        else {
            System.out.println("Location added");
            return this.modelMapper.map(this.locationRepository.save(this.modelMapper.map(locationDTO, Location.class)), LocationDTO.class);
        }
    }

    @Override
    public LocationDTO modLocation(LocationDTO locationDTO,long id) {
        if(locationDTO==null)throw new NullPointerException();
        Location location=this.locationRepository.findById(id).orElse(null);
        locationDTO.setIdLocation(id);
        return this.modelMapper.map(this.locationRepository.save(this.modelMapper.map(locationDTO, Location.class)), LocationDTO.class);

    }

    @Override
    public List<LocationDTO> afficherLocations() {
        return this.locationRepository.findAll().stream().map(l->this.modelMapper.map(l, LocationDTO.class)).toList();
    }

    @Override
    public List<LocationDTO> afficherLocationsByIdBus(long idbus) {
        return this.locationRepository.findAllByIdbus(idbus).stream().map(l->this.modelMapper.map(l, LocationDTO.class)).toList();
    }


    @Override
    public LocationDTO afficherLocationById(long id) {
        Location location=this.locationRepository.findById(id).orElse(null);
        if(location==null)throw new NullPointerException();
        return this.modelMapper.map(location, LocationDTO.class);
    }

    @Override
    public LocationDTO afficherLocationByIdBus(long id) {
        Location location=this.locationRepository.findByIdbus(id);
        if(location!=null)return this.modelMapper.map(location, LocationDTO.class);
        return null;
    }

    @Override
    public boolean delLocation(long id) {
        Location location=this.locationRepository.findById(id).orElse(null);
        if(location==null)throw new NullPointerException();
        location.setDeleted(true);
        return this.locationRepository.save(location).isDeleted();
    }
}
