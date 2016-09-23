package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonPropertyNavigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalEmbeddedListClauseEnforcer extends HalDocumentPartClauseEnforcer implements HalEmbeddedPartClauseEnforcer{


    public HalEmbeddedListClauseEnforcer(List<? extends HalBaseEmbeddedResourceClauseEnforcer> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonPropertyNavigator("_embedded");
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        if (documentClause == null) {
            return Arrays.asList(new EnforcementError(
                    new StringBuilder()
                            .append("Was expecting a hal document to have a _embedded property it hasn't")
                            .toString()));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }
}
