package com.monibus.etudiantmicroservice.dto;

import lombok.Data;

import java.util.Date;
@Data
public class EtudiantDTO {
    private Long etudiantId;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;
    private String tel;
    private String niveau;
    private long busId;
    private long locationId;
    private double latitude;
    private double longtitude;
    private long ecoleId;
    private boolean isDeleted;

}
