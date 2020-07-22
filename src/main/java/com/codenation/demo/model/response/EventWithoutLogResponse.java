package com.codenation.demo.model.response;

import com.codenation.demo.model.Event;
import com.codenation.demo.model.Level;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class EventWithoutLogResponse {
    private Level level;
    private String description;
    private String origin;
    private Long quantity;
    private LocalDateTime date;

    public static EventWithoutLogResponse valueOf (Event e) {
        return builder()
                .description(e.getDescription())
                .origin(e.getOrigin())
                .quantity(e.getQuantity())
                .level(e.getLevel())
                .date(e.getDate())
                .build();
    }

    public static List<EventWithoutLogResponse> valueOf(List<Event> events) {
        return events.stream().map(EventWithoutLogResponse::valueOf).collect(Collectors.toList());
    }
}
