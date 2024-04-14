package com.monibus.busmicroservice.service.serviceImp;

import com.monibus.busmicroservice.dto.BusDTO;
import com.monibus.busmicroservice.entity.Bus;
import com.monibus.busmicroservice.repository.BusRepository;
import com.monibus.busmicroservice.service.IBus;
import com.monibus.busmicroservice.feignclients.ecole.EcoleClient;
import com.monibus.busmicroservice.feignclients.ecole.EcoleDTO;
import com.monibus.busmicroservice.feignclients.location.LocationClient;
import com.monibus.busmicroservice.feignclients.utilisateur.UtilisateurClient;
import com.monibus.busmicroservice.feignclients.utilisateur.UtilisateurDTO;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusService implements IBus {

    private BusRepository busRepository;
    private final LocationClient locationClient;
    private final EcoleClient ecoleClient;
    private final UtilisateurClient utilisateurClient;
    private ModelMapper modelMapper;

    public BusService(BusRepository busRepository, LocationClient locationClient, EcoleClient ecoleClient, UtilisateurClient utilisateurClient, ModelMapper modelMapper) {
        this.busRepository=busRepository;
        this.locationClient = locationClient;
        this.ecoleClient = ecoleClient;
        this.utilisateurClient = utilisateurClient;
        this.modelMapper=modelMapper;
    }

    @Override
    public BusDTO addBus(BusDTO busDTO) {
        if (busDTO==null)throw new NullPointerException();
        //UtilisateurDTO utilisateurDTO=utilisateurClient.getUtilisateur(busDTO.getIdchauffeur()).getBody();
        //if (utilisateurDTO==null)throw new NotFoundException("Chauffeur not found");
        EcoleDTO ecoleDTO=ecoleClient.getEcole(busDTO.getIdecole()).getBody();
        if (ecoleDTO==null)throw new NotFoundException("Ecole not found");
        Bus bus=modelMapper.map(busDTO, Bus.class);
        return this.modelMapper.map(busRepository.save(bus),BusDTO.class);
    }

    @Override
    public BusDTO modBus(BusDTO busDTO, long id) {
        if (busDTO==null)throw new NullPointerException();
        Bus bus=busRepository.findById(id).orElse(null);
        if(bus == null)throw new NotFoundException("Bus not found");
        if(busDTO.getIdchauffeur()!=0){
            UtilisateurDTO utilisateurDTO=utilisateurClient.getUtilisateur(busDTO.getIdchauffeur()).getBody();
            if (utilisateurDTO==null)throw new NotFoundException("Chauffeur not found");
        }
        EcoleDTO ecoleDTO=ecoleClient.getEcole(busDTO.getIdecole()).getBody();
        if (ecoleDTO==null)throw new NotFoundException("Ecole not found");
        Bus bus1=modelMapper.map(busDTO, Bus.class);
        bus1.setIdBus(id);
        return this.modelMapper.map(busRepository.save(bus1),BusDTO.class);
    }

    @Override
    public List<BusDTO> afficherBuses(long idEcole) {
        List<Bus> buses=busRepository.findAllByIdecoleAndDeletedFalse(idEcole);
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
    public BusDTO afficherBusbyChauffeur(long id) {
        Bus bus=busRepository.findByIdchauffeur(id);
        if(bus!=null){
            return modelMapper.map(bus, BusDTO.class);
        }
        return null;
    }

    @Override
    public boolean delBus(long id) {
        Bus bus=busRepository.findById(id).orElse(null);
        if(bus == null)throw new NullPointerException();
        bus.setDeleted(true);
        return this.busRepository.save(bus).isDeleted();
    }
}
