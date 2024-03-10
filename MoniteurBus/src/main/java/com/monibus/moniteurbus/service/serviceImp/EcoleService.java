package com.monibus.moniteurbus.service.serviceImp;

import com.monibus.moniteurbus.dto.EcoleDTO;
import com.monibus.moniteurbus.entity.Ecole;
import com.monibus.moniteurbus.entity.Location;
import com.monibus.moniteurbus.repository.EcoleRepository;
import com.monibus.moniteurbus.service.IEcole;
import com.monibus.moniteurbus.service.ILocation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EcoleService implements IEcole {

    private EcoleRepository ecoleRepository;
    private ILocation ilocation;
    private ModelMapper modelMapper;
    public EcoleService(EcoleRepository ecoleRepository,ModelMapper modelMapper,ILocation ilocation){
        this.ecoleRepository=ecoleRepository;
        this.ilocation=ilocation;
        this.modelMapper=modelMapper;
    }

    @Override
    public EcoleDTO addEcole(EcoleDTO ecoleDTO) {
        if(ecoleDTO == null) throw new NullPointerException();
        Location location=this.modelMapper.map(this.ilocation.afficherLocationById(ecoleDTO.getIdLocation()), Location.class);
        Ecole ecole=this.modelMapper.map(ecoleDTO,Ecole.class);
        ecole.setLocation(location);
        return this.modelMapper.map(ecole,EcoleDTO.class);
    }

    @Override
    public EcoleDTO modEcole(EcoleDTO ecoleDTO) {
        if(ecoleDTO == null) throw new NullPointerException();
        Location location=this.modelMapper.map(this.ilocation.afficherLocationById(ecoleDTO.getIdLocation()), Location.class);
        Ecole ecole=this.modelMapper.map(ecoleDTO,Ecole.class);
        ecole.setLocation(location);
        return this.modelMapper.map(ecole,EcoleDTO.class);
    }

    @Override
    public List<EcoleDTO> afficherEcoles() {
        return this.ecoleRepository.findAll().stream().map(e-> this.modelMapper.map(e,EcoleDTO.class)).toList();
    }

    @Override
    public EcoleDTO afficherEcoleById(long id) {
        Ecole ecole=this.ecoleRepository.findById(id).orElse(null);
        if(ecole==null) throw new NullPointerException();
        return this.modelMapper.map(ecole,EcoleDTO.class);
    }
}
