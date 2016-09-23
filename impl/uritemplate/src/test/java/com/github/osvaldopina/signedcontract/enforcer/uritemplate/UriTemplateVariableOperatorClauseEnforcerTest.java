package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.Operator;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class UriTemplateVariableOperatorClauseEnforcerTest {

    private UriTemplateVariableOperatorClauseEnforcer uriTemplateVariableOperatorClauseEnforcer;


    @Test
    public void enforce() {
        uriTemplateVariableOperatorClauseEnforcer = new UriTemplateVariableOperatorClauseEnforcer(Operator.PATH);

        UriTemplate uriTemplate = UriTemplate.fromTemplate("{/var1}");

        UriTemplateVariable variable = new UriTemplateVariable(
                (VarSpec) uriTemplate.getExpressions()[0].getVarSpecs().get(0),
                uriTemplate.getExpressions()[0]);

        Clause<UriTemplateVariable> uriTemplateVariableClause =
                uriTemplateVariableOperatorClauseEnforcer.enforce(variable);

        assertTrue(uriTemplateVariableClause.getErrors().isEmpty());
    }

    @Test
    public void enforceDifferentOperator() {
        uriTemplateVariableOperatorClauseEnforcer = new UriTemplateVariableOperatorClauseEnforcer(Operator.PATH);

        UriTemplate uriTemplate = UriTemplate.fromTemplate("{?var1}");

        UriTemplateVariable variable = new UriTemplateVariable(
                (VarSpec) uriTemplate.getExpressions()[0].getVarSpecs().get(0),
                uriTemplate.getExpressions()[0]);

        Clause<UriTemplateVariable> uriTemplateVariableClause =
                uriTemplateVariableOperatorClauseEnforcer.enforce(variable);

        assertEquals(1, uriTemplateVariableClause.getErrors().size());
        assertEquals("Was expecting uri template variable [var1] operator to be PATH but it was QUERY",
                uriTemplateVariableClause.getErrors().get(0).toString());
    }


}