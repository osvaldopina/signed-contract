package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class HalEmptyResourceClauseEnforcerTest {

    private HalEmptyResourceClauseEnforcer halEmptyResourceClauseEnforcer;


    @Test
    public void enforceEmptyResource() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{}");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNomEmptyResourceNoLinksNoEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : 1 }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting to have empty resource but resource has 1 property(ies) [prop1].",
                halDocumentClause.getErrors().get(0).toString());

    }


    @Test
    public void enforceNomEmptyResourceWithLinksNoEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : 1, \"_links\" : { \"link1\" : 1 } }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting to have empty resource but resource has 1 property(ies) [prop1].",
                halDocumentClause.getErrors().get(0).toString());

    }

    @Test
    public void enforceEmptyResourceWithLinksNoEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"_links\" : { \"link1\" : 1 } }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNomEmptyResourceWithLinksWithEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : 1, \"_links\" : { \"link1\" : 1 }, \"_embedded\" : { \"emb1\" : 1} }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting to have empty resource but resource has 1 property(ies) [prop1].",
                halDocumentClause.getErrors().get(0).toString());

    }

    @Test
    public void enforceEmptyResourceWithLinksWithEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"_links\" : { \"link1\" : 1 }, \"_embedded\" : { \"emb1\" : 1} }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }


    @Test
    public void enforceNomEmptyResourceNoLinksWithEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : 1, \"prop2\" : 1,  \"_embedded\" : { \"link1\" : 1 } }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting to have empty resource but resource has 2 property(ies) [prop1, prop2].",
                halDocumentClause.getErrors().get(0).toString());

    }

    @Test
    public void enforceEmptyResourceNoLinksWithEmmbedded() throws Exception {

        halEmptyResourceClauseEnforcer = new HalEmptyResourceClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"_embedded\" : { \"link1\" : 1 } }");

        Clause<JsonNode> halDocumentClause  = halEmptyResourceClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

}