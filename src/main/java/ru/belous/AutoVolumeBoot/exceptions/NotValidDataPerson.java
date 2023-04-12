package ru.belous.AutoVolumeBoot.exceptions;

import ru.belous.AutoVolumeBoot.utils.PersonErrorResponse;

public class NotValidDataPerson extends RuntimeException{
    public NotValidDataPerson(String msg){
        super(msg);
    }
}
