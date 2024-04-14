package com.monibus.etudiantmicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long etudiantId;
    private String nom;
    private String prenom;
    private String adresse;
    private LocalDate dateNaissance;
    private String email;
    private String tel;
    private String niveau;
    private long busId;
    private long locationId;
    private long ecoleId;
    @Column(name="is_deleted")
    private boolean deleted;
}