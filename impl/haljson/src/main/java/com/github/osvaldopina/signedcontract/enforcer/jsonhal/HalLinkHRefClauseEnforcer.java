package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;

import java.util.ArrayList;
import java.util.List;

public class HalLinkHRefClauseEnforcer extends LeafClauseEnforcer<JsonNode> implements HalLinkPropertyClauseEnforcer {


    private String rel;

    private String href;

    public HalLinkHRefClauseEnforcer(String href) {
        this.href = href;
    }

    protected List<EnforcementError> enforceClause(JsonNode documentClause) {

        List<EnforcementError> errors = new ArrayList<EnforcementError>();

        if (!documentClause.has("href")) {
            errors.add(new EnforcementError("Was expecting link to have a href property but it was not defined!"));
        } else if (!(documentClause.get("href") instanceof TextNode)) {
            errors.add(new EnforcementError("Was expecting link to have a string as a href property value!"));
        } else {
            String documentHref = documentClause.get("href").asText();
            if (!documentHref.equals(href)) {
                errors.add(new EnforcementError("Was expecting link href to be " +
                        href + " but it was " + documentHref));
            }
        }
        return errors;
    }


}
