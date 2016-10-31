package com.maketest.exceptions;

import java.util.Date;

/**
 * Created by Jovana Micic on 31-Oct-16.
 */
public class Error {
    private int code;
    private String message;
    private String description;
    private long timestamp;

    public Error(){}

    public Error(int code, String message, String description, long timestamp) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
