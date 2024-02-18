package com.monibus.moniteurbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ecoles")
public class Ecole {
    @Id
    private long idEcole;
    private String nomEcole;
    @ManyToOne
    private Location location;
    private String adresse;
    @Column(name="is_deleted")
    private boolean isDeleted;
    
}
