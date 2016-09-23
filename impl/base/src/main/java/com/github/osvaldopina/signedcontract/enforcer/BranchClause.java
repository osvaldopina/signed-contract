package com.github.osvaldopina.signedcontract.enforcer;

import com.github.osvaldopina.signedcontract.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchClause<T, E> extends LeafClause<T> {

    private List<Clause<E>> subClauses = new ArrayList<Clause<E>>();

    public BranchClause(T documentClause, ClauseEnforcer<T> clauseEnforcer) {
        this(documentClause, clauseEnforcer, Collections.EMPTY_LIST);
    }

    public BranchClause(T documentClause, ClauseEnforcer<T> clauseEnforcer, List<EnforcementError> errors) {
        super(documentClause, clauseEnforcer, errors);
    }

    public List<Clause<E>> getSubClauses() {
        return Collections.unmodifiableList(subClauses);
    }

    public void addSubClauses(List<Clause<E>> subClauses) {
        this.subClauses.addAll(subClauses);
    }

    public void addSubClause(Clause<E> subClause) {
        this.subClauses.add(subClause);
    }


    @Override
    public Clause<T> cloneClause(CloneFilter cloneFilter) {

        BranchClause<T,E> clonedClause = null;

        if (cloneFilter.shouldClone(this)) {
            clonedClause = new BranchClause<T, E>(getDocumentClause(), getEnforcer(), getErrors());
            Clause<E> clonedSubClause;
            for(Clause<E> subClause: getSubClauses()) {
                clonedSubClause = subClause.cloneClause(cloneFilter);
                if (clonedSubClause != null) {
                    clonedClause.addSubClause(clonedSubClause);
                }
            }
        }
        return clonedClause;
    }


}
