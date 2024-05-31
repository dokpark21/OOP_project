package com.example.server.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role implements GrantedAuthority {
    @Id
    private String id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
