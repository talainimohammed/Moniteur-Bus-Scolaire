package com.monibus.busmicroservice.service.serviceImp;

import com.monibus.busmicroservice.dto.BusDTO;
import com.monibus.busmicroservice.service.IBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BusServiceTest {

    @Autowired
    IBus busService;
    BusDTO busDTO;
    @BeforeEach
    void setUp() {
        busDTO=new BusDTO();
        busDTO.setIdBus(1L);
        busDTO.setNbplaces(50);
        busDTO.setMatricule("Citaro");
        busDTO.setIdecole(1);
        busDTO.setIdchauffeur(1);
    }

    @Test
    void addBus() {
        BusDTO busDTO1=busService.addBus(busDTO);
        assertEquals(busDTO1.getMatricule(),busDTO.getMatricule());
    }

    @Test
    void modBus() {
        BusDTO busDTO1=busService.addBus(busDTO);
        BusDTO busDTO2=busService.modBus(busDTO1,1);
        assertEquals(busDTO2.getMatricule(),busDTO1.getMatricule());
    }

    @Test
    void afficherBuses() {
        BusDTO busDTO1=busService.addBus(busDTO);
        assertNotNull(busService.afficherBuses(1L));
    }

    @Test
    void afficherBus() {
        BusDTO busDTO1=busService.addBus(busDTO);
        assertNotNull(busService.afficherBus(busDTO1.getIdBus()));
    }

    @Test
    void delBus() {
        BusDTO busDTO1=busService.addBus(busDTO);
        assertTrue(busService.delBus(busDTO1.getIdBus()));
    }
}