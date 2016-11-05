package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.jsonpath.JsonPathClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HalEmptyResourceClauseEnforcer extends HalDocumentPartClauseEnforcer implements HalEmbeddedPartClauseEnforcer {


    public HalEmptyResourceClauseEnforcer() {
        super(Collections.EMPTY_LIST);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonStayPutNavigator();
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        List<EnforcementError> errors = new ArrayList<EnforcementError>();

        int nonResourceProperties = 0;
        if (documentClause.has("_links")) {
            nonResourceProperties++;
        }

        if (documentClause.has("_embedded")) {
            nonResourceProperties++;
        }

        if (documentClause.size() > nonResourceProperties) {
            errors.add(new EnforcementError("Was expecting to have empty resource but resource has "
                    + (documentClause.size() - nonResourceProperties) + " property(ies)."));
        }

        return errors;
    }
}
