package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HalEmptyEmbeddedClauseEnforcer extends HalDocumentPartClauseEnforcer implements HalEmbeddedPartClauseEnforcer {


    public HalEmptyEmbeddedClauseEnforcer() {
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

         if (documentClause.has("_embedded")) {
            errors.add(new EnforcementError("Was expecting not to have _embedded."));
        }

        return errors;
    }
}
