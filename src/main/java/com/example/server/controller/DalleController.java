package com.example.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.ImageResult;

@RestController
@RequestMapping("/api/dalle")
public class DalleController {

    private final OpenAiService openAiService;

    @Autowired
    public DalleController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/image")
    public ResponseEntity<?> generateImage(@RequestBody String prompt) {
        try {
            String imageUrl = generatePicture(prompt);
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generatePicture(String prompt) {
        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(prompt)
                .n(1)
                .size("1024x1024")
                .build();

        ImageResult result = openAiService.createImage(request);
        if (result == null || result.getData().isEmpty()) {
            throw new RuntimeException("No image returned from OpenAI.");
        }
        return result.getData().get(0).getUrl();
    }
}
