package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

public class UriTemplateVariableNavigator implements Navigator<UriTemplate, UriTemplateVariable> {

    private String variableName;

    public UriTemplateVariableNavigator(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public UriTemplateVariable navigate(UriTemplate uriTemplate) {
        for(Expression expression: uriTemplate.getExpressions()) {
            for(VarSpec varSpec: expression.getVarSpecs()) {
                if (varSpec.getVariableName().equals(variableName)) {
                    return new UriTemplateVariable(varSpec, expression);
                }
            }
        }
        return null;
    }
}
