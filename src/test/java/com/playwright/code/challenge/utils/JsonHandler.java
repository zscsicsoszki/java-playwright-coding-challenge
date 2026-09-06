package com.playwright.code.challenge.utils;

import com.microsoft.playwright.APIResponse;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T read(APIResponse response, Class<T> clazz) {
        try {
            return mapper.readValue(response.text(), clazz);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
