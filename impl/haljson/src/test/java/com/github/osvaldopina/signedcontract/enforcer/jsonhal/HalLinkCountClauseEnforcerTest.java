package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class HalLinkCountClauseEnforcerTest  {


    private HalLinkCountClauseEnforcer halLinkCountClauseEnforcer;


    @Test
    public void enforceSameLinkCount() throws Exception {

        halLinkCountClauseEnforcer = new HalLinkCountClauseEnforcer(2);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{\"link1\": {} , \"link2\": {} }");

        Clause<JsonNode> halDocumentClause  = halLinkCountClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }


    @Test
    public void enforceDiferentCount() throws Exception {

        halLinkCountClauseEnforcer = new HalLinkCountClauseEnforcer(1);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"link1\": {} , \"link2\": {} }");

        Clause<JsonNode> halDocumentClause  = halLinkCountClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting to have 1 link(s) but _links has 2 link(s).",
                halDocumentClause.getErrors().get(0).toString()
        );

    }

}