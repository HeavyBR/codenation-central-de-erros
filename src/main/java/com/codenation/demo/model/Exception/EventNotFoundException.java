package com.codenation.demo.model.Exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("There is no element with that ID.");
    }
}
