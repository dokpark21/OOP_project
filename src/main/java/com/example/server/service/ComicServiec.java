package com.example.demo.service;

import com.example.demo.model.Comic;
import com.example.demo.repository.ComicRepository;
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

    public Comic updateComic(String id, Comic comic) {
        Comic existingComic = comicRepository.findById(id).orElse(null);
        if (existingComic != null) {
            existingComic.setTitle(comic.getTitle());
            existingComic.setDescription(comic.getDescription());
            existingComic.setComicPosts(comic.getComicPosts());
            existingComic.setUser(comic.getUser());
            return comicRepository.save(existingComic);
        }
        return null;
    }

    public void deleteComic(String id) {
        comicRepository.deleteById(id);
    }
}
