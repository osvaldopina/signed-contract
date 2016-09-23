package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class HalLinkListClauseEnforcerTest {

    private HalLinkListClauseEnforcer halLinkListClauseEnforcer;


    @Test
    public void enforce() throws Exception {
        halLinkListClauseEnforcer = new HalLinkListClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"_links\": {} }");

        Clause<JsonNode> halDocumentClause  = halLinkListClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNoLinksClause() throws Exception {
        halLinkListClauseEnforcer = new HalLinkListClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\": {} }");

        Clause<JsonNode> halDocumentClause  = halLinkListClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals("Was expecting a hal document to have a _links property it hasn't",
                halDocumentClause.getErrors().get(0).toString());

    }


}