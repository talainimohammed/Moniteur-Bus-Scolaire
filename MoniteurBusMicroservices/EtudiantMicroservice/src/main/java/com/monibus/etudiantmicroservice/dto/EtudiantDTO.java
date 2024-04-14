package com.monibus.etudiantmicroservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EtudiantDTO {
    private Long etudiantId;
    private String nom;
    private String prenom;
    private String adresse;
    private LocalDate dateNaissance;
    private String email;
    private String password;
    private String tel;
    private String niveau;
    private long busId;
    private long locationId;
    private double latitude;
    private double longtitude;
    private long ecoleId;
    private boolean isDeleted;

}
