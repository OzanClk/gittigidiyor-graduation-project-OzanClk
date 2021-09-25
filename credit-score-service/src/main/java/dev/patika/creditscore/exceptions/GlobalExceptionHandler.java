package dev.patika.creditscore.exceptions;


import dev.patika.creditscore.entity.ErrorsResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({WrongIdentificationNumber.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorsResponseEntity> handleException(WrongIdentificationNumber exception) {

        ErrorsResponseEntity appErrorResponse = new ErrorsResponseEntity();

        appErrorResponse.setMessage(exception.getMessage());
        appErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(appErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoUserExistWithThisID.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorsResponseEntity> handleException(NoUserExistWithThisID exception) {

        ErrorsResponseEntity appErrorResponse = new ErrorsResponseEntity();

        appErrorResponse.setMessage(exception.getMessage());
        appErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(appErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
