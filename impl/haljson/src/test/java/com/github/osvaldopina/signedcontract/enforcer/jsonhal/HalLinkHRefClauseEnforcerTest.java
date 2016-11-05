package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HalLinkHRefClauseEnforcerTest {


    private HalLinkHRefClauseEnforcer halLinkHRefClauseEnforcer;


    @Test
    public void enforce() throws Exception {

        halLinkHRefClauseEnforcer = new HalLinkHRefClauseEnforcer("http://test");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\" : \"http://test\" }");

        Clause<JsonNode> halDocumentClause  = halLinkHRefClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }


    @Test
    public void enforceNoHref() throws Exception {

        halLinkHRefClauseEnforcer = new HalLinkHRefClauseEnforcer("http://test");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\" : \"http://other-than-test\" }");

        Clause<JsonNode> halDocumentClause  = halLinkHRefClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link href to be http://test but it was http://other-than-test",
                halDocumentClause.getErrors().get(0).toString()
        );

    }
}