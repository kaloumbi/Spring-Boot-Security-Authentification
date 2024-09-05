package com.abs.SpringSecurityJWT.service.gestionCotisationService;


import com.abs.SpringSecurityJWT.dto.EventDTO;
import com.abs.SpringSecurityJWT.enitty.Event;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ETAT_EVENT;
import com.abs.SpringSecurityJWT.mapper.EventMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private UserService userService;

    @Override
    public EventDTO addEvent(EventDTO eventDTO) {

        //mapper le dto event en objet
        Event eventToAdd = eventMapper.toEntity(eventDTO);

        //Appel de la fonction pour trouver l'utisateur connecté
        User userConnectedFound = userService.getAuthenticatedUser();

        //ensuite sauvegarder en base
        eventToAdd.setDate(new Date());
        eventToAdd.setEtat(ETAT_EVENT.ACTIF.toString());

        if (userConnectedFound == null){
            return null;
        }

        eventToAdd.setUser(userConnectedFound);
        Event eventStored = eventRepo.save(eventToAdd);

        //le mapper en dto puis le renvoyé
        return eventMapper.toDto(eventStored);
    }

    @Override
    public List<EventDTO> listEvents() {
        List<Event> eventList = eventRepo.findAll();

        List<EventDTO> eventDTOList = eventMapper.toDto(eventList);

        return eventDTOList;
    }

    @Override
    public EventDTO detailEvent(Long id) {

        Optional<Event> evenSearched = eventRepo.findById(id);

        if (evenSearched.isEmpty()){
            return null;
        }

        Event evenFound = evenSearched.get();

        return eventMapper.toDto(evenFound);

    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {

        //Appel de la fonction pour trouver l'utisateur connecté
        User userConnectedFound = userService.getAuthenticatedUser();

        Optional<Event> eventSearched = eventRepo.findById(id);

        if (eventSearched.isEmpty()){
            return null;
        }

        Event eventFound = eventSearched.get();

        //si id dto est null prends l'id l'objet trouvé
        if (eventDTO.getId() == null){
            eventDTO.setId(eventFound.getId());
        }


        // Convertir le DTO en entité
        eventFound = eventMapper.toEntity(eventDTO);

        //recuperer l'id de l'utilisateur qui a modifie
        eventFound.setUser(userConnectedFound);

        // Sauvegarder l'entité mise à jour
        eventRepo.save(eventFound);

        // Renvoyer le DTO
        return eventMapper.toDto(eventFound);
    }


    //Function me permettant de supprimer un evenement
    @Override
    public void deleteEvent(Long id) {

        Optional<Event> searchEvent = eventRepo.findById(id);

        if (searchEvent.isEmpty()){
            throw new MyNotFoundExceptionClass("Aucun utilisateur trouvé avec l'identifiant " + id + " fourni !");
        }

        Event eventFound = searchEvent.get();
        eventFound.setEtat(ETAT_EVENT.SUPPRIME.toString());

        eventRepo.save(eventFound);
    }


    @Override
    public List<EventDTO> searchEvents(String nom) {

        List<Event> listEventsSearch = eventRepo.findByNom(nom);


        return eventMapper.toDto(listEventsSearch);

    }



}
