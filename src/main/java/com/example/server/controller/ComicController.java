package com.example.demo.controller;

import com.example.comicserver.entity.Comic;
import com.example.comicserver.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @PostMapping
    public Comic createComic(@RequestBody Comic comic) {
        return comicService.createComic(comic);
    }

    @GetMapping
    public List<Comic> getAllComics() {
        return comicService.getAllComics();
    }

    @GetMapping("/{id}")
    public Comic getComicById(@PathVariable String id) {
        return comicService.getComicById(id);
    }
}
