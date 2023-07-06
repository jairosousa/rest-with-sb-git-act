package com.jnsdevs.app.exceptions;

import java.io.Serializable;
import java.util.Date;

/**
 * @Autor Jairo Nascimento
 * @Created 06/07/2023 - 11:08
 */
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
