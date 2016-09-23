package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertTrue;

public class UriTemplatedVariableClauseEnforcerTest {

    private UriTemplatedVariableClauseEnforcer uriTemplatedVariableClauseEnforcer;


    @Test
    public void enforce() {
        uriTemplatedVariableClauseEnforcer = new UriTemplatedVariableClauseEnforcer("var1", Collections.EMPTY_LIST);

        UriTemplate uriTemplate = UriTemplate.fromTemplate("{var1}");

        Clause<UriTemplate> enforcedClause = uriTemplatedVariableClauseEnforcer.enforce(uriTemplate);

        assertSame(uriTemplate, enforcedClause.getDocumentClause());
        assertTrue(enforcedClause.getErrors().isEmpty());

    }

    @Test
    public void enforceVariableNotFound() {
        uriTemplatedVariableClauseEnforcer = new UriTemplatedVariableClauseEnforcer("var1", Collections.EMPTY_LIST);

        UriTemplate uriTemplate = UriTemplate.fromTemplate("{var2}");

        Clause<UriTemplate> enforcedClause = uriTemplatedVariableClauseEnforcer.enforce(uriTemplate);

        assertSame(uriTemplate, enforcedClause.getDocumentClause());
        assertEquals(1, enforcedClause.getErrors().size());
        assertEquals("Was expecting uri template to have a variable named [var1]",
                enforcedClause.getErrors().get(0).toString());

    }
}