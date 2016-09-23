package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UriTemplatedVariableClauseEnforcer extends BranchClauseEnforcer<UriTemplate, UriTemplateVariable> {

    private String variableName;

    public UriTemplatedVariableClauseEnforcer(String variableName,
                                              List<? extends UriTemplateVariablePropertyClauseEnforcer> subClauseEnforcers) {
        super(subClauseEnforcers);
        this.variableName = variableName;
    }

    @Override
    protected Navigator<UriTemplate, UriTemplateVariable> createNavigator() {
        return new UriTemplateVariableNavigator(variableName);
    }

    @Override
    protected List<EnforcementError> verifyNavigation(UriTemplateVariable documentClause) {

        if (documentClause == null) {
            return Arrays.asList(new EnforcementError(new StringBuilder()
                    .append("Was expecting uri template to have a variable named [")
                    .append(variableName)
                    .append("]")
                    .toString()));
        }
        else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected List<EnforcementError> enforceClause(UriTemplate documentClause) {
        return Collections.EMPTY_LIST;
    }

    public String getVariableName() {
        return variableName;
    }
}
