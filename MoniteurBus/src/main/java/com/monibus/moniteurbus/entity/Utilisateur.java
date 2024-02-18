package com.monibus.moniteurbus.entity;

import com.monibus.moniteurbus.Enum.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateurs")
public class Utilisateur extends Personne{

    private String username;
    private String password;
    private RoleEnum roleEnum;

}
