package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonPropertyNavigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalEmbeddedArrayClauseEnforcer extends HalBaseEmbeddedResourceClauseEnforcer {

    private String rel;

    public HalEmbeddedArrayClauseEnforcer(String rel, List<HalEmbeddedArrayItemClauseEnforcer> subClauseEnforcers) {
        super(subClauseEnforcers);
        this.rel = rel;
        for(int i=0; i< subClauseEnforcers.size(); i++) {
            subClauseEnforcers.get(i).setIndex(i);
            subClauseEnforcers.get(i).setRel(rel);
        }
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonPropertyNavigator(rel);
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        if (! documentClause.has(rel)) {
            return Arrays.asList(new EnforcementError(
                    new StringBuilder()
                            .append("Was expecting to find a hal embedded with rel [")
                            .append(rel)
                            .append("] but it was not found!")
                            .toString(), true));
        }
        JsonNode embeddedDocument = documentClause.get(rel);

        if (! (embeddedDocument instanceof ArrayNode)) {
            return Arrays.asList(new EnforcementError("Was expecting a json array as embedded resource but it was " +
                    documentClause.getNodeType(), true));
        }
        return Collections.EMPTY_LIST;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
