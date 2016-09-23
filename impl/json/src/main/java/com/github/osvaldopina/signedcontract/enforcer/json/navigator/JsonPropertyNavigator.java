package com.github.osvaldopina.signedcontract.enforcer.json.navigator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

public class JsonPropertyNavigator implements Navigator<JsonNode, JsonNode> {

    private String propertyName;

    public JsonPropertyNavigator(String propertyName) {
        this.propertyName = propertyName;
    }


    @Override
    public JsonNode navigate(JsonNode document) {
        return document.get(propertyName);
    }


}
