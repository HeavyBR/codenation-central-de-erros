package com.codenation.demo.service;

import com.codenation.demo.model.Event;
import com.codenation.demo.model.Exception.EventNotFoundException;
import com.codenation.demo.model.request.EventRequest;
import com.codenation.demo.model.response.EventWithLogResponse;
import com.codenation.demo.model.response.EventWithoutLogResponse;
import com.codenation.demo.repository.EventRepository;
import com.codenation.demo.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventWithoutLogResponse> findAll(Pageable page, Specification<Event> specification) {
        List<Event> events = eventRepository.findAll(Specification.where(specification), page).getContent();
        return EventWithoutLogResponse.valueOf(events);
    }

    @Transactional
    public Long save(EventRequest eventRequest) {
        Event createdEvent = eventRepository.save(Event.valueOf(eventRequest));
        return createdEvent.getId();
    }

    public EventWithLogResponse findById(Long id) {
        Optional<Event> event = Optional.ofNullable(eventRepository.findById(id)).orElseThrow(EventNotFoundException::new);
        return EventWithLogResponse.valueOf(event.get());
    }

    @Transactional
    public void deleteEventById(Long id) throws ChangeSetPersister.NotFoundException {
        eventRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        eventRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, EventRequest eventRequest) {
        Event event = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
        event = event.valueOf(eventRequest);
        eventRepository.save(event);
    }
}
