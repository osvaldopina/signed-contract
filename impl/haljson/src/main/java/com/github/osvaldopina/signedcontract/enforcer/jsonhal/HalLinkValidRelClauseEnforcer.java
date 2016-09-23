package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HalLinkValidRelClauseEnforcer extends LeafClauseEnforcer<JsonNode>
        implements HalLinkPropertyClauseEnforcer, Rel, HalEmbeddedPartClauseEnforcer {


    private String rel;

    private List<String> ianaRels = IanaRels.RELS;


    public HalLinkValidRelClauseEnforcer() {
    }


    protected List<EnforcementError> enforceClause(JsonNode documentClause) {

        List<EnforcementError> errors = new ArrayList<EnforcementError>();

        if (!ianaRels.contains(rel)) {
            try {
                if (!new URI(rel).isAbsolute()) {
                    errors.add(new EnforcementError("Was expecting link rel to be a absolute URI but was not!"));
                }
            } catch (URISyntaxException e) {
                errors.add(new EnforcementError("Was expecting link rel to be a absolute URI or a IANA rel but was not!"));
            }
        }
        return errors;
    }

    @Override
    public String getRel() {
        return rel;
    }

    @Override
    public void setRel(String rel) {
        this.rel = rel;
    }
}
