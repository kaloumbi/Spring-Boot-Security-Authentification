package com.abs.SpringSecurityJWT.service.gestionCotisationService;


import com.abs.SpringSecurityJWT.dto.EventDTO;

import java.util.List;

public interface EventService {

    EventDTO addEvent (EventDTO eventDTO);

    List<EventDTO> listEvents ();

    EventDTO detailEvent(Long id);

    EventDTO updateEvent(Long id, EventDTO eventDTO);

    void deleteEvent(Long id);

    List<EventDTO> searchEvents(String nom);
}
