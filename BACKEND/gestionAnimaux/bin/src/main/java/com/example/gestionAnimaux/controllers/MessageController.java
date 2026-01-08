package com.example.gestionAnimaux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestionAnimaux.dto.MessageDto;
import com.example.gestionAnimaux.service.MessageService;


@RestController
@CrossOrigin("*")
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/send")
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDTO) {
        return ResponseEntity.ok(messageService.envoyerMessage(messageDTO));
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages(
            @RequestParam String idVeterinaire,
            @RequestParam String idProprietaire
    ) {
        return ResponseEntity.ok(messageService.getMessagesBetween(idVeterinaire, idProprietaire));
    }
}
