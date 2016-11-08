package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class HalEmbeddedArrayClauseEnforcerTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    public Clause<JsonNode> subClause;

    @Mock
    HalEmbeddedArrayItemBaseClauseEnforcer halEmbeddedArrayItemBaseClauseEnforcer;

    private HalEmbeddedArrayClauseEnforcer halEmbeddedArrayClauseEnforcer;

    @Test
    public void enforceConsumeIndex() throws Exception {

        String json = "{ \"rel\": [  ] }";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);

        EasyMock.expect(halEmbeddedArrayItemBaseClauseEnforcer.consumesIndex()).andReturn(true);

        halEmbeddedArrayItemBaseClauseEnforcer.setRel("rel");
        EasyMock.expectLastCall();

        halEmbeddedArrayItemBaseClauseEnforcer.setIndex(0);
        EasyMock.expectLastCall();

        EasyMock.expect(halEmbeddedArrayItemBaseClauseEnforcer.enforce(document.get("rel"))).andReturn(subClause);

        replayAll();

        halEmbeddedArrayClauseEnforcer = new HalEmbeddedArrayClauseEnforcer("rel", Arrays.asList(halEmbeddedArrayItemBaseClauseEnforcer));

        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayClauseEnforcer.enforce(document);

        verifyAll();

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceConsumeNoIndex() throws Exception {

        String json = "{ \"rel\": [  ] }";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);

        EasyMock.expect(halEmbeddedArrayItemBaseClauseEnforcer.consumesIndex()).andReturn(false);

        EasyMock.expect(halEmbeddedArrayItemBaseClauseEnforcer.enforce(document.get("rel"))).andReturn(subClause);

        replayAll();

        halEmbeddedArrayClauseEnforcer = new HalEmbeddedArrayClauseEnforcer("rel", Arrays.asList(halEmbeddedArrayItemBaseClauseEnforcer));

        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayClauseEnforcer.enforce(document);

        verifyAll();

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }


    @Test
    public void enforceRelNotFound() throws Exception {
        String json = "{ \"other-rel\": [  ] }";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);

        EasyMock.expect(halEmbeddedArrayItemBaseClauseEnforcer.consumesIndex()).andReturn(true);

        halEmbeddedArrayItemBaseClauseEnforcer.setRel("rel");
        EasyMock.expectLastCall();

        halEmbeddedArrayItemBaseClauseEnforcer.setIndex(0);
        EasyMock.expectLastCall();

        replayAll();

        halEmbeddedArrayClauseEnforcer = new HalEmbeddedArrayClauseEnforcer("rel", Arrays.asList(halEmbeddedArrayItemBaseClauseEnforcer));

        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayClauseEnforcer.enforce(document);

        verifyAll();

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals("Was expecting to find a hal embedded with rel [rel] but it was not found!",
                halDocumentClause.getErrors().get(0).toString());
    }

    @Test
    public void enforceRelIsNotArray() throws Exception {
        String json = "{ \"rel\": {} }";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree(json);

        EasyMock.expect(halEmbeddedArrayItemBaseClauseEnforcer.consumesIndex()).andReturn(true);

        halEmbeddedArrayItemBaseClauseEnforcer.setRel("rel");
        EasyMock.expectLastCall();

        halEmbeddedArrayItemBaseClauseEnforcer.setIndex(0);
        EasyMock.expectLastCall();

        replayAll();

        halEmbeddedArrayClauseEnforcer = new HalEmbeddedArrayClauseEnforcer("rel", Arrays.asList(halEmbeddedArrayItemBaseClauseEnforcer));

        Clause<JsonNode> halDocumentClause  = halEmbeddedArrayClauseEnforcer.enforce(document);

        verifyAll();

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals("Was expecting a json array as embedded resource but it was OBJECT",
                halDocumentClause.getErrors().get(0).toString());
    }

}