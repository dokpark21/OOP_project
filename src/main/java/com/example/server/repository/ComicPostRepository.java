package com.example.server.repository;

import com.example.server.model.ComicPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ComicPostRepository extends MongoRepository<ComicPost, String> {
    void deleteByComicId(String comicId);

    List<ComicPost> findByComicId(String comicId);

    List<ComicPost> findByComicIdAndAccepted(String comicId, boolean accepted);
}
