package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;

import java.util.ArrayList;
import java.util.List;

public class HalLinkCountClauseEnforcer extends LeafClauseEnforcer<JsonNode> implements HalLinksClauseEnforcer{

    private int linkCount;

    public HalLinkCountClauseEnforcer(int linkCount) {
        super();
        this.linkCount = linkCount;
    }


    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {

        List<EnforcementError> errors = new ArrayList<EnforcementError>();

        if (documentClause.size() != linkCount) {
            errors.add(new EnforcementError("Was expecting to have " + linkCount + " link(s) but _links has " +
                    documentClause.size() + " link(s)."));
        }

        return errors;
    }

}
