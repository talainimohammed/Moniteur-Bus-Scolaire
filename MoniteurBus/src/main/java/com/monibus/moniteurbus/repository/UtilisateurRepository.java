package com.monibus.moniteurbus.repository;

import com.monibus.moniteurbus.entity.Bus;
import com.monibus.moniteurbus.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
}
