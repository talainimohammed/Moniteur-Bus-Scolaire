package com.monibus.utilisateurmicroservice.service;

import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;

import java.util.List;

public interface IUtilisateur {

    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO);
    public UtilisateurDTO modUtilisateur(UtilisateurDTO utilisateurDTO,long id);
    public List<UtilisateurDTO> afficherUtilisateurs();
    public UtilisateurDTO afficherUtilisateurById(long id);
    public boolean delUtilisateur(long id);
}
