package org.bankapp.Exception;

import org.bankapp.DTO.ErrorResponseDTO;
import org.bankapp.Exception.CustomeException.CustomerAvailableException;
import org.bankapp.Exception.CustomeException.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobleExceptions {

    @ExceptionHandler(CustomerAvailableException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(CustomerAvailableException exception, WebRequest request){

        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
         return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(ResourceNotFoundException exception, WebRequest request){

        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception, WebRequest request){

        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
