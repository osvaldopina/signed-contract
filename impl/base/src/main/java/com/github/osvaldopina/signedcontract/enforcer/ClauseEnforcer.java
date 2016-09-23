package com.github.osvaldopina.signedcontract.enforcer;

import java.util.List;

public interface ClauseEnforcer<T> {

    Clause<T> enforce(T documentClause);

}
