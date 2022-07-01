package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Comment;


import com.esprit.pidev2022.entities.CommentChild;
import com.esprit.pidev2022.services.CommentChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@RestController
@Transactional
@RequestMapping("/CommentChild")
public class CommentChildController {

    private final CommentChildService commentChildService;

    public CommentChildController(CommentChildService commentChildService) {
        this.commentChildService = commentChildService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<CommentChild>> getAllCommentChild() {

        List<CommentChild> commentChildren = commentChildService.findAllCommentChild();
        return new ResponseEntity<>(commentChildren, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CommentChild> getCommentChildByID(@PathVariable("id") Long id) {

        CommentChild commentChild = commentChildService.findCommentChildById(id);
        return new ResponseEntity<>(commentChild, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<CommentChild> addCommentChild(@RequestBody CommentChild commentChild) {
        if (commentChild.getDateCreated()==null)
        {commentChild.setDateCreated(new Date());}
        CommentChild newCommentChild = commentChildService.addCommentChild(commentChild);
        return new ResponseEntity<>(commentChild, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentChild> updateCommentChild(@RequestBody CommentChild commentChild) {
        CommentChild updateCommentChild = commentChildService.updateCommentChild(commentChild);
        return new ResponseEntity<>(commentChild, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentChildByID(@PathVariable("id") Long id) {

        commentChildService.deleteCommentChild(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
