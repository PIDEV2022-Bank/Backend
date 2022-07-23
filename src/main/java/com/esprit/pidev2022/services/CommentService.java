package com.esprit.pidev2022.services;

import com.esprit.pidev2022.Exception.CommentNotFoundException;
import com.esprit.pidev2022.entities.Comment;
import com.esprit.pidev2022.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public Comment addComment(Comment comment){

        return commentRepository.save(comment);
    }
    public Comment updateComment(Comment  comment){
        return commentRepository.save(comment);
    }
    public void deleteComment(Long id)
    {
        commentRepository.deleteCommentById(id);
    }
    public Comment findCommentById(Long id)
    {
        return commentRepository.findCommentById(id)
                .orElseThrow(()-> new CommentNotFoundException("Comment by id"+id+"not found"));

    }

    public List<Comment > findAllForum()
    {return (List<Comment>) commentRepository.findAll();
    }

    public List<Comment> findAllComment()

        {return (List<Comment>) commentRepository.findAll();
        }

    public List<Comment> FindCommentByPost(Long id)

    {return (List<Comment>) commentRepository.findCommentByPostId(id);
    }

    public List<Comment> findCommentByPostId(Long id) {
        return commentRepository.findCommentByPostId(id);
    }
}

