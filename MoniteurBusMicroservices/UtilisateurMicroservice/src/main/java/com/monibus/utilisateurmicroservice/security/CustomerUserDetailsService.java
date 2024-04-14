package com.monibus.utilisateurmicroservice.security;

import com.monibus.utilisateurmicroservice.entity.Utilisateur;
import com.monibus.utilisateurmicroservice.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Optional<Utilisateur> credential = Optional.ofNullable(userRepository.findByEmail(email));
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with this email : " + email));
    }
}
