package com.github.osvaldopina.signedcontract.enforcer.json.navigator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;

public class JsonArrayItemNavigator implements Navigator<JsonNode, JsonNode> {

    private int index;

    public JsonArrayItemNavigator(int index) {
        this.index = index;
    }


    @Override
    public JsonNode navigate(JsonNode document) {
        if (! (document instanceof ArrayNode)) {
            throw new SignedContractException("Was expecting a json array but it was " + document.getNodeType());
        }
        ArrayNode arrayNode = (ArrayNode) document;

        if (index >= arrayNode.size()) {
            throw new SignedContractException("Trying to get array index "  + index + " but the array size is " +
                    arrayNode.size());
        }
        return ((ArrayNode) document).get(index);
    }


}
