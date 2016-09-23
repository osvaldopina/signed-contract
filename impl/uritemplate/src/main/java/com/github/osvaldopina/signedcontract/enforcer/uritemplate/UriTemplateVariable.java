package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;

public class UriTemplateVariable {

    private VarSpec variable;

    private Expression expression;

    public UriTemplateVariable(VarSpec variable, Expression expression) {
        checkParameters(variable, expression);
        this.variable = variable;
        this.expression = expression;
    }

    private void checkParameters(VarSpec variable, Expression expression) {
        if (variable == null) {
            throw new SignedContractException("variable must not be null");
        }
        if (expression == null) {
            throw new SignedContractException("expression must not be null");
        }
    }

    public VarSpec getVariable() {
        return variable;
    }

    public Expression getExpression() {
        return expression;
    }
}
