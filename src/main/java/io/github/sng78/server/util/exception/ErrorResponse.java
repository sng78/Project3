package io.github.sng78.server.util.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;

    private LocalDateTime timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        setTimestamp(LocalDateTime.now());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
