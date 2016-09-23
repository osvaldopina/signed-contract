package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonArrayItemNavigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalEmbeddedArrayItemClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode>   {

    private int index;

    private String rel;

    public HalEmbeddedArrayItemClauseEnforcer(List<? extends HalEmbeddedPartClauseEnforcer> subClauseEnforcers) {
        super(subClauseEnforcers);
    }

    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonArrayItemNavigator(index);
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
        for(ClauseEnforcer<JsonNode> subClause:getSubClauseEnforcers()) {
            if (subClause instanceof Rel) {
                ((Rel) subClause).setRel(rel);
            }
        }
    }
}
