package com.youkeda.practice.exception;

public class WeatherServiceException extends RuntimeException {

    private String errorCode;

    public WeatherServiceException(String message) {
        super(message);
    }

    public WeatherServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}