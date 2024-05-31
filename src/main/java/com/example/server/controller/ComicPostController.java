package com.example.server.controller;

import com.example.server.model.ComicPost;
import com.example.server.service.ComicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comicposts")
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

    @GetMapping("/comic/{comicId}")
    public List<ComicPost> getComicPostsByComicId(@PathVariable String comicId) {
        return comicPostService.getComicPostsByComicId(comicId);
    }

    @GetMapping("/{id}")
    public ComicPost getComicPostById(@PathVariable String id) {
        return comicPostService.getComicPostById(id);
    }

    @PutMapping("/{id}")
    public ComicPost updateComicPost(@PathVariable String id, @RequestBody ComicPost comicPost) {
        return comicPostService.updateComicPost(id, comicPost);
    }

    @DeleteMapping("/{id}")
    public String deleteComicPost(@PathVariable String id) {
        comicPostService.deleteComicPost(id);
        return "Comic post deleted successfully";
    }
}
