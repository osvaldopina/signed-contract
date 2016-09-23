package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

public class JsonPathDSLHelper {

    public static JsonPathValueClauseEnforcer is(String jsonPath, String value) {
        return new JsonPathValueClauseEnforcer(jsonPath, value);
    }

    public static JsonPathValueClauseEnforcer is(String jsonPath, boolean value) {
        return new JsonPathValueClauseEnforcer(jsonPath, value);
    }

    public static JsonPathValueClauseEnforcer is(String jsonPath, Number value) {
        return new JsonPathValueClauseEnforcer(jsonPath, value);
    }

    public static JsonPathTypeClauseEnforcer isString(String jsonPath) {
        return new JsonPathTypeClauseEnforcer(jsonPath, JSON_TYPE.STRING);
    }

    public static JsonPathTypeClauseEnforcer isArray(String jsonPath) {
        return new JsonPathTypeClauseEnforcer(jsonPath, JSON_TYPE.ARRAY);
    }

    public static JsonPathTypeClauseEnforcer isNumber(String jsonPath) {
        return new JsonPathTypeClauseEnforcer(jsonPath, JSON_TYPE.NUMBER);
    }

    public static JsonPathTypeClauseEnforcer isBoolean(String jsonPath) {
        return new JsonPathTypeClauseEnforcer(jsonPath, JSON_TYPE.BOOLEAN);
    }

    public static JsonPathTypeClauseEnforcer isObject(String jsonPath) {
        return new JsonPathTypeClauseEnforcer(jsonPath, JSON_TYPE.OBJECT);
    }
}
