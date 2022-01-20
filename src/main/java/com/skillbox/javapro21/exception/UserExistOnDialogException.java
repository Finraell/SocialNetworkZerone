package com.skillbox.javapro21.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserExistOnDialogException extends CustomException {
    public UserExistOnDialogException(String message) {
        super(message);
    }
}