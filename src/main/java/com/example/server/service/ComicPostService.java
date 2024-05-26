package com.example.demo.service;

import com.example.demo.model.ComicPost;
import com.example.demo.repository.ComicPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicPostService {
    @Autowired
    private ComicPostRepository comicPostRepository;

    public ComicPost createComicPost(ComicPost comicPost) {
        return comicPostRepository.save(comicPost);
    }

    public List<ComicPost> getAllComicPosts() {
        return comicPostRepository.findAll();
    }

    public List<ComicPost> getComicPostsByComicId(String comicId) {
        return comicPostRepository.findByComicId(comicId);
    }

    public ComicPost getComicPostById(String id) {
        return comicPostRepository.findById(id).orElse(null);
    }

    public ComicPost updateComicPost(String id, ComicPost comicPost) {
        ComicPost existingComicPost = comicPostRepository.findById(id).orElse(null);
        if (existingComicPost != null) {
            existingComicPost.setImageUrl(comicPost.getImageUrl());
            existingComicPost.setDescription(comicPost.getDescription());
            existingComicPost.setComicId(comicPost.getComicId());
            return comicPostRepository.save(existingComicPost);
        }
        return null;
    }

    public void deleteComicPost(String id) {
        comicPostRepository.deleteById(id);
    }
}
