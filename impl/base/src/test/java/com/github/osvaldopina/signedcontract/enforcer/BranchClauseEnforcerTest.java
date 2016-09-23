package com.github.osvaldopina.signedcontract.enforcer;

import org.easymock.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BranchClauseEnforcerTest  extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    private SimpleBranchClauseEnforcer simpleBranchClauseEnforcer;

    private List<EnforcementError> navigationErrors;

    private List<EnforcementError> enforcementErrors;

    @Mock
    private EnforcementError navigationError;

    @Mock
    private EnforcementError enformentError;

    private List<ClauseEnforcer<Object>> subClauseEnforcers;

    @Mock
    private ClauseEnforcer<Object> subClauseEnforcer;

    @Mock
    private Clause<Object> subClause;

    @Mock
    private Navigator<Object,Object> navigator;

    @Mock
    private Object documentClause;


    @Before
    public void setUp() {
        subClauseEnforcers = new ArrayList<ClauseEnforcer<Object>>();
        subClauseEnforcers.add(subClauseEnforcer);
    }

    @Test
    public void branchClauseEnforcerWithOnlyNavigationErrors() {
        EasyMock.expect(navigator.navigate(documentClause)).andReturn(documentClause);
        navigationErrors = Arrays.asList(navigationError);
        enforcementErrors = Collections.EMPTY_LIST;

        simpleBranchClauseEnforcer = new SimpleBranchClauseEnforcer(
                navigator,
                navigationErrors,
                enforcementErrors,
                subClauseEnforcers);

        replayAll();

        Clause<Object> enforcedClause = simpleBranchClauseEnforcer.enforce(documentClause);

        verifyAll();

        assertTrue(enforcedClause instanceof BranchClause);
        BranchClause<Object, Object> clause = (BranchClause<Object, Object>) enforcedClause;
        assertEquals(1,clause.getErrors().size());
        assertSame(navigationError, clause.getErrors().get(0));
        assertSame(documentClause, clause.getDocumentClause());

        assertTrue(clause.getSubClauses().isEmpty());

    }

    @Test
    public void branchClauseEnforcerWithOnlyEnforcementErrors() {
        EasyMock.expect(navigator.navigate(documentClause)).andReturn(documentClause);
        EasyMock.expect(subClauseEnforcer.enforce(documentClause)).andReturn(subClause);
        EasyMock.expect(enformentError.isFatal()).andReturn(false);
        enforcementErrors = Arrays.asList(enformentError);
        navigationErrors = Collections.EMPTY_LIST;

        simpleBranchClauseEnforcer = new SimpleBranchClauseEnforcer(
                navigator,
                navigationErrors,
                enforcementErrors,
                subClauseEnforcers);

        replayAll();

        Clause<Object> enforcedClause = simpleBranchClauseEnforcer.enforce(documentClause);

        verifyAll();

        assertTrue(enforcedClause instanceof BranchClause);
        BranchClause<Object, Object> clause = (BranchClause<Object, Object>) enforcedClause;
        assertEquals(1,clause.getErrors().size());
        assertSame(enformentError, clause.getErrors().get(0));
        assertSame(documentClause, clause.getDocumentClause());

        assertEquals(1, clause.getSubClauses().size());
        assertEquals(subClause, clause.getSubClauses().get(0));


    }

    @Test
    public void branchClauseEnforcerEnforcementAndNavigationErrors() {
        EasyMock.expect(navigator.navigate(documentClause)).andReturn(documentClause);
        EasyMock.expect(enformentError.isFatal()).andReturn(false);
        enforcementErrors = Arrays.asList(enformentError);
        navigationErrors = Arrays.asList(navigationError);

        simpleBranchClauseEnforcer = new SimpleBranchClauseEnforcer(
                navigator,
                navigationErrors,
                enforcementErrors,
                subClauseEnforcers);

        replayAll();

        Clause<Object> enforcedClause = simpleBranchClauseEnforcer.enforce(documentClause);

        verifyAll();

        assertTrue(enforcedClause instanceof BranchClause);
        BranchClause<Object, Object> clause = (BranchClause<Object, Object>) enforcedClause;
        assertEquals(2,clause.getErrors().size());
        assertSame(enformentError, clause.getErrors().get(0));
        assertSame(navigationError, clause.getErrors().get(1));
        assertSame(documentClause, clause.getDocumentClause());

        assertTrue(clause.getSubClauses().isEmpty());


    }

    @Test
    public void branchClauseEnforcerNavigationThrowsException() {
        String message = "a message";
        EasyMock.expect(navigator.navigate(documentClause)).andThrow(new IllegalArgumentException(message));

        simpleBranchClauseEnforcer = new SimpleBranchClauseEnforcer(
                navigator,
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST,
                subClauseEnforcers);

        replayAll();

        Clause<Object> enforcedClause = simpleBranchClauseEnforcer.enforce(documentClause);

        verifyAll();

        assertTrue(enforcedClause instanceof BranchClause);
        BranchClause<Object, Object> clause = (BranchClause<Object, Object>) enforcedClause;
        assertEquals(1,clause.getErrors().size());
        assertEquals(message, clause.getErrors().get(0).getMessage());
        assertSame(documentClause, clause.getDocumentClause());

        assertTrue(clause.getSubClauses().isEmpty());


    }

    public static final class SimpleBranchClauseEnforcer extends BranchClauseEnforcer<Object,Object> {

        private Navigator<Object,Object> navigator;

        private List<EnforcementError> navigationErrors;

        private List<EnforcementError> enforcementErrors;

        public Object verifiedNavigationDocumentClause;

        public Object enforcedDocumentClause;

        public SimpleBranchClauseEnforcer(Navigator navigator,
                                          List<EnforcementError> navigationErros,
                                          List<EnforcementError> enforcementErrors,
                                          List<? extends ClauseEnforcer<Object>> subClauseEnforcers) {
            super(subClauseEnforcers);
            this.navigator = navigator;
            this.navigationErrors = navigationErros;
            this.enforcementErrors = enforcementErrors;
        }

        @Override
        protected Navigator<Object, Object> createNavigator() {
            return navigator;
        }

        @Override
        protected List<EnforcementError> verifyNavigation(Object documentClause) {
            verifiedNavigationDocumentClause = documentClause;
            return navigationErrors;
        }

        @Override
        protected List<EnforcementError> enforceClause(Object documentClause) {
            enforcedDocumentClause = documentClause;
            return enforcementErrors;
        }
    }


}