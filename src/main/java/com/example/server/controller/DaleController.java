package com.example.server.controller;

import com.example.server.service.DalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/dalle")
public class DaleController {

    @Autowired
    private DalleService dalleService;

    @PostMapping("/generate")
    public String generateImage(@RequestParam String prompt) {
        try {
            return dalleService.generateImage(prompt);
        } catch (IOException e) {
            return "Error generating image: " + e.getMessage();
        }
    }
}
