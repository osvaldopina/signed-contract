package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class HalEmptyEmbeddedClauseEnforcerTest {

    private HalEmptyEmbeddedClauseEnforcer halEmptyEmbeddedClauseEnforcer;


    @Test
    public void enforceEmptyResource() throws Exception {

        halEmptyEmbeddedClauseEnforcer = new HalEmptyEmbeddedClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : 1 , \"_links\" : { \"var1\" : 1 } }");

        Clause<JsonNode> halDocumentClause  = halEmptyEmbeddedClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNomEmptyResourceNoLinksNoEmmbedded() throws Exception {

        halEmptyEmbeddedClauseEnforcer = new HalEmptyEmbeddedClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : 1 , \"_links\" : { \"var1\" : 1 }, \"_embedded\" : { \"a\" : 1}  }");

        Clause<JsonNode> halDocumentClause  = halEmptyEmbeddedClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting not to have _embedded.",
                halDocumentClause.getErrors().get(0).toString());

    }

}