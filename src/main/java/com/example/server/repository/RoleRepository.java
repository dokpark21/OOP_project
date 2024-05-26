package com.example.demo.repository;

import com.example.comicserver.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
