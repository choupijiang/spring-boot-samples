package io.ostenant.springboot.sample.exception;

import lombok.Getter;
import lombok.Setter;

public class IllegalPropertiesException extends Exception {

    @Getter
    @Setter
    protected String message;

    public IllegalPropertiesException() {
        setMessage("Prop is illegal!");
    }

    public IllegalPropertiesException(String message) {
        this.message = message;
        setMessage(String.format("Prop: %s is illegal!", message));
    }
}
