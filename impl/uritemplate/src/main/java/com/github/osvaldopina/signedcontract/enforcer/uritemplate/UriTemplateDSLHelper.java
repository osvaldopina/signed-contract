package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.impl.Modifier;
import com.damnhandy.uri.template.impl.Operator;

import java.util.Arrays;

public class UriTemplateDSLHelper {


    public static UriTemplatedVariableClauseEnforcer variable(String variable,
                  UriTemplateVariablePropertyClauseEnforcer... uriTemplateVariablePropertyClauseEnforcers) {
        return new UriTemplatedVariableClauseEnforcer(variable,
                Arrays.asList(uriTemplateVariablePropertyClauseEnforcers));
    }

    public static UriTemplateVariablePropertyClauseEnforcer exploded() {
        return new UriTemplateVariableModifierClauseEnforcer(Modifier.EXPLODE);
    }

    public static UriTemplateVariablePropertyClauseEnforcer noModifier() {
        return new UriTemplateVariableModifierClauseEnforcer(Modifier.NONE);
    }

    public static UriTemplateVariablePropertyClauseEnforcer defaulExpansion() {
        return new UriTemplateVariableOperatorClauseEnforcer(Operator.NUL);
    }

    public static UriTemplateVariablePropertyClauseEnforcer pathExpansion() {
        return new UriTemplateVariableOperatorClauseEnforcer(Operator.PATH);
    }

    public static UriTemplateVariablePropertyClauseEnforcer queryExpansion() {
        return new UriTemplateVariableOperatorClauseEnforcer(Operator.QUERY);
    }

    public static UriTemplateVariablePropertyClauseEnforcer matrixExpansion() {
        return new UriTemplateVariableOperatorClauseEnforcer(Operator.MATRIX);
    }

    public static UriTemplateVariablePropertyClauseEnforcer continuationExpansion() {
        return new UriTemplateVariableOperatorClauseEnforcer(Operator.CONTINUATION);
    }

    public static UriTemplateVariablePropertyClauseEnforcer fragmentExpansion() {
        return new UriTemplateVariableOperatorClauseEnforcer(Operator.FRAGMENT);
    }


}
