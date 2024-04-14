package com.monibus.utilisateurmicroservice.dto;

import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class UtilisateurDTO {
    private long idUtilisateur;
    @NotBlank(message = "Nom utilisateur est vide")
    @NotEmpty(message = "Nom utilisateur empty")
    @NotNull(message = "Nom utilisateur null")
    private String nom;
    @NotBlank(message = "prenom utilisateur est vide")
    @NotEmpty(message = "prenom utilisateur empty")
    @NotNull(message = "prenom utilisateur null")
    private String prenom;
    @NotBlank(message = "adresse utilisateur est vide")
    @NotEmpty(message = "adresse utilisateur empty")
    @NotNull(message = "adresse utilisateur null")
    private String adresse;
    private LocalDate dateNaissance;
    @NotBlank(message = "tel utilisateur est vide")
    @NotEmpty(message = "tel utilisateur empty")
    @NotNull(message = "tel utilisateur null")
    private String tel;
    @NotBlank(message = "email utilisateur est vide")
    @NotEmpty(message = "email utilisateur empty")
    @NotNull(message = "email utilisateur null")
    private String email;
    @NotBlank(message = "password utilisateur est vide")
    @NotEmpty(message = "password utilisateur empty")
    @NotNull(message = "password utilisateur null")
    private String password;
    private long idEcole;
    private RoleEnum roleEnum;
    private boolean isDeleted;

}
