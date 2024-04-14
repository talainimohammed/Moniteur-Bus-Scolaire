package com.monibus.ecolemicroservice.service.serviceImp;

import com.monibus.ecolemicroservice.dto.EcoleDTO;
import com.monibus.ecolemicroservice.entity.Ecole;
import com.monibus.ecolemicroservice.repository.EcoleRepository;
import com.monibus.ecolemicroservice.service.IEcole;
import com.monibus.ecolemicroservice.feignclients.location.LocationClient;
import com.monibus.ecolemicroservice.feignclients.location.LocationDTO;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EcoleService implements IEcole {

    private final EcoleRepository ecoleRepository;
    private final LocationClient locationClient;
    private final ModelMapper modelMapper;
    @Autowired
    public EcoleService(EcoleRepository ecoleRepository,ModelMapper modelMapper,LocationClient locationClient1){
        this.ecoleRepository=ecoleRepository;
        this.modelMapper=modelMapper;
        this.locationClient=locationClient1;
    }

    @Override
    public EcoleDTO addEcole(EcoleDTO ecoleDTO) {
        if(ecoleDTO == null) throw new NullPointerException();
        LocationDTO locationDTO1=new LocationDTO();
        locationDTO1.setLatitude(ecoleDTO.getLatitude());
        locationDTO1.setLongtitude(ecoleDTO.getLongtitude());
        LocationDTO locationDTO= this.locationClient.addLocation(locationDTO1).getBody();
        if (locationDTO==null) throw new NullPointerException();
        ecoleDTO.setLocationId(locationDTO.getIdLocation());
        Ecole ecole=this.ecoleRepository.save(this.modelMapper.map(ecoleDTO,Ecole.class));
        return this.modelMapper.map(ecole,EcoleDTO.class);
    }

    @Override
    public EcoleDTO modEcole(EcoleDTO ecoleDTO,long id) {
        Ecole ecole=this.ecoleRepository.findById(id).orElse(null);
        if (ecole==null) throw new NotFoundException();
        if(ecoleDTO == null) throw new NullPointerException();
        LocationDTO locationDTO=this.locationClient.getLocation(ecoleDTO.getLocationId()).getBody();
        if(locationDTO==null) throw new NullPointerException();
        if (ecoleDTO.getLatitude()!=locationDTO.getLatitude() || ecoleDTO.getLongtitude()!=locationDTO.getLongtitude()){
            locationDTO.setLatitude(ecoleDTO.getLatitude());
            locationDTO.setLongtitude(ecoleDTO.getLongtitude());
            locationDTO=this.locationClient.modLocation(ecoleDTO.getLocationId(),locationDTO).getBody();
            if(locationDTO==null) throw new NullPointerException();
        }
        ecole.setNomEcole(ecoleDTO.getNomEcole());
        ecole.setAdresse(ecoleDTO.getAdresse());
        ecole.setTelephone(ecoleDTO.getTelephone());
        ecole.setEmail(ecoleDTO.getEmail());
        ecole.setLocationId(locationDTO.getIdLocation());
        EcoleDTO ecoleDTO1=this.modelMapper.map(this.ecoleRepository.save(ecole),EcoleDTO.class);
        ecoleDTO1.setLatitude(locationDTO.getLatitude());
        ecoleDTO1.setLongtitude(locationDTO.getLongtitude());
        return ecoleDTO1;
    }

    @Override
    public List<EcoleDTO> afficherEcoles() {
        return this.ecoleRepository.findAll().stream().map(e-> this.modelMapper.map(e,EcoleDTO.class)).toList();
    }

    @Override
    public EcoleDTO afficherEcoleById(long id) {
        Ecole ecole=this.ecoleRepository.findById(id).orElse(null);
        if(ecole==null) throw new NullPointerException();
        LocationDTO locationDTO=this.locationClient.getLocation(ecole.getLocationId()).getBody();
        if(locationDTO==null) throw new NullPointerException();
        EcoleDTO ecoleDTO=this.modelMapper.map(ecole,EcoleDTO.class);
        ecoleDTO.setLatitude(locationDTO.getLatitude());
        ecoleDTO.setLongtitude(locationDTO.getLongtitude());
        return ecoleDTO;
    }
}
