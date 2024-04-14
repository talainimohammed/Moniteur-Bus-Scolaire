package com.monibus.utilisateurmicroservice.service;

import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;

import java.util.List;

public interface IUtilisateur {

    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO);
    public UtilisateurDTO modUtilisateur(UtilisateurDTO utilisateurDTO,long id);
    public List<UtilisateurDTO> afficherUtilisateurs();
    //public List<UtilisateurDTO> afficherUtilisateursByEcoleId(long id);

    public UtilisateurDTO afficherUtilisateurById(long id);
    public List<UtilisateurDTO> afficherUtilisateurByRole(int roleEnum,long idEcole);
    public boolean delUtilisateur(long id);
    String generateToken(Long id, String email, String role,Long idEcole);
    void validateToken(String token);
}
