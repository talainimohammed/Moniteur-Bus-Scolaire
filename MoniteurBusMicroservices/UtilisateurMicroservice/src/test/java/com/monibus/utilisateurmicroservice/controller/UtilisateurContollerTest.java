package com.monibus.utilisateurmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monibus.utilisateurmicroservice.Enum.RoleEnum;
import com.monibus.utilisateurmicroservice.dto.UtilisateurDTO;
import com.monibus.utilisateurmicroservice.service.IUtilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UtilisateurContoller.class)
class UtilisateurContollerTest {

    @MockBean
    IUtilisateur iUtilisateur;
    UtilisateurDTO utilisateurDTO;

    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom("nom");
        utilisateurDTO.setPrenom("prenom");
        utilisateurDTO.setRoleEnum(RoleEnum.CHAUFFEUR);
        utilisateurDTO.setEmail("login");
        utilisateurDTO.setIdUtilisateur(1L);
        utilisateurDTO.setAdresse("adresse");
        utilisateurDTO.setTel("telephone");
    }

    @Test
    void tousUtilisateurs() throws Exception {
        when(iUtilisateur.afficherUtilisateurs()).thenReturn(List.of(utilisateurDTO));
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/utilisateur"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void tousUtilisateursparRole() throws Exception {
        when(iUtilisateur.afficherUtilisateurByRole(1,1L)).thenReturn(List.of(utilisateurDTO));
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/utilisateur/role/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getUtilisateur() throws Exception {
        when(iUtilisateur.afficherUtilisateurById(1L)).thenReturn(utilisateurDTO);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/utilisateur/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void saveUtilisateur() throws Exception {
        when(iUtilisateur.addUtilisateur(utilisateurDTO)).thenReturn(utilisateurDTO);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/utilisateur")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(utilisateurDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void modUtilisateur() throws Exception{
        when(iUtilisateur.modUtilisateur(utilisateurDTO, 1L)).thenReturn(utilisateurDTO);
        MvcResult mvcResult = mockMvc.perform(put("/api/v1/utilisateur/1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(utilisateurDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void delUtilisateur() {
        when(iUtilisateur.delUtilisateur(1L)).thenReturn(true);
        assertDoesNotThrow(() -> mockMvc.perform(delete("/api/v1/utilisateur/1")));
    }
}