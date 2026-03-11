package org.cardservice.Exception.CustomeException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAvailableException extends RuntimeException {

    public CardAvailableException(String msg) {
        super(msg);
    }
}
