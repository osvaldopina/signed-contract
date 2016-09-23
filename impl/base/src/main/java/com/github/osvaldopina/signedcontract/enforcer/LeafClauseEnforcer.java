package com.github.osvaldopina.signedcontract.enforcer;

import java.util.List;

public abstract class LeafClauseEnforcer<T> implements ClauseEnforcer<T> {


    @Override
    public final Clause<T> enforce(T documentClause) {

        LeafClause<T> leafClause = new LeafClause<T>(documentClause, this);

        leafClause.addErrors(enforceClause(documentClause));

        return leafClause;
    }

    protected abstract List<EnforcementError> enforceClause(T documentClause);

}
