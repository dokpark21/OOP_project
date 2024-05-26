package com.example.demo.repository;

import com.example.demo.model.ComicPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComicPostRepository extends MongoRepository<ComicPost, String> {
    List<ComicPost> findByComicId(String comicId);
}
