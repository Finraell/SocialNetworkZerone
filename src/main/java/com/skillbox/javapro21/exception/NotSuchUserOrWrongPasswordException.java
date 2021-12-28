package com.skillbox.javapro21.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotSuchUserOrWrongPasswordException extends Exception {
    public NotSuchUserOrWrongPasswordException(String message) {
        super(message);
    }
}
