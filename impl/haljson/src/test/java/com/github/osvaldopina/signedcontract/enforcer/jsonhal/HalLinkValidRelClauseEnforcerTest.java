package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class HalLinkValidRelClauseEnforcerTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    private HalLinkValidRelClauseEnforcer halLinkValidRelClauseEnforcer;

    @Mock
    List<String> ianaRels;


    @Test
    public void enforceIanaRel() throws Exception {

        halLinkValidRelClauseEnforcer = new HalLinkValidRelClauseEnforcer();
        String rel = "self";
        halLinkValidRelClauseEnforcer.setRel(rel);

        Field field = halLinkValidRelClauseEnforcer.getClass().getDeclaredField("ianaRels");
        field.setAccessible(true);
        field.set(halLinkValidRelClauseEnforcer, ianaRels);

        EasyMock.expect(ianaRels.contains(rel)).andReturn(true);

        replayAll();

        Clause<JsonNode> halDocumentClause  = halLinkValidRelClauseEnforcer.enforce(null);

        verifyAll();

        assertTrue(halDocumentClause.getErrors().isEmpty());
    }

    @Test
    public void enforceAbsoluteUriRel() throws Exception {

        halLinkValidRelClauseEnforcer = new HalLinkValidRelClauseEnforcer();
        String rel = "http://any-absolute-uri";
        halLinkValidRelClauseEnforcer.setRel(rel);

        Field field = halLinkValidRelClauseEnforcer.getClass().getDeclaredField("ianaRels");
        field.setAccessible(true);
        field.set(halLinkValidRelClauseEnforcer, ianaRels);


        EasyMock.expect(ianaRels.contains(rel)).andReturn(false);

        replayAll();

        Clause<JsonNode> halDocumentClause  = halLinkValidRelClauseEnforcer.enforce(null);

        verifyAll();

        assertTrue(halDocumentClause.getErrors().isEmpty());
        halLinkValidRelClauseEnforcer = new HalLinkValidRelClauseEnforcer();


    }

    @Test
    public void enforceNotAbsoluteUriRel() throws Exception {

        halLinkValidRelClauseEnforcer = new HalLinkValidRelClauseEnforcer();
        String rel = "non-absolute-uri";
        halLinkValidRelClauseEnforcer.setRel(rel);

        Field field = halLinkValidRelClauseEnforcer.getClass().getDeclaredField("ianaRels");
        field.setAccessible(true);
        field.set(halLinkValidRelClauseEnforcer, ianaRels);


        EasyMock.expect(ianaRels.contains(rel)).andReturn(false);

        replayAll();

        Clause<JsonNode> halDocumentClause  = halLinkValidRelClauseEnforcer.enforce(null);

        verifyAll();

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link rel to be a absolute URI but was not!",
                halDocumentClause.getErrors().get(0).toString()
        );


    }

    @Test
    public void enforceInvalidUriRel() throws Exception {

        halLinkValidRelClauseEnforcer = new HalLinkValidRelClauseEnforcer();
        String rel = "`";
        halLinkValidRelClauseEnforcer.setRel(rel);

        Field field = halLinkValidRelClauseEnforcer.getClass().getDeclaredField("ianaRels");
        field.setAccessible(true);
        field.set(halLinkValidRelClauseEnforcer, ianaRels);


        EasyMock.expect(ianaRels.contains(rel)).andReturn(false);

        replayAll();

        Clause<JsonNode> halDocumentClause  = halLinkValidRelClauseEnforcer.enforce(null);

        verifyAll();

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link rel to be a absolute URI or a IANA rel but was not!",
                halDocumentClause.getErrors().get(0).toString()
        );


    }

}