package com.monibus.moniteurbus.service;

import com.monibus.moniteurbus.dto.UtilisateurDTO;

import java.util.List;

public interface IUtilisateur {

    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO);
    public UtilisateurDTO modUtilisateur(UtilisateurDTO utilisateurDTO);
    public List<UtilisateurDTO> afficherUtilisateurs();
    public UtilisateurDTO afficherUtilisateurById(long id);
    public boolean delUtilisateur(long id);
}
