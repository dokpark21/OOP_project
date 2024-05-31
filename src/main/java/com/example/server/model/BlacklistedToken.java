package com.example.server.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "blacklistedTokens")
public class BlacklistedToken {
    @Id
    private String id;
    private String token;
    private long expiration;
}
