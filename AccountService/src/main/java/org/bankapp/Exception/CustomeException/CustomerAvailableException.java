package org.bankapp.Exception.CustomeException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAvailableException extends RuntimeException {

    public CustomerAvailableException(String msg) {
        super(msg);
    }
}
