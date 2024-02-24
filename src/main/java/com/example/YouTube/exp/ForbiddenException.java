package com.example.YouTube.exp;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String massage){
        super(massage);
    }

}
