package com.jnsdevs.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Autor Jairo Nascimento
 * @Created 06/07/2023 - 11:11
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}
