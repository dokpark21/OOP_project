package com.example.server.controller;

import com.example.server.dto.ComicPostRequest;
import com.example.server.dto.ComicRequest;
import com.example.server.dto.ComicScenesRequest;
import com.example.server.model.Comic;
import com.example.server.model.ComicPost;
import com.example.server.service.ComicService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comics")
public class ComicController {

    private final ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @PostMapping("/createComic")
    public ResponseEntity<Comic> createComic(@RequestBody ComicRequest comicRequest) {
        try {
            Comic comic = comicService.createComic(comicRequest.getTitle(), comicRequest.getUserId());
            return new ResponseEntity<>(comic, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/scenes")
    public ResponseEntity<ComicPost> createComicPost(@RequestBody ComicPostRequest request) {
        try {
            ComicPost comicPost = comicService.createComicPost(request.getDescription(), request.getComicId(),
                    request.getUserId());
            return new ResponseEntity<>(comicPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteComic")
    public ResponseEntity<HttpStatus> deleteComic(@RequestBody String comicId) {
        try {
            comicService.deleteComic(comicId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comic>> getAllComics() {
        try {
            List<Comic> comics = comicService.getAllComics();
            return new ResponseEntity<>(comics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/scenes")
    public ResponseEntity<List<ComicPost>> getComicScenes(@RequestBody ComicScenesRequest request) {
        try {
            List<ComicPost> scenes = comicService.getComicScenes(request.getComicId());
            return new ResponseEntity<>(scenes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
