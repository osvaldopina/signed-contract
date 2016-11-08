package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class HalEmbeddedArrayItemCountClauseEnforcerTest {

    private HalEmbeddedArrayItemCountClauseEnforcer halEmbeddedArrayItemClauseEnforcer;

    @Test
    public void enforce() throws Exception {

        String json = "[{ \"p1\" : 1}, { \"p2\" : 2}]";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);


        halEmbeddedArrayItemClauseEnforcer = new HalEmbeddedArrayItemCountClauseEnforcer(2);

        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayItemClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceDiferentItemCount() throws Exception {

        String json = "[{ \"p1\" : 1}, { \"p2\" : 2}]";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);


        halEmbeddedArrayItemClauseEnforcer = new HalEmbeddedArrayItemCountClauseEnforcer(3);

        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayItemClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals("Was expecting to have 3 array item(s) but _embedded has 2 array item(s).",
                halDocumentClause.getErrors().get(0).toString());

    }


}