package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.uritemplate.UriTemplatedVariableClauseEnforcer;

import java.util.Collections;
import java.util.List;

public class HalLinkTemplatedUriClauseEnforcer extends BranchClauseEnforcer<JsonNode, UriTemplate> implements HalLinkPropertyClauseEnforcer {

    private String rel;

    public HalLinkTemplatedUriClauseEnforcer(List<UriTemplatedVariableClauseEnforcer> subClauses) {
        super(subClauses);
    }

    @Override
    protected Navigator<JsonNode, UriTemplate> createNavigator() {
        return new HalLinkTemplatedUriTemplateNavigator();
    }

    @Override
    protected List<EnforcementError> verifyNavigation(UriTemplate documentClause) {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected List<EnforcementError> enforceClause(JsonNode documentClause) {
        return Collections.EMPTY_LIST;
    }

}
