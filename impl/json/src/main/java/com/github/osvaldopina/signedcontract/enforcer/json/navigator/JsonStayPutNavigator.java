package com.github.osvaldopina.signedcontract.enforcer.json.navigator;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

public class JsonStayPutNavigator implements Navigator<JsonNode, JsonNode> {


    @Override
    public JsonNode navigate(JsonNode document) {
        return document;
    }
}
