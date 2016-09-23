package com.github.osvaldopina.signedcontract.enforcer;

public class CloneAllFilter implements CloneFilter {

    public static final CloneAllFilter INSTANCE = new CloneAllFilter();

    @Override
    public boolean shouldClone(Clause<?> clause) {
        return true;
    }
}
