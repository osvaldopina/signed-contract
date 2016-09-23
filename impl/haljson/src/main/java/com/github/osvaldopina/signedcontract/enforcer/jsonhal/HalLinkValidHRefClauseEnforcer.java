package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.json.jsonpath.JsonValuePrinter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HalLinkValidHRefClauseEnforcer extends LeafClauseEnforcer<JsonNode> implements HalLinkPropertyClauseEnforcer {


    private String rel;



    protected List<EnforcementError> enforceClause(JsonNode documentClause) {

        List<EnforcementError> errors = new ArrayList<EnforcementError>();

        if (! documentClause.has("href")) {
            errors.add(new EnforcementError("Was expecting link to have a href property but it was not defined!"));
        }
        else if (! (documentClause.get("href") instanceof TextNode)) {
            errors.add(new EnforcementError("Was expecting link to have a string as a href property value!"));
        }
        else {
            String href = documentClause.get("href").asText();
            try {
                URL url = new URL(href);
            } catch (MalformedURLException e) {
                errors.add(new EnforcementError(new StringBuilder()
                        .append("Was expecting link to have a valid url as href property value!")
                        .toString()));
            }
        }
        return errors;
    }


}
