package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Comment;
import com.esprit.pidev2022.entities.Forum;

import com.esprit.pidev2022.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/Comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComment() {

        List<Comment> comments = commentService.findAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Comment> getCommentByID(@PathVariable("id") Long id) {

        Comment comment = commentService.findCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        if (comment.getDateCreated()==null)
        {comment.setDateCreated(new Date());}
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        Comment updateComment = commentService.updateComment(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentByID(@PathVariable("id") Long id) {

        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
