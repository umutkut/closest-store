package com.jumbo.closeststore.controller.advice;

import com.jumbo.closeststore.exceptions.InvalidLatitudeException;
import com.jumbo.closeststore.exceptions.InvalidLocationTypeException;
import com.jumbo.closeststore.exceptions.InvalidLongitudeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Slf4j
public class StoreControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidLatitudeException.class, InvalidLongitudeException.class, InvalidLocationTypeException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<String> notAcceptable(Exception ex) {
        log.warn("Invalid request. Reason: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ex.getMessage());
    }


}
