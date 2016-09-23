package com.github.osvaldopina.signedcontract.enforcer.walker;

import com.github.osvaldopina.signedcontract.enforcer.Clause;

public interface ClauseWalker {

    void walk(Clause<?> clause);

}
