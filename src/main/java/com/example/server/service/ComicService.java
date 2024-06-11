package com.example.server.service;

import com.example.server.dto.DescriptionRequest;
import com.example.server.model.Comic;
import com.example.server.model.ComicPost;
import com.example.server.repository.ComicPostRepository;
import com.example.server.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComicService {

    private final ComicPostRepository comicPostRepository;
    private final ComicRepository comicRepository;
    private final RestTemplate restTemplate;
    private final String dalleControllerUrl = "http://localhost:8080/api/dalle/image";

    @Autowired
    public ComicService(ComicPostRepository comicPostRepository, ComicRepository comicRepository,
            RestTemplate restTemplate) {
        this.comicPostRepository = comicPostRepository;
        this.comicRepository = comicRepository;
        this.restTemplate = restTemplate;
    }

    public Comic createComic(String title, String userId) {
        Comic comic = new Comic();
        comic.setTitle(title);
        comic.setUserId(userId);
        comic.setSceneIds(new ArrayList<>());
        return comicRepository.save(comic);
    }

    public ComicPost createComicPost(String description, String comicId, String userId) {
        // DalleController에 이미지 생성 요청
        DescriptionRequest request = new DescriptionRequest();
        request.setPrompt(description);
        String imageUrl = restTemplate.postForObject(dalleControllerUrl, request, String.class);

        // ComicPost 객체 생성 및 저장
        ComicPost comicPost = new ComicPost();
        comicPost.setDescription(description);
        comicPost.setImageUrl(imageUrl);
        comicPost.setComicId(comicId);
        comicPost.setUserId(userId);
        ComicPost savedPost = comicPostRepository.save(comicPost);

        // Comic 엔티티 업데이트
        Optional<Comic> comicOptional = comicRepository.findById(comicId);
        if (comicOptional.isPresent()) {
            Comic comic = comicOptional.get();
            comic.getSceneIds().add(savedPost.getId());
            comicRepository.save(comic);
        }

        return savedPost;
    }

    public void deleteComic(String comicId) {
        Optional<Comic> comicOptional = comicRepository.findById(comicId);
        if (comicOptional.isPresent()) {
            Comic comic = comicOptional.get();
            for (String sceneId : comic.getSceneIds()) {
                comicPostRepository.deleteById(sceneId);
            }
            comicRepository.deleteById(comicId);
        }
    }

    public List<Comic> getAllComics() {
        return comicRepository.findAll();
    }

    public List<ComicPost> getComicScenes(String comicId) {
        Optional<Comic> comicOptional = comicRepository.findById(comicId);
        if (comicOptional.isPresent()) {
            Comic comic = comicOptional.get();
            List<ComicPost> scenes = new ArrayList<>();
            for (String sceneId : comic.getSceneIds()) {
                comicPostRepository.findById(sceneId).ifPresent(scenes::add);
            }
            return scenes;
        }
        return new ArrayList<>();
    }
}
