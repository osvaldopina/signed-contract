package com.github.osvaldopina.signedcontract.enforcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BranchClauseEnforcer<T, E> implements ClauseEnforcer<T> {

    private List<ClauseEnforcer<E>> subClauseEnforcers = new ArrayList<ClauseEnforcer<E>>();

    private Navigator<T, E> navigator;

    public BranchClauseEnforcer(List<? extends ClauseEnforcer<E>> subClauseEnforcers) {
        this.subClauseEnforcers.addAll(subClauseEnforcers);
    }


    @Override
    public final Clause<T> enforce(T documentClause) {

        BranchClause<T, E> clause = new BranchClause<T, E>(documentClause, this);

        clause.addErrors(enforceClause(documentClause));

        for(EnforcementError error: clause.getErrors()) {
            if (error.isFatal()) {
                return clause;
            }
        }

        E documentSubClause = null;

        List<EnforcementError> navigationErrors = new ArrayList<EnforcementError>();
        try {
            documentSubClause = navigate(documentClause);
            navigationErrors.addAll(verifyNavigation(documentSubClause));
        }
        catch(Throwable e) {
            navigationErrors.add(new EnforcementError(e.getMessage()));
        }


        if (!navigationErrors.isEmpty()) {
            clause.addErrors(navigationErrors);
        } else {

            List<Clause<E>> subClauses = new ArrayList<Clause<E>>();

            for (ClauseEnforcer<E> subClauseEnforcer : subClauseEnforcers) {
                subClauses.add(subClauseEnforcer.enforce(documentSubClause));
            }

            clause.addSubClauses(subClauses);
        }
        return clause;
    }

    public List<ClauseEnforcer<E>> getSubClauseEnforcers() {
        return Collections.unmodifiableList(subClauseEnforcers);
    }

    protected final E navigate(T documentClause) {
        if (navigator == null) {
            navigator = createNavigator();
        }

        return navigator.navigate(documentClause);
    }

    protected abstract Navigator<T, E> createNavigator();

    protected abstract List<EnforcementError> verifyNavigation(E documentClause);

    protected abstract List<EnforcementError> enforceClause(T documentClause);

}
