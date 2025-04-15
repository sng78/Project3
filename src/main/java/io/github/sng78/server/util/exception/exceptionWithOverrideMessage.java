package io.github.sng78.server.util.exception;

public class exceptionWithOverrideMessage extends RuntimeException {

    public exceptionWithOverrideMessage(String message) {
        super(message);
    }
}
