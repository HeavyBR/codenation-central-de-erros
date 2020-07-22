package com.codenation.demo.model;

import com.codenation.demo.model.request.EventRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String description;

    private String log;

    private String origin;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm")
    private LocalDateTime date;

    private Long quantity;

    public static Event valueOf(EventRequest eventRequest) {
        return builder()
                .description(eventRequest.getDescription())
                .log(eventRequest.getLog())
                .origin(eventRequest.getOrigin())
                .quantity(eventRequest.getQuantity())
                .level(eventRequest.getLevel())
                .build();
    }
}
