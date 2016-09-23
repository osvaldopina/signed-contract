package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.json.jsonpath.JsonValuePrinter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class HalLinkPropertyValueClauseEnforcer extends LeafClauseEnforcer<JsonNode> implements HalLinkPropertyClauseEnforcer {

    private String property;

    private Object expectedValue;

    private Object defaultValue;

    private String rel;


    public HalLinkPropertyValueClauseEnforcer(String property, Object expectedValue, Object defaultValue) {
        this.property = property;
        this.expectedValue = expectedValue;
        this.defaultValue = defaultValue;
    }

    public HalLinkPropertyValueClauseEnforcer(String property, Object expectedValue) {
        this.property = property;
        this.expectedValue = expectedValue;
    }

    protected List<EnforcementError> enforceClause(JsonNode documentClause) {

        List<EnforcementError> errors = new ArrayList<EnforcementError>();

        Object actualValue = null;

        try {
            actualValue = JsonPath.read(documentClause.toString(), "$." + property);
        } catch (PathNotFoundException e) {
            actualValue = defaultValue;
        }

        if (defaultValue == null && !documentClause.has(property)) {
            errors.add(new EnforcementError(new StringBuilder()
                    .append("Was expecting link property ")
                    .append(property)
                    .append(" to be ")
                    .append(JsonValuePrinter.print(expectedValue))
                    .append(" but it was not defined")
                    .toString()));
        } else if (!actualValue.equals(expectedValue)) {
            errors.add(new EnforcementError(new StringBuilder()
                    .append("Was expecting link property ")
                    .append(property)
                    .append(" to be ")
                    .append(JsonValuePrinter.print(expectedValue))
                    .append(" but it was ")
                    .append(JsonValuePrinter.print(actualValue))
                    .toString()));
        }
        return errors;
    }

}
