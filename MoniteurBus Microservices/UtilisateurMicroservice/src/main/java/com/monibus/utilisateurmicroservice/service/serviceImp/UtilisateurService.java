package com.monibus.utilisateurmicroservice.service.serviceImp;

import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;
import com.monibus.utilisateurmicroservice.entity.Utilisateur;
import com.monibus.utilisateurmicroservice.repository.UtilisateurRepository;
import com.monibus.utilisateurmicroservice.service.IUtilisateur;
import jakarta.ws.rs.NotFoundException;
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
        if(utilisateurDTO == null) throw new NullPointerException("Utilisateur ne peut pas etre null");
        return modelMapper.map(this.utilisateurRepository.save(modelMapper.map(utilisateurDTO, Utilisateur.class)),UtilisateurDTO.class);
    }

    @Override
    public UtilisateurDTO modUtilisateur(UtilisateurDTO utilisateurDTO, long id) {
        if(utilisateurDTO == null) throw new NullPointerException("Utilisateur ne peut pas etre null");
        UtilisateurDTO utilisateur=this.afficherUtilisateurById(id);
        if (utilisateur==null)throw new NotFoundException("Utilisateur non trouvé");
        utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
        return modelMapper.map(this.utilisateurRepository.save(modelMapper.map(utilisateurDTO, Utilisateur.class)),UtilisateurDTO.class);
    }

    @Override
    public List<UtilisateurDTO> afficherUtilisateurs() {
        return this.utilisateurRepository.findAllByDeletedFalse().stream().map(u->modelMapper.map(u,UtilisateurDTO.class)).toList();
    }

    @Override
    public UtilisateurDTO afficherUtilisateurById(long id) {
        Utilisateur utilisateur=this.utilisateurRepository.findById(id).orElse(null);
        if (utilisateur==null)throw new NotFoundException("Utilisateur non trouvé");
        return modelMapper.map(utilisateur,UtilisateurDTO.class);
    }

    @Override
    public List<UtilisateurDTO> afficherUtilisateurByRole(int roleEnum) {
        List<Utilisateur> utilisateurs=this.utilisateurRepository.findAllByRoleEnumAndDeletedFalse(RoleEnum.getRoleEnum(roleEnum));
        if (utilisateurs!=null)
            return utilisateurs.stream().map(u->modelMapper.map(u,UtilisateurDTO.class)).toList();
        return null;
    }


    @Override
    public boolean delUtilisateur(long id) {
        Utilisateur utilisateur=this.utilisateurRepository.findById(id).orElse(null);
        if (utilisateur==null)throw new NotFoundException("Utilisateur non trouvé");
        utilisateur.setDeleted(true);
        return this.utilisateurRepository.save(utilisateur).isDeleted();
    }
}
