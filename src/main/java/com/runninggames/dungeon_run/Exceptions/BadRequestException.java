package com.runninggames.dungeon_run.Exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {

            super();
        }
    public BadRequestException(String message) {

            super(message);
        }
}
