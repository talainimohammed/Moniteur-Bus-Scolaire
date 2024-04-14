package com.monibus.ecolemicroservice.feignclients.etudiant;

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
    private long busId;
    private long locationId;
    private long ecoleId;
    private boolean isDeleted;

}
