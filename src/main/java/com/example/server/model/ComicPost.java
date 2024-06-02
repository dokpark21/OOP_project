package com.example.server.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comicposts")
public class ComicPost {
    @Id
    private String id;
    private String comicId;
    private String imageUrl;
    private String description;
}
