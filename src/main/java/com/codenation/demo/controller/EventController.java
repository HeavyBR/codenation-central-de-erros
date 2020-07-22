package com.codenation.demo.controller;

import com.codenation.demo.model.Event;
import com.codenation.demo.model.request.EventRequest;
import com.codenation.demo.model.response.EventWithLogResponse;
import com.codenation.demo.model.response.EventWithoutLogResponse;
import com.codenation.demo.service.EventServiceImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class EventController {

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @GetMapping
    @Cacheable(value = "events")
    public ResponseEntity<List<EventWithoutLogResponse>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable page,
                                                                 @SearchSpec Specification<Event> specs) {

        return ResponseEntity.ok(eventServiceImpl.findAll(page, specs));
    }

    @CacheEvict(value = "events", allEntries = true)
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid EventRequest eventRequest, UriComponentsBuilder uriComponentsBuilder) {
        Long createdEvent = eventServiceImpl.save(eventRequest);

        URI uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(createdEvent).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventWithLogResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventServiceImpl.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EventRequest event) {
        eventServiceImpl.update(id, event);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        eventServiceImpl.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }
}