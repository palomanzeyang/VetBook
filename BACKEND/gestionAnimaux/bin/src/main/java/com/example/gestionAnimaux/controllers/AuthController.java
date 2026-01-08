package com.example.gestionAnimaux.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionAnimaux.entites.Veterinaire;
import com.example.gestionAnimaux.repository.VeterinaireRepository;
import com.example.gestionAnimaux.security.JwtUtil;
import com.example.gestionAnimaux.service.VeterinaireService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    VeterinaireRepository veterinaireRepository;
    @Autowired
    VeterinaireService veterinaireService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody Veterinaire veterinaire) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        veterinaire.getEmailVeterinaire(),
                        veterinaire.getMdpVeterinaire()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
    
    @PostMapping("/signup")
    public String registerUser(@RequestBody Veterinaire veterinaire) {
        if (veterinaireRepository.existsByEmailVeterinaire(veterinaire.getEmailVeterinaire())) {
            return "Error: Username is already taken!";
        }
        			Veterinaire newUser = new Veterinaire();
        			newUser.setIdVeterinaire(generateMatricule());
        			newUser.setEmailVeterinaire(veterinaire.getEmailVeterinaire());
        			newUser.setNomVeterinaire(veterinaire.getNomVeterinaire());
        			newUser.setNumeroVeterinaire(veterinaire.getNumeroVeterinaire());
        			newUser.setMdpVeterinaire(encoder.encode(veterinaire.getMdpVeterinaire()));
        	
        	        veterinaireRepository.save(newUser);
        	        return "Veterinaire registered successfully!";
    }
    
    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "VET" + unique;
	}
}
