package com.example.demo.repository;

import com.example.comicserver.entity.ComicPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComicPostRepository extends MongoRepository<ComicPost, String> {
}
