package com.playwright.code.challenge.api.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.playwright.code.challenge.api.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class Endpoint {

    private final ApiClient apiClient;

    public Endpoint(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public APIResponse createTask(JsonNode body) {
        return apiClient.post("/tasks", body);
    }

    public APIResponse getTask(long id) {
        return apiClient.get("/account/" + id);
    }

    public APIResponse updateTask(long id, JsonNode body) {
        return apiClient.put("/account/" + id, body);
    }

    public APIResponse deleteTask(long id) {
        return apiClient.delete("/account/" + id);
    }
}
