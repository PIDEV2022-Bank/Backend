package com.esprit.pidev2022.services;


import com.esprit.pidev2022.Exception.CommentChildNotFoundException;
import com.esprit.pidev2022.entities.Comment;

import com.esprit.pidev2022.entities.CommentChild;
import com.esprit.pidev2022.entities.Forum;
import com.esprit.pidev2022.repository.CommentChildRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentChildService
{
    private final CommentChildRepository commentChildRepository;

    public CommentChildService(CommentChildRepository commentChildRepository) {
        this.commentChildRepository = commentChildRepository;
    }


    public CommentChild addCommentChild(CommentChild commentChild) {

        return commentChildRepository.save(commentChild);
    }

    public CommentChild updateCommentChild(CommentChild commentChild) {

        return commentChildRepository.save(commentChild);
    }

    public void deleteCommentChild(Long id) {

        commentChildRepository.deleteCommentChildById(id);
    }

    public CommentChild findCommentChildById(Long id) {
        return commentChildRepository.findCommentChildById(id)
                .orElseThrow(() -> new CommentChildNotFoundException("CommentChild by id" + id + "not found"));

    }

    public List<CommentChild> findAllCommentChild()

        {return (List<CommentChild>) commentChildRepository.findAll();
        }

}
