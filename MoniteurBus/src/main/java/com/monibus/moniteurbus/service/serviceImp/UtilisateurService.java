package com.monibus.moniteurbus.service.serviceImp;

import com.monibus.moniteurbus.dto.UtilisateurDTO;
import com.monibus.moniteurbus.entity.Utilisateur;
import com.monibus.moniteurbus.repository.UtilisateurRepository;
import com.monibus.moniteurbus.service.IUtilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtilisateurService implements IUtilisateur {

    private UtilisateurRepository utilisateurRepository;
    private ModelMapper modelMapper;
    public UtilisateurService(UtilisateurRepository utilisateurRepository,ModelMapper modelMapper){
        this.utilisateurRepository=utilisateurRepository;
        this.modelMapper=modelMapper;

    }
    @Override
    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO) {
        if(utilisateurDTO == null) throw new NullPointerException();
        return modelMapper.map(this.utilisateurRepository.save(modelMapper.map(utilisateurDTO, Utilisateur.class)),UtilisateurDTO.class);
    }

    @Override
    public UtilisateurDTO modUtilisateur(UtilisateurDTO utilisateurDTO) {
        if(utilisateurDTO == null) throw new NullPointerException();
        return modelMapper.map(this.utilisateurRepository.save(modelMapper.map(utilisateurDTO, Utilisateur.class)),UtilisateurDTO.class);
    }

    @Override
    public List<UtilisateurDTO> afficherUtilisateurs() {
        return this.utilisateurRepository.findAll().stream().map(u->modelMapper.map(u,UtilisateurDTO.class)).toList();
    }

    @Override
    public UtilisateurDTO afficherUtilisateurById(long id) {
        Utilisateur utilisateur=this.utilisateurRepository.findById(id).orElse(null);
        if (utilisateur==null)throw new NullPointerException();
        return modelMapper.map(utilisateur,UtilisateurDTO.class);
    }

    @Override
    public boolean delUtilisateur(long id) {
        Utilisateur utilisateur=this.utilisateurRepository.findById(id).orElse(null);
        if (utilisateur==null)throw new NullPointerException();
        utilisateur.setDeleted(true);
        return this.utilisateurRepository.save(utilisateur).isDeleted();
    }
}
