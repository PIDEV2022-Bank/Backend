package com.esprit.pidev2022.services;

import com.esprit.ib.Exception.ForumNotFoundException;
import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional.*;
import java.util.UUID;

@Service
@Transactional
public class ForumService {
   private final ForumRepository forumRepository;

    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public Forum addForum(Forum forum){
    return forumRepository.save(forum);
    }
    public Forum updateForum(Forum forum){
        return forumRepository.save(forum);
    }
    public void deleteForum(Long id)
    {
        forumRepository.deleteForumById(id);
    }
   public Forum findForumById(Long id)
    {
        return forumRepository.findForumById(id)
               .orElseThrow(()-> new ForumNotFoundException("Forum by id"+id+"not found"));

    }

    public List<Forum > findAllForum()
    {return (List<Forum>) forumRepository.findAllByOrderByIdDesc();
    }
}
