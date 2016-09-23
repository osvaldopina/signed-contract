package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;

import java.util.Arrays;

public class HalJsonResourceOnlyNavigator implements Navigator<JsonNode, JsonNode> {


    @Override
    public JsonNode navigate(JsonNode documentClause) {
        if (! (documentClause instanceof ObjectNode)) {
            throw new SignedContractException("Hal json document must be a json object");
        }
        ObjectNode copiedDocumentClause = ((ObjectNode) documentClause).deepCopy();
        copiedDocumentClause.remove(Arrays.asList("_links", "_embedded"));
        return copiedDocumentClause;
    }
}
