package com.esprit.pidev2022.Exception;

public class ForumNotFoundException extends RuntimeException{
    public ForumNotFoundException(String message) {
        super(message);
    }
}
