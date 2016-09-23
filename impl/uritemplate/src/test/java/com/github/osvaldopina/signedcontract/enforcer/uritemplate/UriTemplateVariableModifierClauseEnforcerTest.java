package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.Modifier;
import com.damnhandy.uri.template.impl.Operator;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static org.junit.Assert.*;

public class UriTemplateVariableModifierClauseEnforcerTest {

    private UriTemplateVariableModifierClauseEnforcer uriTemplateVariableModifierClauseEnforcer;


    @Test
    public void enforce() {
        uriTemplateVariableModifierClauseEnforcer = new UriTemplateVariableModifierClauseEnforcer(Modifier.EXPLODE);

        UriTemplate uriTemplate = UriTemplate.fromTemplate("{/var1*}");

        UriTemplateVariable variable = new UriTemplateVariable(
                (VarSpec) uriTemplate.getExpressions()[0].getVarSpecs().get(0),
                uriTemplate.getExpressions()[0]);

        Clause<UriTemplateVariable> uriTemplateVariableClause =
                uriTemplateVariableModifierClauseEnforcer.enforce(variable);

        assertTrue(uriTemplateVariableClause.getErrors().isEmpty());
    }

    @Test
    public void enforceDifferentOperator() {
        uriTemplateVariableModifierClauseEnforcer = new UriTemplateVariableModifierClauseEnforcer(Modifier.NONE);

        UriTemplate uriTemplate = UriTemplate.fromTemplate("{/var1*}");

        UriTemplateVariable variable = new UriTemplateVariable(
                (VarSpec) uriTemplate.getExpressions()[0].getVarSpecs().get(0),
                uriTemplate.getExpressions()[0]);

        Clause<UriTemplateVariable> uriTemplateVariableClause =
                uriTemplateVariableModifierClauseEnforcer.enforce(variable);

        assertEquals(1, uriTemplateVariableClause.getErrors().size());
        assertEquals("Was expecting uri template variable [var1] modifier to be NONE but it was EXPLODE",
                uriTemplateVariableClause.getErrors().get(0).toString());
    }


}