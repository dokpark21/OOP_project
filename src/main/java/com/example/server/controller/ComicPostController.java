package com.example.server.controller;

import com.example.server.dto.ComicPostRequest;
import com.example.server.model.ComicPost;
import com.example.server.service.ComicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comicposts")
public class ComicPostController {
    private final ComicPostService comicPostService;

    @Autowired
    public ComicPostController(ComicPostService comicPostService) {
        this.comicPostService = comicPostService;
    }

    @PostMapping("/create")
    public ResponseEntity<ComicPost> createComicPost(@RequestBody ComicPostRequest request) {
        try {
            ComicPost comicPost = comicPostService.createComicPost(request.getDescription(), request.getComicId(),
                    request.getUserId());
            return new ResponseEntity<>(comicPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{comicId}")
    public ResponseEntity<Void> deleteComic(@PathVariable String comicId) {
        try {
            comicPostService.deleteComic(comicId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
