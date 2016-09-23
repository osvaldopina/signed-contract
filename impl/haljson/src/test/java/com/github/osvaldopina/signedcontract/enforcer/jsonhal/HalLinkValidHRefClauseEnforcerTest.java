package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class HalLinkValidHRefClauseEnforcerTest {


    private HalLinkValidHRefClauseEnforcer halLinkValidHRefClauseEnforcer;


    @Test
    public void enforce() throws Exception {

        halLinkValidHRefClauseEnforcer = new HalLinkValidHRefClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\" : \"http://test\" }");

        Clause<JsonNode> halDocumentClause  = halLinkValidHRefClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }


    @Test
    public void enforceNoHref() throws Exception {

        halLinkValidHRefClauseEnforcer = new HalLinkValidHRefClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"no-href\" : 123 }");

        Clause<JsonNode> halDocumentClause  = halLinkValidHRefClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link to have a href property but it was not defined!",
                halDocumentClause.getErrors().get(0).toString()
        );

    }

    @Test
    public void enforceHrefInvalidUri() throws Exception {

        halLinkValidHRefClauseEnforcer = new HalLinkValidHRefClauseEnforcer();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\" : \"htttp://teste\" }");

        Clause<JsonNode> halDocumentClause  = halLinkValidHRefClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link to have a valid url as href property value!",
                halDocumentClause.getErrors().get(0).toString()
        );

    }




}