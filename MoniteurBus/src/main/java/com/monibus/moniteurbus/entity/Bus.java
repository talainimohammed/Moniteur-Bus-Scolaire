package com.monibus.moniteurbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personnes")
public class Bus {
    @Id
    private long idBus;
    private String matricule;
    @ManyToOne
    private Utilisateur chauffeur;
    //@ManyToOne
    //private Location location;
    @ManyToOne
    private Ecole ecole;
    @Column(name="is_deleted")
    private boolean isDeleted;
    
}
