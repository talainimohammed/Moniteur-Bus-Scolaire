package com.monibus.ecolemicroservice.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEcole;
    private String nomEcole;
    private long locationId;
    private String adresse;
    private String email;
    private String telephone;
    @Column(name="is_deleted")
    private boolean Deleted;
    
}
