package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HalEmbeddedListClauseEnforcerTest {

    private HalEmbeddedListClauseEnforcer halEmbeddedListClauseEnforcer;


    @Test
    public void enforce() throws Exception {
        halEmbeddedListClauseEnforcer = new HalEmbeddedListClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"_embedded\": {} }");

        Clause<JsonNode> halDocumentClause  = halEmbeddedListClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNoEmbeddedClause() throws Exception {
        halEmbeddedListClauseEnforcer = new HalEmbeddedListClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\": {} }");

        Clause<JsonNode> halDocumentClause  = halEmbeddedListClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals("Was expecting a hal document to have a _embedded property it hasn't",
                halDocumentClause.getErrors().get(0).toString());

    }


}