package com.monibus.ecolemicroservice.service;

import com.monibus.ecolemicroservice.dto.EcoleDTO;

import java.util.List;

public interface IEcole {

    public EcoleDTO addEcole(EcoleDTO ecoleDTO);
    public EcoleDTO modEcole(EcoleDTO ecoleDTO,long id);
    public List<EcoleDTO> afficherEcoles();
    public EcoleDTO afficherEcoleById(long id);
    
}
