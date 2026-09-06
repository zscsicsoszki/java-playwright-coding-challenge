package com.playwright.code.challenge.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import lombok.SneakyThrows;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public class JsonHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    public <T> JsonNode read(APIResponse response) {
        try {
            return mapper.readTree(response.text());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode readJson(String body) {
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(body);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON path is provided!", e);
        }
        return jsonNode;
    }

    public JsonNode readRequestPayload(String fileName) {
        return readJsonFile("request/" + fileName);
    }

    public JsonNode readResponsePayload(String fileName) {
        return readJsonFile("response/" + fileName);
    }

    @SneakyThrows
    public JsonNode readJsonFile(String relativeFilePath) {
        return mapper.readTree(resourceLoader.getResource("data/" + relativeFilePath)
                        .getInputStream());
    }
}
