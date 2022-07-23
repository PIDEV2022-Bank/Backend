package com.esprit.pidev2022.Controller;

import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.entities.MyConstants;
import com.esprit.pidev2022.services.ForumService;
import com.esprit.pidev2022.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/Forum")
@CrossOrigin(origins = "http://localhost:4200")
public class ForumController {
    private final ForumService forumService;
    private final PostService postService;
    @Autowired
    public JavaMailSender emailSender;

    public ForumController(ForumService forumService, PostService postService) {
        this.forumService = forumService;

        this.postService = postService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Forum>> getAllForum() {

        List<Forum> forums = forumService.findAllForum();
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Forum> getForumByID(@PathVariable("id") Long id) {

       Forum forum = forumService.findForumById(id);
        return new ResponseEntity<>(forum, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum) {
        if (forum.getDateCreated()==null)
        {forum.setDateCreated(new Date());}
        Forum newForum = forumService.addForum(forum);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Succes");
        message.setText("Hello"+forum.getUser()+"Forum:+ forum.getTitle()"+ "added with success");

        // Send Message!
        this.emailSender.send(message);
        return new ResponseEntity<>(forum, HttpStatus.CREATED);
    }

    @PostMapping ("/update/{id}")
    public ResponseEntity<Forum> updateForum(@RequestBody Forum forum, @PathVariable("id") Long id) {
        forum.setId(id);
        Forum updateForum = forumService.updateForum(forum);
        return new ResponseEntity<>(updateForum, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteForumByID(@PathVariable("id") Long id) {

      forumService.deleteForum(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/findPost/{id}/{idpost}")
    public ResponseEntity<?> findPostByForum( @PathVariable("id")Long id, @PathVariable("idpost")Long idPost)
    {
        Forum forum = forumService.findForumById(id);

        return new ResponseEntity<>(postService.findAllPostByForum(forum.getId()), HttpStatus.OK);

    }


    }

