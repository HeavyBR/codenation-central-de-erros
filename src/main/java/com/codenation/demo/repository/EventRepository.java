package com.codenation.demo.repository;

import com.codenation.demo.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    Page<Event> findByDescription(String description, Pageable pageable);
    Page<Event> findByOrigin(String origin, Pageable pageable);
    Page<Event> findByQuantity(Long quantity, Pageable page);
    Page<Event> findByDate(LocalDateTime date, Pageable page);

}
