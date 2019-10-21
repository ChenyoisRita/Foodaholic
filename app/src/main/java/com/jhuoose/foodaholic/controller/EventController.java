package com.jhuoose.foodaholic.controller;

import com.jhuoose.foodaholic.model.Event;
import com.jhuoose.foodaholic.repository.EventRepository;

public class EventController {
    private static final EventController ourInstance = new EventController();

    private EventRepository eventRepository;

    public static EventController getInstance() {
        return ourInstance;
    }

    private EventController() {
        this.eventRepository = EventRepository.getInstance();
    }

    public void createEvent(Event event) {
        event.setEid(Integer.toHexString(event.hashCode()));
        eventRepository.saveEvent(event);
    }

    public void updateEvent(Event event) {
        eventRepository.saveEvent(event);
    }
}
