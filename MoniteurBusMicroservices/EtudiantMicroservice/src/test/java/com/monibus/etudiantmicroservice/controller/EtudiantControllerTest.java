package com.monibus.etudiantmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monibus.etudiantmicroservice.dto.EtudiantDTO;
import com.monibus.etudiantmicroservice.service.IEtudiant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EtudiantController.class)
class EtudiantControllerTest {

    @MockBean
    IEtudiant iEtudiant;
    @Autowired
    MockMvc mockMvc;
    EtudiantDTO etudiantDTO;
    @BeforeEach
    void setUp() {
        etudiantDTO= new EtudiantDTO();
        etudiantDTO.setNom("nom");
        etudiantDTO.setPrenom("prenom");
        etudiantDTO.setEtudiantId(1L);
        etudiantDTO.setNiveau("niveau");
        etudiantDTO.setLatitude(1.0);
        etudiantDTO.setLongtitude(1.0);
        etudiantDTO.setAdresse("adresse");
        //etudiantDTO.setDateNaissance(LocalDate.parse("2000-02-02"));
        etudiantDTO.setEmail("email");
        etudiantDTO.setTel("021252325");
    }

    @Test
    void tousEtudiants() throws Exception {
        when(iEtudiant.afficherEtudiants()).thenReturn(List.of(etudiantDTO));
        MvcResult result = mockMvc.perform(get("/api/v1/etudiant"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("nom"));
    }

    @Test
    void getEtudiant() throws Exception {
        when(iEtudiant.afficherEtudiantById(1L)).thenReturn(etudiantDTO);
        MvcResult result = mockMvc.perform(get("/api/v1/etudiant/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("nom"));
    }

    @Test
    void addEtudiant() throws Exception {
        when(iEtudiant.addEtudiant(etudiantDTO)).thenReturn(etudiantDTO);
        MvcResult result = mockMvc.perform(post("/api/v1/etudiant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(etudiantDTO)))
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("nom"));
    }

    @Test
    void modEtudiant() throws Exception {
        when(iEtudiant.modEtudiant(etudiantDTO,1L)).thenReturn(etudiantDTO);
        MvcResult result = mockMvc.perform(put("/api/v1/etudiant/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(etudiantDTO)))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("nom"));
    }

    @Test
    void delEtudiant() throws Exception {
        when(iEtudiant.delEtudiant(1L)).thenReturn(true);
        MvcResult result = mockMvc.perform(delete("/api/v1/etudiant/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }
}