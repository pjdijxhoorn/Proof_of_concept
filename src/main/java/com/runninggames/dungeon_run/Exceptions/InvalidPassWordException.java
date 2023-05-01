package com.runninggames.dungeon_run.Exceptions;

public class InvalidPassWordException extends RuntimeException{

    public InvalidPassWordException() {

        super();
    }
    public InvalidPassWordException(String message) {

        super(message);
    }
}

