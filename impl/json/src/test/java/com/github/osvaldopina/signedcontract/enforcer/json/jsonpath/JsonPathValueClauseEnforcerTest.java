package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonPathValueClauseEnforcerTest {

    private JsonPathValueClauseEnforcer jsonPathValueClauseEnforcer;

    @Test
    public void validateString() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", "prop1-value");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : \"prop1-value\" }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        assertTrue(clause.getErrors().isEmpty());

    }

    @Test
    public void validateWrongString() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", "prop1-value");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : \"prop2-value\" }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        assertEquals(1, clause.getErrors().size());
        assertEquals("Was expecting [\"prop1-value\"] for json path $.prop1 but it was [\"prop2-value\"]",
                clause.getErrors().get(0).toString());


    }


    @Test
    public void validateNumber() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", 123);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : 123 }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        System.out.println(clause.getErrors().isEmpty());

    }

    @Test
    public void validateWrongNumber() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", 123);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : \"a\" }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        assertEquals(1, clause.getErrors().size());
        assertEquals("Was expecting [123] for json path $.prop1 but it was [\"a\"]",
                clause.getErrors().get(0).toString());

    }

    @Test
    public void validatePropertyNotFound() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", "prop1-value");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop2\" : 124 }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        assertEquals(1, clause.getErrors().size());
        assertEquals("Was expecting [\"prop1-value\"] for json path $.prop1 but it was null",
                clause.getErrors().get(0).toString());

    }

    @Test
    public void validateNull() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", null);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : null }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        assertEquals(0, clause.getErrors().size());
    }

    @Test
    public void validateNullValueForNotNullExpected() throws Exception {

        jsonPathValueClauseEnforcer = new JsonPathValueClauseEnforcer("$.prop1", "125");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : null }");

        Clause<JsonNode> clause = jsonPathValueClauseEnforcer.enforce(root);

        assertEquals(1, clause.getErrors().size());
        assertEquals("Was expecting [\"125\"] for json path $.prop1 but it was null",
                clause.getErrors().get(0).toString());
        ;

    }
}