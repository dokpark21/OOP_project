package com.example.server.service;

import com.example.server.model.Comic;
import com.example.server.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService {
    @Autowired
    private ComicRepository comicRepository;

    public Comic createComic(Comic comic) {
        return comicRepository.save(comic);
    }

    public List<Comic> getAllComics() {
        return comicRepository.findAll();
    }

    public Comic getComicById(String id) {
        return comicRepository.findById(id).orElse(null);
    }

    public void deleteComic(String id) {
        comicRepository.deleteById(id);
    }
}
