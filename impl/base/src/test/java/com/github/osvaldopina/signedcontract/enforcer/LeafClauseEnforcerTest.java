package com.github.osvaldopina.signedcontract.enforcer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LeafClauseEnforcerTest {

    private SimpleLeafClauseEnforcer simpleLeafClauseEnforcer;

    EnforcementError error;
    List<EnforcementError> erros;
    Object documentClause;

    @Test
    public void leafClauseEnforcerNoErrors() {
        erros = new ArrayList<EnforcementError>();
        documentClause = new Object();

        simpleLeafClauseEnforcer = new SimpleLeafClauseEnforcer(erros);

        Clause<Object> enforcmentClause = simpleLeafClauseEnforcer.enforce(documentClause);

        assertTrue(enforcmentClause.getErrors().isEmpty());
        assertSame(documentClause, enforcmentClause.getDocumentClause());
    }

    @Test
    public void leafClauseEnforcerWithErrors() {
        error = new EnforcementError("any-error");
        erros = Arrays.asList(error);
        documentClause = new Object();

        simpleLeafClauseEnforcer = new SimpleLeafClauseEnforcer(erros);

        Clause<Object> enforcmentClause = simpleLeafClauseEnforcer.enforce(documentClause);

        assertEquals(1,enforcmentClause.getErrors().size());
        assertEquals("any-error", enforcmentClause.getErrors().get(0).toString());
        assertSame(documentClause, enforcmentClause.getDocumentClause());
    }


    public static final class SimpleLeafClauseEnforcer extends LeafClauseEnforcer<Object> {

        private List<EnforcementError> erros = new ArrayList<EnforcementError>();
        private Object documentClause;

        public SimpleLeafClauseEnforcer(List<EnforcementError> erros) {
            this.erros.addAll(erros);
        }


        @Override
        protected List<EnforcementError> enforceClause(Object documentClause) {
            this.documentClause = documentClause;
            return erros;
        }
    }
}