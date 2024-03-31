package com.monibus.etudiantmicroservice.service.serviceImp;

import com.monibus.etudiantmicroservice.dto.EtudiantDTO;
import com.monibus.etudiantmicroservice.service.IEtudiant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EtudiantServiceTest {

    @Autowired
    IEtudiant etudiantService;
    EtudiantDTO etudiantDTO;
    @BeforeEach
    void setUp() {
        etudiantDTO=new EtudiantDTO();
        etudiantDTO.setNom("nom");
        etudiantDTO.setPrenom("prenom");
        etudiantDTO.setEtudiantId(1L);
        etudiantDTO.setNiveau("niveau");
        etudiantDTO.setLatitude(1.0);
        etudiantDTO.setLongtitude(1.0);
        etudiantDTO.setAdresse("adresse");
        etudiantDTO.setDateNaissance(LocalDate.parse("2000-02-02"));
        etudiantDTO.setEmail("email");
        etudiantDTO.setTel("021252325");
    }

    @Test
    void addEtudiant() {
        EtudiantDTO etudiantDTO1=etudiantService.addEtudiant(etudiantDTO);
        assertEquals(etudiantDTO1.getNom(),etudiantDTO.getNom());
    }

    @Test
    void modEtudiant() {
        EtudiantDTO etudiantDTO1=etudiantService.addEtudiant(etudiantDTO);
        EtudiantDTO etudiantDTO2=etudiantService.modEtudiant(etudiantDTO,etudiantDTO1.getEtudiantId());
        assertEquals(etudiantDTO2.getNom(),etudiantDTO1.getNom());
    }

    @Test
    void afficherEtudiants() {
        EtudiantDTO etudiantDTO1=etudiantService.addEtudiant(etudiantDTO);
        List<EtudiantDTO> etudiantDTOList=etudiantService.afficherEtudiants();
        assertNotNull(etudiantDTOList);
    }

    @Test
    void afficherEtudiantById() {
        EtudiantDTO etudiantDTO2=etudiantService.addEtudiant(etudiantDTO);
        EtudiantDTO etudiantDTO1=etudiantService.afficherEtudiantById(etudiantDTO2.getEtudiantId());
        assertNotNull(etudiantDTO1);
    }

    @Test
    void delEtudiant() {
        EtudiantDTO etudiantDTO1=etudiantService.addEtudiant(etudiantDTO);
        assertTrue(etudiantService.delEtudiant(etudiantDTO1.getEtudiantId()));
    }
}