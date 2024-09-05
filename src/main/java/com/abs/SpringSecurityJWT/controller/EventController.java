package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.EventDTO;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
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
            return new ResponseEntity<>(eventDetail, HttpStatus.OK);
        }catch (MyNotFoundExceptionClass ex){
            return new ResponseEntity<>("Aucun obet trouvé !" ,HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("events/{id}/updated")
    public ResponseEntity<EventDTO> updateEvent (@PathVariable Long id, @RequestBody EventDTO eventDTO){

            EventDTO eventUpDto = eventService.updateEvent(id, eventDTO);

            return ResponseEntity.ok(eventUpDto);
    }

    @DeleteMapping("event/{id}/delete")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id){

        try {
            // Essayer de supprimer l'événement
            eventService.deleteEvent(id);
            // Si tout se passe bien, renvoyer une réponse de succès
            return new ResponseEntity<>("Événement supprimé avec succès.", HttpStatus.OK);

        } catch (MyNotFoundExceptionClass ex) {
            // Exception spécifique pour l'événement non trouvé
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            // Gestion des autres exceptions
            return new ResponseEntity<>("Une erreur est survenue lors de la suppression de l'événement.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("events/search")
    public ResponseEntity<List<EventDTO>> searchEventByName(@RequestParam String nom){

        List<EventDTO> eventDTOList = eventService.searchEvents(nom);

        return ResponseEntity.ok(eventDTOList);
    }



}
