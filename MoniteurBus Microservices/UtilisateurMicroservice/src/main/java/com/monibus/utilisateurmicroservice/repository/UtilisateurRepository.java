package com.monibus.utilisateurmicroservice.repository;

import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import com.monibus.utilisateurmicroservice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    List<Utilisateur> findAllByDeletedFalse();
    List<Utilisateur> findAllByRoleEnumAndDeletedFalse(RoleEnum roleEnum);
}
