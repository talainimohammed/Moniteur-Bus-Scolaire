package com.monibus.busmicroservice.service.serviceImp;

import com.monibus.busmicroservice.dto.BusDTO;
import com.monibus.busmicroservice.service.IBus;
import com.monibus.busmicroservice.feignclients.ecole.EcoleClient;
import com.monibus.busmicroservice.feignclients.ecole.EcoleDTO;
import com.monibus.busmicroservice.feignclients.utilisateur.UtilisateurClient;
import com.monibus.busmicroservice.feignclients.utilisateur.UtilisateurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusServiceTest {

    @Autowired
    IBus busService;
    BusDTO busDTO;
    EcoleDTO ecoleDTO;
    UtilisateurDTO utilisateurDTO;
    @MockBean
    EcoleClient ecoleClient;
    @MockBean
    UtilisateurClient utilisateurClient;
    @BeforeEach
    void setUp() {
        busDTO=new BusDTO();
        busDTO.setIdBus(1L);
        busDTO.setNbplaces(50);
        busDTO.setMatricule("Citaro");
        busDTO.setIdecole(1);
        busDTO.setIdchauffeur(1);
        ecoleDTO=new EcoleDTO();
        ecoleDTO.setIdEcole(1);
        ecoleDTO.setNomEcole("Ecole1");
        ecoleDTO.setAdresse("Adresse1");
        utilisateurDTO=new UtilisateurDTO();
        utilisateurDTO.setIdPersonne(1);
        utilisateurDTO.setNom("Nom1");
        utilisateurDTO.setPrenom("Prenom1");
    }

    @Test
    void addBus() {
        when(ecoleClient.getEcole(1)).thenReturn(ResponseEntity.ok(ecoleDTO));
        BusDTO busDTO1=busService.addBus(busDTO);
        assertEquals(busDTO1.getMatricule(),busDTO.getMatricule());
    }

    @Test
    void modBus() {
        when(ecoleClient.getEcole(1)).thenReturn(ResponseEntity.ok(ecoleDTO));
        when(utilisateurClient.getUtilisateur(1)).thenReturn(ResponseEntity.ok(utilisateurDTO));
        BusDTO busDTO1=busService.addBus(busDTO);
        BusDTO busDTO2=busService.modBus(busDTO1,1);
        assertEquals(busDTO2.getMatricule(),busDTO1.getMatricule());
    }

    @Test
    void afficherBuses() {
        when(ecoleClient.getEcole(1)).thenReturn(ResponseEntity.ok(ecoleDTO));
        BusDTO busDTO1=busService.addBus(busDTO);
        assertNotNull(busService.afficherBuses(1L));
    }

    @Test
    void afficherBus() {
        when(ecoleClient.getEcole(1)).thenReturn(ResponseEntity.ok(ecoleDTO));
        BusDTO busDTO1=busService.addBus(busDTO);
        assertNotNull(busService.afficherBus(busDTO1.getIdBus()));
    }

    @Test
    void delBus() {
        when(ecoleClient.getEcole(1)).thenReturn(ResponseEntity.ok(ecoleDTO));
        BusDTO busDTO1=busService.addBus(busDTO);
        assertTrue(busService.delBus(busDTO1.getIdBus()));
    }
}