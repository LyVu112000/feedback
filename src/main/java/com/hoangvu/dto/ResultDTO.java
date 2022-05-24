package com.hoangvu.dto;

public class ResultDTO<T> {

    private T data;
    private boolean isError;
    private String message;

    public ResultDTO() {}

    public ResultDTO(T data, boolean isError, String message) {
        this.data = data;
        this.message = message;
        this.isError = isError;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"data\": \""
                + this.data
                +"\", \"message\": \""
                +this.message
                +"\"}";
    }
}