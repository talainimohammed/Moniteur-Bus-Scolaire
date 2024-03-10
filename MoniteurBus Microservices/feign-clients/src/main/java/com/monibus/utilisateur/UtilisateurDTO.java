package com.monibus.utilisateur;

import lombok.Data;

import java.util.Date;

@Data
public class UtilisateurDTO {
    private long idPersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;
    private String tel;
    private String username;
    private String password;
    private RoleEnum roleEnum;
    private boolean isDeleted;

}
