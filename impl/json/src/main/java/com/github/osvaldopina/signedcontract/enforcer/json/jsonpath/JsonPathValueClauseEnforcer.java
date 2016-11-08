package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonPathValueClauseEnforcer extends JsonPathClauseEnforcer {

    private Object expected;

    public JsonPathValueClauseEnforcer(String path, Object expected) {
        super(path);
        this.expected = expected;
    }

    @Override
    protected List<EnforcementError> validate(Object actual) {

        if (actual == null) {
            if (expected != null) {
                return Arrays.asList(new EnforcementError(
                        new StringBuilder()
                                .append("Was expecting ")
                                .append(JsonValuePrinter.print(expected))
                                .append(" for json path ")
                                .append(getPath())
                                .append(" but it was null")
                                .toString()));
            }
            else {
                return Collections.emptyList();
            }
        }
        else if (!actual.equals(expected)) {
            return Arrays.asList(new EnforcementError(
                    new StringBuilder()
                            .append("Was expecting ")
                            .append(JsonValuePrinter.print(expected))
                            .append(" for json path ")
                            .append(getPath())
                            .append(" but it was ")
                            .append(JsonValuePrinter.print(actual))
                            .toString()));

        } else {
            return Collections.emptyList();
        }
    }

}
