package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {

    List<Post> findAllByUserOrderByCreatedDateDesc(User user);

    List<Post> finbdAllByCreatedDateDesc();

    Optional<Post> findPostByIdAndUser();

}
