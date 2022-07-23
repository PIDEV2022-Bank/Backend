package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.Dto.PostRequest;
import com.esprit.pidev2022.entities.*;
import com.esprit.pidev2022.repository.ForumRepository;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.security.repository.UserRepository;
import com.esprit.pidev2022.security.services.UserDetailsImpl;
import com.esprit.pidev2022.services.PostService;
import com.esprit.pidev2022.entities.Post;
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
@RequestMapping("/Post")
@CrossOrigin(origins = "http://localhost:4200")

public class PostController {
    private final PostService postService;
    private final ForumRepository forumRepository;
    private final UserRepository userRepository;
    @Autowired
    public JavaMailSender emailSender;
    public PostController(PostService postService, ForumRepository forumRepository, UserRepository userRepository) {
        this.postService = postService;
        this.forumRepository = forumRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost() {

        List<Post> posts = postService.findAllForum();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Post> getPostByID(@PathVariable("id") Long id) {

        Post post = postService.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody PostRequest post) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Long userid = userDetails.getId();
        User user = userRepository.getById(userid);
        Forum forum = forumRepository.findForumById(post.getIdForum()).get();

        if (post.getDateCreated()==null)
        {post.setDateCreated(new Date());}
        Post postEntity = new Post();
        Forum forum1 = new Forum();
        forum1.setId(post.getIdForum());
        postEntity.setForum(forum);
        postEntity.setContained(post.getContained());
        postEntity.setTitle(post.getTitle());
        postEntity.setDateCreated(post.getDateCreated());
        postEntity.setUser(user);
        Post newPost = postService.addPost(postEntity);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Succes");
        message.setText("Hello added with success");

        // Send Message!
        this.emailSender.send(message);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        System.out.println(post+"poste");
        post.setId(id);
        post.setForum(forumRepository.findForumById(post.getForum().getId()).get());
        Post updatePost = postService.updatePost(post);

        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePostByID(@PathVariable("id") Long id) {
System.out.println(id);
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping("/findPostForum/{id}")
    public ResponseEntity<?> findPostByForumId( @PathVariable("id")Long id)
    {
        List<Post> posts = postService.findAllPostByForum(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);

    }


}
