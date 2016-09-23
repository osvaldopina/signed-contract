package com.github.osvaldopina.signedcontract.enforcer;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LeafClauseTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    LeafClause<Object> leafClause;

    @Mock
    Object documentClause;

    @Mock
    EnforcementError error;

    @Mock
    ClauseEnforcer<Object> clauseEnforcer;

    @Mock
    CloneFilter cloneFilter;

    @Test
    public void cloneClause() {
        leafClause = new LeafClause(documentClause, clauseEnforcer, Arrays.asList(error));

        EasyMock.expect(cloneFilter.shouldClone(leafClause)).andReturn(true);

        replayAll();

        Clause<Object> clonedClause = leafClause.cloneClause(cloneFilter);

        verifyAll();


        assertNotNull(clonedClause);
        assertSame(documentClause, clonedClause.getDocumentClause());
        assertSame(clauseEnforcer, clonedClause.getEnforcer());
        assertSame(1, clonedClause.getErrors().size());
        assertSame(error, clonedClause.getErrors().get(0));

    }

    @Test
    public void cloneClauseFilterReturnsFalse() {
        leafClause = new LeafClause(documentClause, clauseEnforcer, Arrays.asList(error));

        EasyMock.expect(cloneFilter.shouldClone(leafClause)).andReturn(false);

        replayAll();

        Clause<Object> clonedClause = leafClause.cloneClause(cloneFilter);

        verifyAll();


        assertNull(clonedClause);

    }

    @Test
    public void cloneDefaultFilter() throws Exception {
        leafClause = new LeafClause(documentClause, clauseEnforcer, Arrays.asList(error));


        EasyMock.expect(cloneFilter.shouldClone(leafClause)).andReturn(false);

        Field field = leafClause.getClass().getDeclaredField("cloneAllFilter");
        field.setAccessible(true);
        field.set(leafClause, cloneFilter);

        replayAll();

        Clause<Object> clonedClause = leafClause.cloneClause();

        verifyAll();


        assertNull(clonedClause);

    }

}