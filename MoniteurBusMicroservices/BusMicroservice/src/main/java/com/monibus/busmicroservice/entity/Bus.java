package com.monibus.busmicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBus;
    private String matricule;
    private long nbplaces;
    private long idchauffeur;
    //private long idlocation;
    private long idecole;
    @Column(name="is_deleted")
    private boolean deleted;
    
}
