package com.monibus.moniteurbus.service;

import com.monibus.moniteurbus.dto.EcoleDTO;

import java.util.List;

public interface IEcole {

    public EcoleDTO addEcole(EcoleDTO ecoleDTO);
    public EcoleDTO modEcole(EcoleDTO ecoleDTO);
    public List<EcoleDTO> afficherEcoles();
    public EcoleDTO afficherEcoleById(long id);
    
}
