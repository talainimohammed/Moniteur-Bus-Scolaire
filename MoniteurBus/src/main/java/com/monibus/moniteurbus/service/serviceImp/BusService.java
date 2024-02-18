package com.monibus.moniteurbus.service.serviceImp;

import com.monibus.moniteurbus.dto.BusDTO;
import com.monibus.moniteurbus.dto.UtilisateurDTO;
import com.monibus.moniteurbus.entity.Bus;
import com.monibus.moniteurbus.entity.Ecole;
import com.monibus.moniteurbus.entity.Utilisateur;
import com.monibus.moniteurbus.repository.BusRepository;
import com.monibus.moniteurbus.service.IBus;
import org.modelmapper.ModelMapper;

import java.util.List;

public class BusService implements IBus {

    private BusRepository busRepository;
    private UtilisateurService  utilisateurService;
    private EcoleService ecoleService;
    private ModelMapper modelMapper;
    public BusService(BusRepository busRepository,EcoleService ecoleService,UtilisateurService utilisateurService, ModelMapper modelMapper) {
        this.busRepository=busRepository;
        this.utilisateurService=utilisateurService;
        this.ecoleService=ecoleService;
        this.modelMapper=modelMapper;
    }

    @Override
    public BusDTO addBus(BusDTO busDTO) {
        if (busDTO==null)throw new NullPointerException();
        Utilisateur utilisateur=modelMapper.map(utilisateurService.afficherUtilisateurById(busDTO.getIdChauffeur()),Utilisateur.class);
        Ecole ecole=modelMapper.map(ecoleService.afficherEcoleById(busDTO.getIdEcole()), Ecole.class);
        Bus bus=modelMapper.map(busDTO, Bus.class);
        bus.setChauffeur(utilisateur);
        bus.setEcole(ecole);
        return this.modelMapper.map(busRepository.save(bus),BusDTO.class);
    }




    @Override
    public BusDTO modBus(BusDTO busDTO) {
        if (busDTO==null)throw new NullPointerException();
        Utilisateur utilisateur=modelMapper.map(utilisateurService.afficherUtilisateurById(busDTO.getIdChauffeur()),Utilisateur.class);
        Ecole ecole=modelMapper.map(ecoleService.afficherEcoleById(busDTO.getIdEcole()), Ecole.class);
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
        Bus bus=busRepository.findById(id).get();
        return modelMapper.map(bus, BusDTO.class);
    }

    @Override
    public boolean delBus(long id) {
        return false;
    }
}
