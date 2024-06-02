package com.example.server.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DalleService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/images/generations";
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    public DalleService() {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String generateImage(String prompt) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("prompt", prompt)
                .add("n", "1")
                .add("size", "1024x1024")
                .build();

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + openAiApiKey)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("data").get(0).get("url").asText();
        }
    }
}
