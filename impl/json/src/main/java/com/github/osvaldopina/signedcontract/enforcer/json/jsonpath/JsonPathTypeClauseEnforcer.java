package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonPathTypeClauseEnforcer extends JsonPathClauseEnforcer {

    private JSON_TYPE expected;

    public JsonPathTypeClauseEnforcer(String path, JSON_TYPE expected) {
        super(path);
        this.expected = expected;
    }

    @Override
    protected List<EnforcementError> validate(Object actual) {

        if (actual == null) {
            return Arrays.asList(new EnforcementError(
                    new StringBuilder()
                            .append("Was expecting a type ")
                            .append(expected)
                            .append(" for json path ")
                            .append(getPath())
                            .append(" but the path was not found in json document")
                            .toString()));
        }
        else if (JSON_TYPE.fromJavaType(actual) != expected) {
            return Arrays.asList(new EnforcementError(
                    new StringBuilder()
                            .append("Was expecting a type ")
                            .append(expected)
                            .append(" for json path ")
                            .append(getPath())
                            .append(" but it was ")
                            .append(JSON_TYPE.fromJavaType(actual))
                            .toString()));
        } else {
            return Collections.emptyList();
        }
    }

}
