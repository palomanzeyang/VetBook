package com.example.gestionAnimaux.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import com.example.gestionAnimaux.entites.Veterinaire;
import com.example.gestionAnimaux.repository.VeterinaireRepository;


@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private VeterinaireRepository veterinaireRepository;
    
    
    @Override
    public UserDetails loadUserByUsername(String emailVeterinaire) throws UsernameNotFoundException {
        Veterinaire vet = veterinaireRepository.findByEmailVeterinaire(emailVeterinaire);
        if (vet == null) {
            throw new UsernameNotFoundException("Veterinaire Not Found with email: " + emailVeterinaire);
        }
        return new org.springframework.security.core.userdetails.User(
        		vet.getEmailVeterinaire(),
        		vet.getMdpVeterinaire(),
                Collections.emptyList()
        );
    }
}