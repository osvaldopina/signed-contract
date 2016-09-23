package com.github.osvaldopina.signedcontract.enforcer;

public class CloneErrorOnlyFilter implements CloneFilter {

    public static final CloneErrorOnlyFilter INSTANCE = new CloneErrorOnlyFilter();

    @Override
    public boolean shouldClone(Clause<?> clause) {
        if (!clause.getErrors().isEmpty()) {
            return true;
        }
        else {
            if (clause instanceof BranchClause) {
                for (Clause<Object> subClause : ((BranchClause<Object, Object>) clause).getSubClauses()) {
                    if (shouldClone(subClause)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
