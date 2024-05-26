package com.example.demo.controller;

import com.example.comicserver.service.DalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/dalle")
public class DalleController {
    @Autowired
    private DalleService dalleService;

    @PostMapping("/generate")
    public String generateImage(@RequestBody String prompt) {
        try {
            return dalleService.generateImage(prompt);
        } catch (IOException e) {
            return "Error generating image: " + e.getMessage();
        }
    }
}
