package com.monibus.ecolemicroservice.controller;

import com.monibus.ecolemicroservice.dto.EcoleDTO;
import com.monibus.ecolemicroservice.service.IEcole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EcoleController.class)
class EcoleControllerTest {

    @MockBean
    private IEcole iEcole;
    @Autowired
    MockMvc mockMvc;
    EcoleDTO ecoleDTO;
    @BeforeEach
    void setUp() {
        ecoleDTO=new EcoleDTO();
        ecoleDTO.setNomEcole("Ecole1");
        ecoleDTO.setAdresse("Adresse1");
        ecoleDTO.setTelephone("Telephone1");
        ecoleDTO.setEmail("Email1");
        ecoleDTO.setLatitude(1.0);
        ecoleDTO.setLongtitude(1.0);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void tousEcoles() throws Exception {
        when(this.iEcole.afficherEcoles()).thenReturn(List.of(ecoleDTO));
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/ecole")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @Test
    void getEcole() throws Exception {
        when(this.iEcole.afficherEcoleById(1)).thenReturn(ecoleDTO);
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/ecole/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @Test
    void ajouterEcole() throws Exception {
        when(this.iEcole.addEcole(ecoleDTO)).thenReturn(ecoleDTO);
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/ecole")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @Test
    void modEcole() throws Exception {
        when(this.iEcole.modEcole(ecoleDTO,1)).thenReturn(ecoleDTO);
        MvcResult mvcResult= mockMvc.perform(get("/api/v1/ecole/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertNotNull(response);
    }
}