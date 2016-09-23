package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.BranchClause;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class HalEmbeddedArrayItemClauseEnforcerTest  {


    private HalEmbeddedArrayItemClauseEnforcer halEmbeddedArrayItemClauseEnforcer;

    @Test
    public void enforce() throws Exception {

        String json = "[{ \"p1\" : 1}, { \"p2\" : 2}]";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);


        halEmbeddedArrayItemClauseEnforcer = new HalEmbeddedArrayItemClauseEnforcer(Collections.EMPTY_LIST);
        halEmbeddedArrayItemClauseEnforcer.setRel("rel");
        halEmbeddedArrayItemClauseEnforcer.setIndex(1);


        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayItemClauseEnforcer.enforce(document);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }


}