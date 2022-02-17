package com.library.management.system.librarymanagementsystem.exception;

import java.util.Date;

public class ErrorMessage {
    
    private boolean status;
    private String message;
    private String details;
    private Date timestamp;

    public ErrorMessage() {
    }

    
    public ErrorMessage(boolean status, String message, String details, Date timestamp) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }


    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    
}
