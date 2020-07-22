package com.codenation.demo.model.request;

import com.codenation.demo.model.Level;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventRequest {
    private Level level;

    private String description;

    private String log;

    private String origin;

    private LocalDateTime date;

    private Long quantity;
}
