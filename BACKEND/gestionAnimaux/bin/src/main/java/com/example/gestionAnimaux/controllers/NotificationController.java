package com.example.gestionAnimaux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.example.gestionAnimaux.dto.NotificationDto;
import com.example.gestionAnimaux.entites.Notification;
import com.example.gestionAnimaux.service.NotificationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {
	
	 @Autowired
	    private NotificationService notificationService;

	    @PostMapping
	    public NotificationDto create(@RequestBody Notification notification) {
	        return notificationService.create(notification);
	    }

	    @GetMapping("/vet/{idVeterinaire}")
	    public List<NotificationDto> findByVetId(@PathVariable String idVeterinaire) {
	        return notificationService.findByVetId(idVeterinaire);
	    }

	    @PutMapping("/{id}")
	    public NotificationDto update(@PathVariable String id, @RequestBody Notification notification) {
	        return notificationService.update(id, notification);
	    }
}
