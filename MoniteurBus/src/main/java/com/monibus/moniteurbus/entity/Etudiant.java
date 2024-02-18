package com.monibus.moniteurbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiants")
public class Etudiant extends Personne{
    private String niveau;
    @ManyToOne
    private Bus bus;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Ecole ecole;
    @Column(name="is_deleted")
    private boolean isDeleted;
}