package com.monibus.etudiantmicroservice.service;

import com.monibus.etudiantmicroservice.dto.EtudiantDTO;

import java.util.List;

public interface IEtudiant {

    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO);
    public EtudiantDTO modEtudiant(EtudiantDTO etudiantDTO,long id);
    public List<EtudiantDTO> afficherEtudiants();
    public EtudiantDTO afficherEtudiantById(long id);
    public EtudiantDTO afficherEtudiantByEmail(String email);

    public List<EtudiantDTO> afficherEtudiantsByEcoleId(long id);
    public boolean delEtudiant(long id);

}
