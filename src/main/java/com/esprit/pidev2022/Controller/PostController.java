package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Comment;
import com.esprit.pidev2022.entities.Post;
import com.esprit.pidev2022.services.PostService;
import com.esprit.pidev2022.entities.Post;
import com.esprit.pidev2022.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/Post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        if (post.getDateCreated()==null)
        {post.setDateCreated(new Date());}
        Post newPost = postService.addPost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatePost = postService.updatePost(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePostByID(@PathVariable("id") Long id) {

        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
