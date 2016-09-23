package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.jsonpath.JsonPathClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;

import java.util.Collections;
import java.util.List;

public class HalResourceClauseEnforcer extends HalDocumentPartClauseEnforcer implements HalEmbeddedPartClauseEnforcer  {


    public HalResourceClauseEnforcer(List<JsonPathClauseEnforcer> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new HalJsonResourceOnlyNavigator();
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }
}
