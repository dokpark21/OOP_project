package com.example.server.controller;

import com.example.server.dto.DescriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> generateImage(@RequestBody DescriptionRequest request) {
        try {
            String prompt = request.getPrompt();
            if (prompt == null || prompt.isEmpty()) {
                return new ResponseEntity<>("Prompt is required", HttpStatus.BAD_REQUEST);
            }

            CreateImageRequest imageRequest = CreateImageRequest.builder()
                    .prompt(prompt)
                    .n(1)
                    .size("1024x1024")
                    .build();
            ImageResult result = openAiService.createImage(imageRequest);
            String imageUrl = result.getData().get(0).getUrl();
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
