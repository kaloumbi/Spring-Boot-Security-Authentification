package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.EventDTO;
import com.abs.SpringSecurityJWT.notFoundExceptionClass.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event/added")
    public ResponseEntity<String> addEvent(@RequestBody EventDTO eventDTO){

        try {
            EventDTO eventToAdd = eventService.addEvent(eventDTO);

            ResponseEntity.ok(eventToAdd);

            return ResponseEntity.ok("Evennement ajouté avec succès !!!");
        }catch (MyNotFoundExceptionClass e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity existante !!!");
        }

    }

    //fonction pour lister les evennements
    @GetMapping("events/list")
    public ResponseEntity<List<EventDTO>> getAllEvents(){

        List<EventDTO> eventDTOList = eventService.listEvents();

        return ResponseEntity.ok(eventDTOList);
    }


    @GetMapping("event/{id}/detail")
    public ResponseEntity<Object> detailEvent(@PathVariable Long id){

        try {
            EventDTO eventDetail = eventService.detailEvent(id);
            return ResponseEntity.ok(eventDetail);
        }catch (MyNotFoundExceptionClass ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune Methode !");
        }
    }


    @PutMapping("events/{id}/updated")
    public ResponseEntity<EventDTO> updateEvent (@PathVariable Long id, @RequestBody EventDTO eventDTO){

            EventDTO eventUpDto = eventService.updateEvent(id, eventDTO);

            return ResponseEntity.ok(eventUpDto);
    }



}
