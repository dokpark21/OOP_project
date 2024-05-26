package com.example.comicserver.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
