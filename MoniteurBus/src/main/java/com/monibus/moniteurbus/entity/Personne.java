package com.monibus.moniteurbus.entity;

import java.util.Date;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;
    private String tel;
    @Column(name="is_deleted")
    private boolean isDeleted;

}
