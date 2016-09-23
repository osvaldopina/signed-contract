package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

public class JsonValuePrinter {

    public static String print(Object value) {
        return new StringBuilder()
                .append((value instanceof String)?"[\"":"[")
                .append(value)
                .append((value instanceof String)?"\"]":"]")
                .toString();
    }
}
