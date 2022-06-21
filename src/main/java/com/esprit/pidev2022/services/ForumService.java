package com.esprit.pidev2022.services;


import com.esprit.pidev2022.Exception.ForumNotFoundException;
import com.esprit.pidev2022.repository.*;

import com.esprit.pidev2022.entities.Forum;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


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

    public List<Forum> findAllForum()
    {return (List<Forum>) forumRepository.findAll();
    }
}
