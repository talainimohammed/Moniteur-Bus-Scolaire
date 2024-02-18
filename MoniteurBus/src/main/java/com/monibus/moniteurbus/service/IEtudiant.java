package com.monibus.moniteurbus.service;

import com.monibus.moniteurbus.dto.EtudiantDTO;

import java.util.List;

public interface IEtudiant {

    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO);
    public EtudiantDTO modEtudiant(EtudiantDTO etudiantDTO);
    public List<EtudiantDTO> afficherEtudiants();
    public EtudiantDTO afficherEtudiantById(long id);
    public boolean delEtudiant(long id);

}
