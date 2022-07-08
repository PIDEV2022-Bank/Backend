package com.esprit.ib.Exception;

public class ForumNotFoundException extends RuntimeException{
    public ForumNotFoundException(String message) {
        super(message);
    }
}
