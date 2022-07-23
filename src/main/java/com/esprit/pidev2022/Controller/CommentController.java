package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Comment;

import com.esprit.pidev2022.entities.MyConstants;
import com.esprit.pidev2022.entities.Post;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.security.repository.UserRepository;
import com.esprit.pidev2022.security.services.UserDetailsImpl;
import com.esprit.pidev2022.services.CommentService;
import com.esprit.pidev2022.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/Comment")
@CrossOrigin(origins = "http://localhost:4200")

public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final UserRepository userRepository;
    @Autowired
    public JavaMailSender emailSender;
    public CommentController(CommentService commentService, PostService postService, UserRepository userRepository) {
        this.commentService = commentService;
        this.postService = postService;
        this.userRepository = userRepository;
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

    @GetMapping("/find/post/{id}")
    public ResponseEntity<?> getCommentByPost(@PathVariable("id") Long id) {
        List<Comment> comments = commentService.FindCommentByPost(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @PostMapping("/add/{id}")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Long id,@RequestBody Comment comment) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Long userid = userDetails.getId();
        User user = userRepository.getById(userid);
        if (comment.getDateCreated()==null)
        {comment.setDateCreated(new Date());}
        Post post = postService.findPostById(id);
        comment.setPost(post);
        comment.setUser(user);
        Comment newComment = commentService.addComment(comment);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Succes");
        message.setText("Hello added with success");

        // Send Message!
        this.emailSender.send(message);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id,@RequestBody Comment comment) {
        Comment commentEntity = commentService.findCommentById(id);
        commentEntity.setContained(comment.getContained());
        Comment updateComment = commentService.updateComment(commentEntity);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentByID(@PathVariable("id") Long id) {

        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findComment/{id}/")
    public ResponseEntity<?> findcommentByPost( @PathVariable("id")Long id)
    {
        List<Comment> comments = commentService.findCommentByPostId(id);


        return new ResponseEntity<>(comments, HttpStatus.OK);

    }



}
