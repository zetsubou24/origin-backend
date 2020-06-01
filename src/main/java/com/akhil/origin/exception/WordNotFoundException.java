package com.akhil.origin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WordNotFoundException extends Exception {
    public WordNotFoundException(String msg) {
        super(msg);
    }
}

