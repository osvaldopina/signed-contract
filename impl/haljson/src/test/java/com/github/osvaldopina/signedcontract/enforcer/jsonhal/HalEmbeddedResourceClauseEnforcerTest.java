package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class HalEmbeddedResourceClauseEnforcerTest {


    private HalEmbeddedResourceClauseEnforcer halEmbeddedResourceClauseEnforcer;

    @Test
    public void enforce() throws Exception {
        halEmbeddedResourceClauseEnforcer = new HalEmbeddedResourceClauseEnforcer("rel", Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"rel\" : { \"link-prop\" : 123} }");

        Clause<JsonNode> halDocumentClause  = halEmbeddedResourceClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceEmbeddedNotFound() throws Exception {
        halEmbeddedResourceClauseEnforcer = new HalEmbeddedResourceClauseEnforcer("rel", Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"other-rel\" : { \"link-prop\" : 123} }");

        Clause<JsonNode> halDocumentClause  = halEmbeddedResourceClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());

        assertEquals("Was expecting to find a hal embedded with rel [rel] but it was not found!",
                halDocumentClause.getErrors().get(0).toString());

    }
    

}