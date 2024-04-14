package com.monibus.etudiantmicroservice.repository;

import com.monibus.etudiantmicroservice.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    List<Etudiant> findByDeletedFalse();
    List<Etudiant> findByEcoleIdAndDeletedFalse(long idEcole);
    Etudiant findByEmail(String email);

}
