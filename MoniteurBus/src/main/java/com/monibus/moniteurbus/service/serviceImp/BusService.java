package com.monibus.moniteurbus.service.serviceImp;

import com.monibus.moniteurbus.dto.BusDTO;
import com.monibus.moniteurbus.entity.Bus;
import com.monibus.moniteurbus.entity.Ecole;
import com.monibus.moniteurbus.entity.Utilisateur;
import com.monibus.moniteurbus.repository.BusRepository;
import com.monibus.moniteurbus.service.IBus;
import com.monibus.moniteurbus.service.IEcole;
import com.monibus.moniteurbus.service.IUtilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusService implements IBus {

    private BusRepository busRepository;
    private IUtilisateur iUtilisateur;
    private IEcole iEcole;
    private ModelMapper modelMapper;
    public BusService(BusRepository busRepository, IUtilisateur iUtilisateur, IEcole iEcole, ModelMapper modelMapper) {
        this.busRepository=busRepository;
        this.iUtilisateur=iUtilisateur;
        this.iEcole=iEcole;
        this.modelMapper=modelMapper;
    }

    @Override
    public BusDTO addBus(BusDTO busDTO) {
        if (busDTO==null)throw new NullPointerException();
        Utilisateur utilisateur=modelMapper.map(iUtilisateur.afficherUtilisateurById(busDTO.getIdChauffeur()),Utilisateur.class);
        Ecole ecole=modelMapper.map(iEcole.afficherEcoleById(busDTO.getIdEcole()), Ecole.class);
        Bus bus=modelMapper.map(busDTO, Bus.class);
        bus.setChauffeur(utilisateur);
        bus.setEcole(ecole);
        return this.modelMapper.map(busRepository.save(bus),BusDTO.class);
    }

    @Override
    public BusDTO modBus(BusDTO busDTO) {
        if (busDTO==null)throw new NullPointerException();
        Utilisateur utilisateur=modelMapper.map(iUtilisateur.afficherUtilisateurById(busDTO.getIdChauffeur()),Utilisateur.class);
        Ecole ecole=modelMapper.map(iEcole.afficherEcoleById(busDTO.getIdEcole()), Ecole.class);
        Bus bus=modelMapper.map(busDTO, Bus.class);
        bus.setChauffeur(utilisateur);
        bus.setEcole(ecole);
        return this.modelMapper.map(busRepository.save(bus),BusDTO.class);
    }

    @Override
    public List<BusDTO> afficherBuses() {
        List<Bus> buses=busRepository.findAll();
        List<BusDTO> busDTOS=buses.stream().map(b->modelMapper.map(b,BusDTO.class)).toList();
        return busDTOS;
    }

    @Override
    public BusDTO afficherBus(long id) {
        Bus bus=busRepository.findById(id).orElse(null);
        if(bus == null)throw new NullPointerException();
        return modelMapper.map(bus, BusDTO.class);
    }

    @Override
    public boolean delBus(long id) {
        Bus bus=busRepository.findById(id).orElse(null);
        if(bus == null)throw new NullPointerException();
        bus.setDeleted(true);
        return this.busRepository.save(bus).isDeleted();
    }
}
