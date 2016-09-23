package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalDocumentClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode> {

    public HalDocumentClauseEnforcer(List<? extends ClauseEnforcer<JsonNode>> subClauses) {
        super(subClauses);
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {

        if ((!documentClause.isObject())) {
            return Arrays.asList(new EnforcementError("Was expecting a hal document to be a json document but it is a " +
                    documentClause.getNodeType()));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonStayPutNavigator();
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }
}
