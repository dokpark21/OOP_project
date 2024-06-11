package com.example.server.repository;

import com.example.server.model.ComicPost;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComicPostRepository extends MongoRepository<ComicPost, String> {
    void deleteByComicId(String comicId);

    List<ComicPost> findByComicId(String comicId);
}
