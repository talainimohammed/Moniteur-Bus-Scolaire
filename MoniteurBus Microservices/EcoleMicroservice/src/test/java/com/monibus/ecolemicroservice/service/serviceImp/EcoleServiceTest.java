package com.monibus.ecolemicroservice.service.serviceImp;

import com.monibus.ecolemicroservice.dto.EcoleDTO;
import com.monibus.ecolemicroservice.repository.EcoleRepository;
import com.monibus.location.LocationClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EcoleServiceTest {

    private EcoleRepository ecoleRepository;
    private LocationClient locationClient;
    private ModelMapper modelMapper;
    EcoleDTO ecoleDTO;
    @Autowired
    public EcoleServiceTest(EcoleRepository ecoleRepository,ModelMapper modelMapper,LocationClient locationClient1){
        this.ecoleRepository=ecoleRepository;
        this.modelMapper=modelMapper;
        this.locationClient=locationClient1;
    }

    @BeforeEach
    void setUp() {
        ecoleDTO=new EcoleDTO();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEcole() {
    }

    @Test
    void modEcole() {
    }

    @Test
    void afficherEcoles() {
    }

    @Test
    void afficherEcoleById() {
    }
}