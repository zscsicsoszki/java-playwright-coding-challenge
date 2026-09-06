package com.playwright.code.challenge.api.client;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.springframework.stereotype.Component;

@Component
public class ApiClient {

    private final APIRequestContext api;

    public ApiClient(APIRequestContext api) {
        this.api = api;
    }

    public APIResponse get(String path) {
        return api.get(path);
    }

    public APIResponse post(String path, Object body) {
        return api.post(path, RequestOptions.create().setData(body));
    }

    public APIResponse put(String path, Object body) {
        return api.put(path, RequestOptions.create().setData(body));
    }

    public APIResponse delete(String path) {
        return api.delete(path);
    }
}
