package com.codenation.demo.model.response;

import com.codenation.demo.model.Event;
import com.codenation.demo.model.Level;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EventWithLogResponse {
    private Level level;
    private String description;
    private String origin;
    private Long quantity;
    private String log;
    private LocalDateTime date;

    public static EventWithLogResponse valueOf(Event e) {
        return builder()
                .description(e.getDescription())
                .origin(e.getOrigin())
                .quantity(e.getQuantity())
                .level(e.getLevel())
                .log(e.getLog())
                .date(e.getDate())
                .build();
    }
}
