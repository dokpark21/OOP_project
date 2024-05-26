package com.example.comicserver.repository;

import com.example.comicserver.entity.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComicRepository extends MongoRepository<Comic, String> {
}
