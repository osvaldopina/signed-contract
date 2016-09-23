package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonPropertyNavigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class HalBaseEmbeddedResourceClauseEnforcer extends BranchClauseEnforcer<JsonNode, JsonNode> {

    private String rel;

    public HalBaseEmbeddedResourceClauseEnforcer(List<? extends ClauseEnforcer<JsonNode>> subClauseEnforcers) {
        super(subClauseEnforcers);
        this.rel = rel;
    }

    public String getRel() {
        return rel;
    }
}
