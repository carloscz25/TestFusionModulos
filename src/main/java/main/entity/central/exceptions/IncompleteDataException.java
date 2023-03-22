package main.entity.central.exceptions;

import io.jmix.core.Entity;

public class IncompleteDataException extends Exception{

    Object[] additionals;

    public IncompleteDataException(String message){
        super(message);
        this.additionals = additionals;
    }

}
