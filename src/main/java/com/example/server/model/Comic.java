package com.example.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "comics")
public class Comic {
    @Id
    private String id;
    private String title;
    private String userId; // 작가의 user ID 추가
    private List<String> sceneIds; // ComicPost의 ID 목록

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSceneIds() {
        return sceneIds;
    }

    public void setSceneIds(List<String> sceneIds) {
        this.sceneIds = sceneIds;
    }
}
