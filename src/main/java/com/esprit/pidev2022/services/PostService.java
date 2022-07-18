package com.esprit.pidev2022.services;


import com.esprit.pidev2022.Exception.PostNotFoundException;
import com.esprit.pidev2022.entities.Post;
import com.esprit.pidev2022.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post addPost(Post post){

      post.setContained(post.getContained());
      post.setTitle(post.getTitle());

        return postRepository.save(post);
    }
    public Post updatePost(Post post){

        return postRepository.save(post);
    }
    public void deletePost(Long id)
    {
        postRepository.deletePostById(id);
    }
    public Post findPostById(Long id)
    {
        return postRepository.findPostById(id)
                .orElseThrow(()-> new PostNotFoundException("Post by id"+id+"not found"));

    }

    public List<Post> findAllPost()
    {return (List<Post>) postRepository.findAll();
    }

    public List<Post> findAllForum() {
        return (List<Post>) postRepository.findAll();
    }
}
