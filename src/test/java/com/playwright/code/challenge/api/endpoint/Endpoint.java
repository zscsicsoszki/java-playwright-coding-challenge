package com.playwright.code.challenge.api.endpoint;

import com.microsoft.playwright.APIResponse;
import com.playwright.code.challenge.api.client.ApiClient;
import com.playwright.code.challenge.api.model.CreateRequest;
import com.playwright.code.challenge.api.model.UpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class Endpoint {

    private final ApiClient apiClient;

    public Endpoint(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public APIResponse createTask(CreateRequest request) {
        return apiClient.post("/tasks", request);
    }

    public APIResponse getTask(long id) {
        return apiClient.get("/account/" + id);
    }

    public APIResponse updateTask(long id, UpdateRequest request) {
        return apiClient.put("/account/" + id, request);
    }

    public APIResponse deleteTask(long id) {
        return apiClient.delete("/account/" + id);
    }
}
