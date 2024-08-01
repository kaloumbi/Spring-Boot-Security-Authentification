package com.abs.SpringSecurityJWT.service.gestionCotisationService;


import com.abs.SpringSecurityJWT.dto.EventDTO;
import com.abs.SpringSecurityJWT.enitty.Event;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ETAT_EVENT;
import com.abs.SpringSecurityJWT.mapper.EventMapper;
import com.abs.SpringSecurityJWT.notFoundExceptionClass.MyNotFoundExceptionClass;
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

    @Override
    public EventDTO addEvent(EventDTO eventDTO) {

        //mapper le dto event en objet
        Event eventToAdd = eventMapper.toEntity(eventDTO);

        //ensuite sauvegarder en base
        eventToAdd.setDate(new Date());
        eventToAdd.setEtat(ETAT_EVENT.ACTIF.toString());
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

        Optional<Event> eventSearched = eventRepo.findById(id);

        if (eventSearched.isEmpty()){
            return null;
        }

        Event eventFound = eventSearched.get();

        //prendre les nouvelles valeurs du dto
        eventFound = eventMapper.toEntity(eventDTO);

        //les sauvegarder
        eventRepo.save(eventFound);

        //renvoyer le dto
        return  eventMapper.toDto(eventFound);

    }
}
