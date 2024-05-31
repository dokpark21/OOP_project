package com.example.server.repository;

import com.example.server.model.BlacklistedToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenBlacklistRepository extends MongoRepository<BlacklistedToken, String> {
    boolean existsByToken(String token);
}
