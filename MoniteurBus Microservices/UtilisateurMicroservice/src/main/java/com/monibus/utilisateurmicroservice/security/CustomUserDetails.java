package com.monibus.utilisateurmicroservice.security;

import com.monibus.utilisateurmicroservice.entity.Utilisateur;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String password;
    private String email;
    private String role;

    public CustomUserDetails(Utilisateur userCredential)
    {
        this.id = userCredential.getIdUtilisateur();
        this.password = userCredential.getPassword();
        this.email = userCredential.getEmail();
        this.role = userCredential.getRoleEnum().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
