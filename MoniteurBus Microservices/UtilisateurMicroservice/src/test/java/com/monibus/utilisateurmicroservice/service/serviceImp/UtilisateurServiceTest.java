package com.monibus.utilisateurmicroservice.service.serviceImp;

import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;
import com.monibus.utilisateurmicroservice.entity.Utilisateur;
import com.monibus.utilisateurmicroservice.service.IUtilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilisateurServiceTest {

    @Autowired
    IUtilisateur iUtilisateur;
    UtilisateurDTO utilisateurDTO;
    @BeforeEach
    void setUp() {
        utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom("nom");
        utilisateurDTO.setPrenom("prenom");
        utilisateurDTO.setRoleEnum(RoleEnum.CHAUFFEUR);
        utilisateurDTO.setEmail("login");
        utilisateurDTO.setPassword("password");
        utilisateurDTO.setIdUtilisateur(1L);
        utilisateurDTO.setAdresse("adresse");
        utilisateurDTO.setTel("telephone");
        utilisateurDTO.setDateNaissance(LocalDate.parse("2022-02-02"));
        utilisateurDTO.setIdEcole(1L);
    }

    @Test
    void addUtilisateur() {
        UtilisateurDTO utilisateur = iUtilisateur.addUtilisateur(utilisateurDTO);
        assertEquals(utilisateur.getNom(), utilisateurDTO.getNom());
        assertEquals(utilisateur.getPrenom(), utilisateurDTO.getPrenom());
        assertEquals(utilisateur.getRoleEnum(), utilisateurDTO.getRoleEnum());
        assertEquals(utilisateur.getEmail(), utilisateurDTO.getEmail());
        assertEquals(utilisateur.getIdUtilisateur(), utilisateurDTO.getIdUtilisateur());
        assertEquals(utilisateur.getAdresse(), utilisateurDTO.getAdresse());
        assertEquals(utilisateur.getTel(), utilisateurDTO.getTel());
        assertEquals(utilisateur.getDateNaissance(), utilisateurDTO.getDateNaissance());
    }

    @Test
    void modUtilisateur() {
        UtilisateurDTO utilisateur1 = iUtilisateur.addUtilisateur(utilisateurDTO);
        UtilisateurDTO utilisateur = iUtilisateur.modUtilisateur(utilisateurDTO, 1L);
        assertEquals(utilisateur.getNom(), utilisateurDTO.getNom());
        assertEquals(utilisateur.getPrenom(), utilisateurDTO.getPrenom());
        assertEquals(utilisateur.getRoleEnum(), utilisateurDTO.getRoleEnum());
        assertEquals(utilisateur.getEmail(), utilisateurDTO.getEmail());
        assertEquals(utilisateur.getIdUtilisateur(), utilisateurDTO.getIdUtilisateur());
        assertEquals(utilisateur.getAdresse(), utilisateurDTO.getAdresse());
        assertEquals(utilisateur.getTel(), utilisateurDTO.getTel());
        assertEquals(utilisateur.getDateNaissance(), utilisateurDTO.getDateNaissance());
    }

    @Test
    void afficherUtilisateurs() {
        UtilisateurDTO utilisateur = iUtilisateur.addUtilisateur(utilisateurDTO);
        assertNotNull(iUtilisateur.afficherUtilisateurs());
    }

    @Test
    void afficherUtilisateurById() {
        UtilisateurDTO utilisateur = iUtilisateur.addUtilisateur(utilisateurDTO);
        assertNotNull(iUtilisateur.afficherUtilisateurById(1L));
    }

    @Test
    void afficherUtilisateurByRole() {
        UtilisateurDTO utilisateur = iUtilisateur.addUtilisateur(utilisateurDTO);
        assertNotNull(iUtilisateur.afficherUtilisateurByRole(RoleEnum.CHAUFFEUR.ordinal(), 1L));
    }

    @Test
    void delUtilisateur() {
        UtilisateurDTO utilisateur = iUtilisateur.addUtilisateur(utilisateurDTO);
        assertTrue(iUtilisateur.delUtilisateur(1L));
    }
}