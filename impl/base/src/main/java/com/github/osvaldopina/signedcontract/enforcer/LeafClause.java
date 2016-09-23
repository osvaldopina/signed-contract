package com.github.osvaldopina.signedcontract.enforcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeafClause<T> implements Clause<T> {

    private T documentClause;

    private List<EnforcementError> errors = new ArrayList<EnforcementError>();

    private ClauseEnforcer<T> clauseEnforcer;

    private CloneFilter cloneAllFilter = CloneAllFilter.INSTANCE;

    public LeafClause(T documentClause, ClauseEnforcer<T> clauseEnforcer) {
        this(documentClause, clauseEnforcer, Collections.EMPTY_LIST);
    }

    public LeafClause(T documentClause, ClauseEnforcer<T> clauseEnforcer, List<EnforcementError> errors) {
        this.documentClause = documentClause;
        this.clauseEnforcer = clauseEnforcer;
        this.errors.addAll(errors);
    }


    public T getDocumentClause() {
        return documentClause;
    }

    public List<EnforcementError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public void addErrors(List<EnforcementError> errors) {
        this.errors.addAll(errors);
    }

    public void addError(EnforcementError error) {
        errors.add(error);
    }

    @Override
    public Clause<T> cloneClause() {
        return cloneClause(cloneAllFilter);
    }

    @Override
    public Clause<T> cloneClause(CloneFilter cloneFilter) {
        if (cloneFilter.shouldClone(this)) {
            return new LeafClause<T>(documentClause, clauseEnforcer, errors);
        }
        else {
            return null;
        }
    }

    @Override
    public ClauseEnforcer<T> getEnforcer() {
        return clauseEnforcer;
    }

}
