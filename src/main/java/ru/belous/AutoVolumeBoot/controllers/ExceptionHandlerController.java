package ru.belous.AutoVolumeBoot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.belous.AutoVolumeBoot.exceptions.DataNotFoundException;
import ru.belous.AutoVolumeBoot.exceptions.NotValidDataException;
import ru.belous.AutoVolumeBoot.utils.PersonErrorResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionHandlerController {

    private final SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss a zzz");
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(DataNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Data not found",
                formatForDateNow.format(new Date())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> personException(NotValidDataException e){
        PersonErrorResponse response = new PersonErrorResponse(
                e.toString(),
                formatForDateNow.format(new Date())
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(UsernameNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Data not found",
                formatForDateNow.format(new Date())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
