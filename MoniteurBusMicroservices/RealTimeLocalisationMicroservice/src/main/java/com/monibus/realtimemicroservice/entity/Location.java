package com.monibus.realtimemicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "localisations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idLocation;
    private double latitude;
    private double longtitude;
    private  long idbus;
    @Column(name="is_deleted")
    private boolean deleted;
    
}
