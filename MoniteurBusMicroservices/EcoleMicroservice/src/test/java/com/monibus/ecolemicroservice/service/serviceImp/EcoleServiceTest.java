package com.monibus.ecolemicroservice.service.serviceImp;

import com.monibus.ecolemicroservice.dto.EcoleDTO;
import com.monibus.ecolemicroservice.repository.EcoleRepository;
import com.monibus.ecolemicroservice.service.IEcole;
import com.monibus.ecolemicroservice.feignclients.location.LocationClient;
import com.monibus.ecolemicroservice.feignclients.location.LocationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
class EcoleServiceTest {

    private IEcole ecoleRepository;
    EcoleDTO ecoleDTO;
    LocationDTO locationDTO;
    @Mock
    private LocationClient locationClient;

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
    void addEcole() throws Exception {
        when(this.locationClient.addLocation(locationDTO)).thenReturn(ResponseEntity.ok(locationDTO));
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        assertNotNull(ecoleDTO1);
        assertEquals(ecoleDTO.getNomEcole(),ecoleDTO1.getNomEcole());
        assertEquals(ecoleDTO.getAdresse(),ecoleDTO1.getAdresse());
        assertEquals(ecoleDTO.getTelephone(),ecoleDTO1.getTelephone());
        assertEquals(ecoleDTO.getEmail(),ecoleDTO1.getEmail());
    }

    @Test
    void modEcole() {
        when(locationClient.addLocation(locationDTO)).thenReturn(ResponseEntity.ok(locationDTO));
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
        when(locationClient.addLocation(locationDTO)).thenReturn(ResponseEntity.ok(locationDTO));
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        List<EcoleDTO> ecoleDTOList=this.ecoleRepository.afficherEcoles();
        assertNotNull(ecoleDTOList);
    }

    @Test
    void afficherEcoleById() {
        when(locationClient.addLocation(locationDTO)).thenReturn(ResponseEntity.ok(locationDTO));
        EcoleDTO ecoleDTO1=this.ecoleRepository.addEcole(ecoleDTO);
        EcoleDTO ecoleDTO2=this.ecoleRepository.afficherEcoleById(ecoleDTO1.getIdEcole());
        assertNotNull(ecoleDTO2);
        assertEquals(ecoleDTO1.getNomEcole(),ecoleDTO2.getNomEcole());
        assertEquals(ecoleDTO1.getAdresse(),ecoleDTO2.getAdresse());
        assertEquals(ecoleDTO1.getTelephone(),ecoleDTO2.getTelephone());
        assertEquals(ecoleDTO1.getEmail(),ecoleDTO2.getEmail());
    }
}