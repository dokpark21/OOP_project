package com.example.demo.controller;

import com.example.demo.model.Comic;
import com.example.demo.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comics")
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

    @PutMapping("/{id}")
    public Comic updateComic(@PathVariable String id, @RequestBody Comic comic) {
        return comicService.updateComic(id, comic);
    }

    @DeleteMapping("/{id}")
    public String deleteComic(@PathVariable String id) {
        comicService.deleteComic(id);
        return "Comic deleted successfully";
    }
}
