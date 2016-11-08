package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonArrayItemNavigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonStayPutNavigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HalEmbeddedArrayItemCountClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode>
        implements HalEmbeddedArrayItemBaseClauseEnforcer {

    int arrayItemCount;

    public HalEmbeddedArrayItemCountClauseEnforcer(int arrayItemCount) {
        super(Collections.EMPTY_LIST);
        this.arrayItemCount = arrayItemCount;
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

        if (documentClause.size() != arrayItemCount) {
            errors.add(new EnforcementError("Was expecting to have " + arrayItemCount +
                    " array item(s) but _embedded has " +
                    documentClause.size() + " array item(s)."));
        }

        return errors;
    }

    public void setIndex(int index) {
    }

    public void setRel(String rel) {
    }

    @Override
    public boolean consumesIndex() {
        return false;
    }
}
