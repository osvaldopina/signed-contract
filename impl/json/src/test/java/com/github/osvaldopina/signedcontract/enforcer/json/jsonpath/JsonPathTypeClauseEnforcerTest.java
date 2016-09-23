package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonPathTypeClauseEnforcerTest {

    private JsonPathTypeClauseEnforcer jsonPathTypeClauseEnforcer;

    @Test
    public void validateSameType() throws Exception {

        jsonPathTypeClauseEnforcer = new JsonPathTypeClauseEnforcer("$.prop1", JSON_TYPE.STRING);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : \"prop1-value\" }");

        Clause<JsonNode> clause = jsonPathTypeClauseEnforcer.enforce(root);

        assertTrue(clause.getErrors().isEmpty());

    }

    @Test
    public void validateDifferentTypes() throws Exception {

       jsonPathTypeClauseEnforcer = new JsonPathTypeClauseEnforcer("$.prop1", JSON_TYPE.NUMBER);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop1\" : \"prop1-value\" }");

        Clause<JsonNode> clause = jsonPathTypeClauseEnforcer.enforce(root);

        assertEquals(1, clause.getErrors().size());
        assertEquals("Was expecting a type NUMBER for json path $.prop1 but it was STRING",
                clause.getErrors().get(0).toString());


    }



    @Test
    public void validatePropertyNotFound() throws Exception {

       jsonPathTypeClauseEnforcer = new JsonPathTypeClauseEnforcer("$.prop1", JSON_TYPE.STRING);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree("{ \"prop2\" : 124 }");

        Clause<JsonNode> clause = jsonPathTypeClauseEnforcer.enforce(root);

        assertEquals(1, clause.getErrors().size());
        assertEquals("Was expecting a type STRING for json path $.prop1 but the path was not found in json document",
                clause.getErrors().get(0).toString());

    }


}