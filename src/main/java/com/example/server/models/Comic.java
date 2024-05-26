package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comics")
public class Comic {
    @Id
    private String id;
    private String title;
    private String description;
    
    @DBRef
    private User user;
}
