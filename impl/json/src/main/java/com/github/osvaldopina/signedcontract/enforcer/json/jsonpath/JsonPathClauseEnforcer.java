package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.LeafClauseEnforcer;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.List;

public abstract class JsonPathClauseEnforcer extends LeafClauseEnforcer<JsonNode> {

    private String path;

    public JsonPathClauseEnforcer(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    protected final List<EnforcementError> enforceClause(JsonNode documentClause) {

        try {

            Object value = JsonPath.read(documentClause.toString(), path);

            return validate(value);

        }
        catch(PathNotFoundException e) {
            return validate(null);
        }
    }

    protected abstract List<EnforcementError> validate(Object actual);




}
