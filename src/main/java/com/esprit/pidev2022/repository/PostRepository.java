package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Comment;
import com.esprit.pidev2022.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    void deletePostById(Long id);

    Optional<Post> findPostById(Long id);
}
