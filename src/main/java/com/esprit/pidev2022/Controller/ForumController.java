package com.esprit.pidev2022.Controller;
import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.services.ForumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/Forum")
public class ForumController {
    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
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
        Forum newForum = forumService.addForum(forum);
        return new ResponseEntity<>(forum, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Forum> updateForum(@RequestBody Forum forum) {
        Forum updateForum = forumService.updateForum(forum);
        return new ResponseEntity<>(forum, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteForumByID(@PathVariable("id") Long id) {

      forumService.deleteForum(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }





    }

