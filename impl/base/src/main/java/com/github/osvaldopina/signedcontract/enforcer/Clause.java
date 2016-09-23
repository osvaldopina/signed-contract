package com.github.osvaldopina.signedcontract.enforcer;

import java.util.List;

public interface Clause<T> {

    T getDocumentClause();

    List<EnforcementError> getErrors();

    Clause<T> cloneClause();

    Clause<T> cloneClause(CloneFilter cloneFilter);

    ClauseEnforcer<T> getEnforcer();

}
