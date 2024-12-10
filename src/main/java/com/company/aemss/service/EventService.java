package com.company.aemss.service;

import com.company.aemss.entity.Event;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final DataManager dataManager;

    public EventService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Event> getAllEvents() {
        return dataManager.load(Event.class).all().list();
    }
}