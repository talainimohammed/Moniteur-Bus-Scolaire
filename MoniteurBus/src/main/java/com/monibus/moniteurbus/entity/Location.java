package com.monibus.moniteurbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idLocation;
    private double latitude;
    private double longtitude;
    @Column(name="is_deleted")
    private boolean isDeleted;
    
}
