package com.github.osvaldopina.signedcontract.enforcer;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BranchClauseTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    BranchClause<Object, Object> branchClause;

    @Mock
    Clause<Object> subClause1;

    @Mock
    Clause<Object> clonedSubClause1;

    @Mock
    Clause<Object> subClause2;

    @Mock
    Clause<Object> clonedSubClause2;

    @Mock
    Object documentClause;

    @Mock
    EnforcementError error;

    @Mock
    ClauseEnforcer<Object> clauseEnforcer;

    @Mock
    CloneFilter cloneFilter;

    @Test
    public void cloneClauseCloneAll() {
        branchClause = new BranchClause<Object, Object>(documentClause, clauseEnforcer, Arrays.asList(error));
        branchClause.addSubClause(subClause1);
        branchClause.addSubClause(subClause2);

        EasyMock.expect(cloneFilter.shouldClone(branchClause)).andReturn(true);
        EasyMock.expect(subClause1.cloneClause(cloneFilter)).andReturn(clonedSubClause1);
        EasyMock.expect(subClause2.cloneClause(cloneFilter)).andReturn(clonedSubClause2);

        replayAll();

        Clause<Object> clonedClause = branchClause.cloneClause(cloneFilter);

        verifyAll();

        BranchClause<Object, Object> clonedBranchClause = (BranchClause<Object, Object>) clonedClause;

        assertNotSame(clonedBranchClause, branchClause);
        assertSame(documentClause, clonedBranchClause.getDocumentClause());
        assertSame(clauseEnforcer, clonedBranchClause.getEnforcer());
        assertEquals(1, clonedBranchClause.getErrors().size());
        assertEquals(error, clonedBranchClause.getErrors().get(0));
        assertEquals(2 , clonedBranchClause.getSubClauses().size());
        assertSame(clonedSubClause1, clonedBranchClause.getSubClauses().get(0));
        assertSame(clonedSubClause2, clonedBranchClause.getSubClauses().get(1));

    }

    @Test
    public void cloneClauseBranchIsFiltered() {
        branchClause = new BranchClause<Object, Object>(documentClause, clauseEnforcer, Arrays.asList(error));
        branchClause.addSubClause(subClause1);
        branchClause.addSubClause(subClause2);

        EasyMock.expect(cloneFilter.shouldClone(branchClause)).andReturn(false);

        replayAll();

        Clause<Object> clonedClause = branchClause.cloneClause(cloneFilter);

        verifyAll();

        BranchClause<Object, Object> clonedBranchClause = (BranchClause<Object, Object>) clonedClause;

        assertNull(clonedBranchClause);

    }

    @Test
    public void cloneClauseSubClauesFiltered() {
        branchClause = new BranchClause<Object, Object>(documentClause, clauseEnforcer, Arrays.asList(error));
        branchClause.addSubClause(subClause1);
        branchClause.addSubClause(subClause2);

        EasyMock.expect(cloneFilter.shouldClone(branchClause)).andReturn(true);
        EasyMock.expect(subClause1.cloneClause(cloneFilter)).andReturn(null);
        EasyMock.expect(subClause2.cloneClause(cloneFilter)).andReturn(clonedSubClause2);

        replayAll();

        Clause<Object> clonedClause = branchClause.cloneClause(cloneFilter);

        verifyAll();

        BranchClause<Object, Object> clonedBranchClause = (BranchClause<Object, Object>) clonedClause;


        assertNotSame(clonedBranchClause, branchClause);
        assertSame(documentClause, clonedBranchClause.getDocumentClause());
        assertSame(clauseEnforcer, clonedBranchClause.getEnforcer());
        assertEquals(1, clonedBranchClause.getErrors().size());
        assertEquals(error, clonedBranchClause.getErrors().get(0));
        assertEquals(1 , clonedBranchClause.getSubClauses().size());
        assertSame(clonedSubClause2, clonedBranchClause.getSubClauses().get(0));




    }
}