package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.impl.Operator;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UriTemplateVariableOperatorClauseEnforcer extends UriTemplateVariablePropertyClauseEnforcer {

    private Operator operator;


    public UriTemplateVariableOperatorClauseEnforcer(Operator operator) {
        this.operator = operator;
    }

    protected List<EnforcementError> enforceClause(UriTemplateVariable documentClause) {

        if (operator != documentClause.getExpression().getOperator()) {
            return Arrays.asList(new EnforcementError(new StringBuilder()
                    .append("Was expecting uri template variable [")
                    .append(documentClause.getVariable().getVariableName())
                    .append("] operator to be ")
                    .append(operator)
                    .append(" but it was ")
                    .append(documentClause.getExpression().getOperator())
                    .toString()));
        } else {
            return Collections.EMPTY_LIST;
        }
    }

}
