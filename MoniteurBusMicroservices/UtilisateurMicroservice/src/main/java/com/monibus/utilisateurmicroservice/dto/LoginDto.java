package com.monibus.utilisateurmicroservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "email utilisateur est vide")
    @NotEmpty(message = "email utilisateur empty")
    @NotNull(message = "email utilisateur null")
    private String email;
    @NotBlank(message = "password utilisateur est vide")
    @NotEmpty(message = "password utilisateur empty")
    @NotNull(message = "password utilisateur null")
    private String password;
}
