package com.playwright.code.challenge.stepdefinitions.api.types;

import com.fasterxml.jackson.databind.JsonNode;
import com.playwright.code.challenge.utils.JsonHandler;
import io.cucumber.java.DocStringType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParameterTypes {
    private final JsonHandler jsonHandler;

    @DocStringType
    public JsonNode json(String docString) {
        return jsonHandler.readJson(docString);
    }
}
