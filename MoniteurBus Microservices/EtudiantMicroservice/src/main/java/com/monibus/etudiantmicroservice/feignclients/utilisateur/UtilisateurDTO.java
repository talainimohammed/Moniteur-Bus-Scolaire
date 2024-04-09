package com.monibus.etudiantmicroservice.feignclients.utilisateur;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UtilisateurDTO {
    private long idPersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private LocalDate dateNaissance;
    private String tel;
    private String email;
    private String password;
    private long idEcole;
    private RoleEnum roleEnum;
    private boolean isDeleted;

}
