package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.Document;

public class HalDocument implements Document<JsonNode> {

    private HalDocumentClauseEnforcer halDocumentClauseEnforcer;

    public HalDocument(HalDocumentClauseEnforcer halDocumentClauseEnforcer) {
        this.halDocumentClauseEnforcer = halDocumentClauseEnforcer;
    }


    @Override
    public Clause<JsonNode> enforce(JsonNode document) {
        return halDocumentClauseEnforcer.enforce(document);
    }
}
