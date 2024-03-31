package com.monibus.ecolemicroservice.service.serviceImp;

import com.monibus.ecolemicroservice.dto.EcoleDTO;
import com.monibus.ecolemicroservice.repository.EcoleRepository;
import com.monibus.ecolemicroservice.service.IEcole;
import com.monibus.location.LocationClient;
import com.monibus.location.LocationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EcoleServiceTest {

    private IEcole ecoleRepository;
    EcoleDTO ecoleDTO;
    LocationDTO locationDTO;
    @Autowired
    public EcoleServiceTest(IEcole ecoleRepository){
        this.ecoleRepository=ecoleRepository;
    }

    @BeforeEach
    void setUp() {
        ecoleDTO=new EcoleDTO();
        ecoleDTO.setNomEcole("Ecole1");
        ecoleDTO.setAdresse("Adresse1");
        ecoleDTO.setTelephone("Telephone1");
        ecoleDTO.setEmail("Email1");
        ecoleDTO.setLatitude(1.0);
        ecoleDTO.setLongtitude(1.0);
        locationDTO=new LocationDTO();
        locationDTO.setIdLocation(1);
        locationDTO.setLatitude(1.0);
        locationDTO.setLongtitude(1.0);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEcole() {
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        assertNotNull(ecoleDTO1);
        assertEquals(ecoleDTO.getNomEcole(),ecoleDTO1.getNomEcole());
        assertEquals(ecoleDTO.getAdresse(),ecoleDTO1.getAdresse());
        assertEquals(ecoleDTO.getTelephone(),ecoleDTO1.getTelephone());
        assertEquals(ecoleDTO.getEmail(),ecoleDTO1.getEmail());
    }

    @Test
    void modEcole() {
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        ecoleDTO1.setAdresse("Adresse2");
        EcoleDTO ecoleDTO2=this.ecoleRepository.modEcole(ecoleDTO1,ecoleDTO1.getIdEcole());
        assertNotNull(ecoleDTO2);
        assertEquals(ecoleDTO1.getNomEcole(),ecoleDTO2.getNomEcole());
        assertEquals(ecoleDTO1.getAdresse(),ecoleDTO2.getAdresse());
        assertEquals(ecoleDTO1.getTelephone(),ecoleDTO2.getTelephone());
        assertEquals(ecoleDTO1.getEmail(),ecoleDTO2.getEmail());
    }

    @Test
    void afficherEcoles() {
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        List<EcoleDTO> ecoleDTOList=this.ecoleRepository.afficherEcoles();
        assertNotNull(ecoleDTOList);
    }

    @Test
    void afficherEcoleById() {
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        EcoleDTO ecoleDTO2=this.ecoleRepository.afficherEcoleById(ecoleDTO1.getIdEcole());
        assertNotNull(ecoleDTO2);
        assertEquals(ecoleDTO1.getNomEcole(),ecoleDTO2.getNomEcole());
        assertEquals(ecoleDTO1.getAdresse(),ecoleDTO2.getAdresse());
        assertEquals(ecoleDTO1.getTelephone(),ecoleDTO2.getTelephone());
        assertEquals(ecoleDTO1.getEmail(),ecoleDTO2.getEmail());
    }
}