package com.github.osvaldopina.signedcontract.enforcer;

import java.util.List;

public interface Document<T> {

    Clause<T> enforce(T document);

 }
