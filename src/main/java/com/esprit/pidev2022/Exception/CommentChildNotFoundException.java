package com.esprit.pidev2022.Exception;

import com.esprit.pidev2022.entities.CommentChild;

public class CommentChildNotFoundException extends RuntimeException{
    public CommentChildNotFoundException(String message) {
        super(message);
    }

}
