package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.json.navigator.JsonPropertyNavigator;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class HalDocumentClauseEnforcerTest {

    private HalDocumentClauseEnforcer halDocumentClauseEnforcer;


    @Test
    public void enforce() throws Exception {

        halDocumentClauseEnforcer = new HalDocumentClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : { \"sub-prop\" : 125 }  }");

        Clause<JsonNode> halDocumentClause  = halDocumentClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNonJsonDocument() throws Exception {

        halDocumentClauseEnforcer = new HalDocumentClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("\"prop1\"");

        Clause<JsonNode> halDocumentClause  = halDocumentClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals("Was expecting a hal document to be a json document but it is a STRING",
                halDocumentClause.getErrors().get(0).toString());

    }
}