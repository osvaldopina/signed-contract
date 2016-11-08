package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonPropertyNavigator;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalLinkListClauseEnforcer extends  HalDocumentPartClauseEnforcer implements HalEmbeddedPartClauseEnforcer {


    public HalLinkListClauseEnforcer(List<HalLinksClauseEnforcer> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonPropertyNavigator("_links");
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        if (documentClause == null) {
            return Arrays.asList(
                    new EnforcementError("Was expecting a hal document to have a _links property it hasn't"));
        }
        else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }
}
