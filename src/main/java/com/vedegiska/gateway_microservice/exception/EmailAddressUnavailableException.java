package com.vedegiska.gateway_microservice.exception;

public class EmailAddressUnavailableException extends RuntimeException {

    public EmailAddressUnavailableException(String message) {
        super(message);
    }
}
