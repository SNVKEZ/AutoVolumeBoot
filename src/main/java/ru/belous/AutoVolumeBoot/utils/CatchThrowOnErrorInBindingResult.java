package ru.belous.AutoVolumeBoot.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.belous.AutoVolumeBoot.exceptions.NotValidDataException;

import java.util.List;

public class CatchThrowOnErrorInBindingResult {

    public void catchNotValidException(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder error = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError oneError : errors){
                error.append(oneError.getField()).append("-").append(oneError.getDefaultMessage())
                        .append(";");
            }

            throw new NotValidDataException(error.toString());
        }
    }

}
