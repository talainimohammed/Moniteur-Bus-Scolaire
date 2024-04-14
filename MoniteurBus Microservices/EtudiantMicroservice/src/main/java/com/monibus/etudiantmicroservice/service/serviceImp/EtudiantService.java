package com.monibus.etudiantmicroservice.service.serviceImp;

import com.monibus.etudiantmicroservice.dto.EtudiantDTO;
import com.monibus.etudiantmicroservice.entity.Etudiant;
import com.monibus.etudiantmicroservice.repository.EtudiantRepository;
import com.monibus.etudiantmicroservice.service.IEtudiant;
import com.monibus.etudiantmicroservice.feignclients.location.LocationClient;
import com.monibus.etudiantmicroservice.feignclients.location.LocationDTO;
import com.monibus.etudiantmicroservice.feignclients.utilisateur.RoleEnum;
import com.monibus.etudiantmicroservice.feignclients.utilisateur.UtilisateurClient;
import com.monibus.etudiantmicroservice.feignclients.utilisateur.UtilisateurDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EtudiantService implements IEtudiant {

    private final LocationClient locationClient;
    private final UtilisateurClient utilisateurClient;


    private EtudiantRepository etudiantRepository;
    private ModelMapper modelMapper;
    public EtudiantService(EtudiantRepository etudiantRepository, ModelMapper modelMapper, LocationClient locationClient, UtilisateurClient utilisateurClient){
        this.etudiantRepository=etudiantRepository;
        this.modelMapper=modelMapper;
        this.locationClient=locationClient;
        this.utilisateurClient = utilisateurClient;
    }

    @Override
    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO) {
        if(etudiantDTO==null) throw new NullPointerException();
        LocationDTO location=new LocationDTO();
        location.setLongtitude(etudiantDTO.getLongtitude());
        location.setLatitude(etudiantDTO.getLatitude());
        LocationDTO locationDTO=this.locationClient.addLocation(location).getBody();
        if(locationDTO==null) throw new NullPointerException();
        etudiantDTO.setLocationId(locationDTO.getIdLocation());
        EtudiantDTO etudiantDTO1=modelMapper.map(this.etudiantRepository.save(modelMapper.map(etudiantDTO, Etudiant.class)),EtudiantDTO.class);
        if (etudiantDTO1!=null) {
            UtilisateurDTO utilisateurDTO=new UtilisateurDTO();
            utilisateurDTO.setNom(etudiantDTO1.getNom());
            utilisateurDTO.setPrenom(etudiantDTO1.getPrenom());
            utilisateurDTO.setEmail(etudiantDTO1.getEmail());
            utilisateurDTO.setRoleEnum(RoleEnum.PARENT);
            utilisateurDTO.setAdresse(etudiantDTO1.getAdresse());
            utilisateurDTO.setTel(etudiantDTO1.getTel());
            utilisateurDTO.setDateNaissance(etudiantDTO1.getDateNaissance());
            utilisateurDTO.setPassword(etudiantDTO.getPassword());
            utilisateurDTO.setIdEcole(etudiantDTO1.getEcoleId());
            utilisateurClient.saveUtilisateur(utilisateurDTO);
        }
        etudiantDTO1.setLatitude(locationDTO.getLatitude());
        etudiantDTO1.setLongtitude(locationDTO.getLongtitude());
        return etudiantDTO1;
    }

    @Override
    public EtudiantDTO modEtudiant(EtudiantDTO etudiantDTO,long id){
        if(etudiantDTO==null) throw new NullPointerException();
        EtudiantDTO etudiantDTO1=this.afficherEtudiantById(id);
        if(etudiantDTO1==null) throw new NullPointerException();
        etudiantDTO.setEtudiantId(id);
        etudiantDTO1.setNom(etudiantDTO.getNom());
        etudiantDTO1.setPrenom(etudiantDTO.getPrenom());
        etudiantDTO1.setAdresse(etudiantDTO.getAdresse());
        etudiantDTO1.setDateNaissance(etudiantDTO.getDateNaissance());
        etudiantDTO1.setTel(etudiantDTO.getTel());
        etudiantDTO1.setEmail(etudiantDTO.getEmail());
        etudiantDTO1.setNiveau(etudiantDTO.getNiveau());
        etudiantDTO1.setBusId(etudiantDTO.getBusId());
        etudiantDTO1.setEcoleId(etudiantDTO.getEcoleId());
        LocationDTO locationDTO=this.locationClient.getLocation(etudiantDTO.getLocationId()).getBody();
        if(locationDTO==null) throw new NullPointerException();
        if (etudiantDTO.getLatitude()!=locationDTO.getLatitude() || etudiantDTO.getLongtitude()!=locationDTO.getLongtitude()){
            locationDTO.setLatitude(etudiantDTO.getLatitude());
            locationDTO.setLongtitude(etudiantDTO.getLongtitude());
            locationDTO=this.locationClient.modLocation(etudiantDTO.getLocationId(),locationDTO).getBody();
            if(locationDTO==null) throw new NullPointerException();
        }
        EtudiantDTO etudiantDTO2=modelMapper.map(this.etudiantRepository.save(modelMapper.map(etudiantDTO1, Etudiant.class)),EtudiantDTO.class);
        etudiantDTO2.setLatitude(locationDTO.getLatitude());
        etudiantDTO2.setLongtitude(locationDTO.getLongtitude());
        return etudiantDTO2;

    }

    @Override
    public List<EtudiantDTO> afficherEtudiants() {
        List<EtudiantDTO> etudiants=this.etudiantRepository.findByDeletedFalse().stream().map(e->modelMapper.map(e,EtudiantDTO.class)).toList();
        etudiants.forEach(e->{
            LocationDTO locationDTO=this.locationClient.getLocation(e.getLocationId()).getBody();
            if(locationDTO==null) throw new NullPointerException();
            e.setLatitude(locationDTO.getLatitude());
            e.setLongtitude(locationDTO.getLongtitude());
        });
        return etudiants;
    }

    @Override
    public EtudiantDTO afficherEtudiantById(long id) {
        Etudiant etudiant=this.etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new NullPointerException();
        LocationDTO locationDTO=this.locationClient.getLocation(etudiant.getLocationId()).getBody();
        if(locationDTO==null) throw new NullPointerException();
        EtudiantDTO etudiantDTO=modelMapper.map(etudiant,EtudiantDTO.class);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        //LocalDate date=LocalDate.parse(dt1.format(etudiant.getDateNaissance()));
        //etudiantDTO.setDateNaissance(date);
        etudiantDTO.setLatitude(locationDTO.getLatitude());
        etudiantDTO.setLongtitude(locationDTO.getLongtitude());
        return etudiantDTO;
    }

    @Override
    public EtudiantDTO afficherEtudiantByEmail(String email) {
        Etudiant etudiant=this.etudiantRepository.findByEmail(email);
        if(etudiant!=null){
            LocationDTO locationDTO=this.locationClient.getLocation(etudiant.getLocationId()).getBody();
            if(locationDTO==null) throw new NullPointerException();
            EtudiantDTO etudiantDTO=modelMapper.map(etudiant,EtudiantDTO.class);
            etudiantDTO.setLatitude(locationDTO.getLatitude());
            etudiantDTO.setLongtitude(locationDTO.getLongtitude());
            return etudiantDTO;
        }
        return null;
    }

    @Override
    public List<EtudiantDTO> afficherEtudiantsByEcoleId(long idEcole) {
        List<EtudiantDTO> etudiants=this.etudiantRepository.findByEcoleIdAndDeletedFalse(idEcole).stream().map(e->modelMapper.map(e,EtudiantDTO.class)).toList();
        return etudiants ;
    }

    @Override
    public boolean delEtudiant(long id) {
        Etudiant etudiant=this.etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new NullPointerException();
        etudiant.setDeleted(true);
        return this.etudiantRepository.save(etudiant).isDeleted();
    }
}
