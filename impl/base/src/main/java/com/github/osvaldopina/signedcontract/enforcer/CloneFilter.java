package com.github.osvaldopina.signedcontract.enforcer;

public interface CloneFilter {

    boolean shouldClone(Clause<?> clause);

}
