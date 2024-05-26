package com.example.comicserver.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DalleService {
    private static final String API_URL = "https://api.openai.com/v1/images/generations";
    private static final String API_KEY = "your-openai-api-key";

    public String generateImage(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\":\"" + prompt + "\",\"n\":1,\"size\":\"1024x1024\"}");
        Request request = new Request.Builder()
            .url(API_URL)
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer " + API_KEY)
            .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            // Parse the response and extract the image URL
            return responseBody; // Simplified for brevity
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
