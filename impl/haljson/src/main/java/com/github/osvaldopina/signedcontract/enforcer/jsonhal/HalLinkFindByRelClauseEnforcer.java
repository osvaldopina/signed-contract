package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonPropertyNavigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HalLinkFindByRelClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode>
        implements HalLinksClauseEnforcer {

    private String rel;

    public HalLinkFindByRelClauseEnforcer(String rel, List<? extends HalLinkPropertyClauseEnforcer> subClauses) {
        super(subClauses);
        for(HalLinkPropertyClauseEnforcer halLinkPropertyClauseEnforcer:subClauses) {
           if (halLinkPropertyClauseEnforcer instanceof Rel) {
               ((Rel) halLinkPropertyClauseEnforcer).setRel(rel);
           }
        }
        this.rel = rel;
    }


    @Override
    protected Navigator<JsonNode, JsonNode> createNavigator() {
        return new JsonPropertyNavigator(rel);
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected List<EnforcementError> verifyNavigation(JsonNode documentClause) {
        if (documentClause == null) {
            return Arrays.asList(new EnforcementError(
                    new StringBuilder()
                            .append("Was expecting to find a hal link with rel [")
                            .append(rel)
                            .append("] but it was not found!")
                            .toString()));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public String getRel() {
        return rel;
    }
}
