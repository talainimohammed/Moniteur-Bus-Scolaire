package com.monibus.moniteurbus.dto;

import com.monibus.moniteurbus.entity.Bus;
import com.monibus.moniteurbus.entity.Ecole;
import com.monibus.moniteurbus.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
public class EtudiantDTO {
    private Long idPersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;
    private String tel;
    private String niveau;
    private long idBus;
    private long idLocation;
    private long idEcole;
    private boolean isDeleted;

}
