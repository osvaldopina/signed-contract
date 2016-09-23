package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;

import java.util.Collections;
import java.util.List;

public class HalLinkPropertyAggregatorClause extends BranchClauseEnforcer<JsonNode, JsonNode> implements HalLinkPropertyClauseEnforcer {

    private String rel;

    public HalLinkPropertyAggregatorClause(List<? extends HalLinkPropertyClauseEnforcer> subClauseEnforcers) {
        super(subClauseEnforcers);
        for(HalLinkPropertyClauseEnforcer subClauseEnforcer:subClauseEnforcers) {
            if (subClauseEnforcer instanceof Rel) {
                ((Rel) subClauseEnforcer).setRel(rel);
            }
        }
    }

    @Override
    protected Navigator createNavigator() {
        return new JsonStayPutNavigator();
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
