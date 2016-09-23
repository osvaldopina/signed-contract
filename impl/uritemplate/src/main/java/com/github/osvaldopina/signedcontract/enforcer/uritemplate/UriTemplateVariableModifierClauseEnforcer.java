package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.impl.Modifier;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UriTemplateVariableModifierClauseEnforcer extends UriTemplateVariablePropertyClauseEnforcer {

    private Modifier modifier;

    public UriTemplateVariableModifierClauseEnforcer(Modifier modifier) {
        this.modifier = modifier;
    }

    protected List<EnforcementError> enforceClause(UriTemplateVariable documentClause) {

        if (modifier  != documentClause.getVariable().getModifier()) {
            return Arrays.asList(new EnforcementError(new StringBuilder()
                    .append("Was expecting uri template variable [")
                    .append(documentClause.getVariable().getVariableName())
                    .append("] modifier to be ")
                    .append(modifier)
                    .append(" but it was ")
                    .append(documentClause.getVariable().getModifier())
                    .toString()));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}
