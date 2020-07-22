package com.codenation.demo.service.interfaces;

import com.codenation.demo.model.Event;
import com.codenation.demo.model.request.EventRequest;
import com.codenation.demo.model.response.EventWithoutLogResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EventService {

    List<EventWithoutLogResponse> findAll(Pageable page, Specification<Event> specification);
    Long save(EventRequest event);
}
