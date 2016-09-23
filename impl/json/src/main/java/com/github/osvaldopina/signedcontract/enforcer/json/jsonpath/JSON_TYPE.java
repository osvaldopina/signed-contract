package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import java.util.List;
import java.util.Map;

public enum JSON_TYPE {

    STRING,
    ARRAY,
    OBJECT,
    NUMBER,
    BOOLEAN;

    public static JSON_TYPE fromJavaType(Object object) {
        if (object == null) {
            throw new IllegalStateException("Cannot determine jsonpath type from null value!");
        }
        else {
            return fromJavaType(object.getClass());
        }
    }

    public static JSON_TYPE fromJavaType(Class<?> javaType) {
        if (javaType == null) {
            throw new IllegalStateException("Cannot determine jsonpath type from null value");
        }
        if (List.class.isAssignableFrom(javaType)) {
            return ARRAY;
        }
        else if (String.class.isAssignableFrom(javaType)) {
            return STRING;
        }
        else if (Number.class.isAssignableFrom(javaType)) {
            return NUMBER;
        }
        else if (Boolean.class.isAssignableFrom(javaType)) {
            return BOOLEAN;
        }
        else if (Map.class.isAssignableFrom(javaType)) {
            return OBJECT;
        }
        else {
            throw new IllegalStateException("class " + javaType.getName() + " is not a valid jsonpath type");
        }

    }


}
