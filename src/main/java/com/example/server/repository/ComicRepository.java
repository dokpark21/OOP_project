package com.example.server.repository;

import com.example.server.model.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComicRepository extends MongoRepository<Comic, String> {
}
