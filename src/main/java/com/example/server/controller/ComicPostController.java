package com.example.demo.controller;

import com.example.comicserver.entity.ComicPost;
import com.example.comicserver.service.ComicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comicposts")
public class ComicPostController {
    @Autowired
    private ComicPostService comicPostService;

    @PostMapping
    public ComicPost createComicPost(@RequestBody ComicPost comicPost) {
        return comicPostService.createComicPost(comicPost);
    }

    @GetMapping
    public List<ComicPost> getAllComicPosts() {
        return comicPostService.getAllComicPosts();
    }

    @GetMapping("/{id}")
    public ComicPost getComicPostById(@PathVariable String id) {
        return comicPostService.getComicPostById(id);
    }
}
