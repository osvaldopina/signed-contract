package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.Collections;

import static org.junit.Assert.*;

public class HalLinkTemplatedUriClauseEnforcerTest {

    private HalLinkTemplatedUriClauseEnforcer halLinkTemplatedUriClauseEnforcer;

    @Test
    public void enforce() throws Exception {

        halLinkTemplatedUriClauseEnforcer = new HalLinkTemplatedUriClauseEnforcer(Collections.EMPTY_LIST);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\": \"template{var1}\" }");

        Clause<JsonNode> halDocumentClause = halLinkTemplatedUriClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

}