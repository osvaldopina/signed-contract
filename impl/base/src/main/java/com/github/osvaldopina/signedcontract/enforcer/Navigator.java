package com.github.osvaldopina.signedcontract.enforcer;

public interface Navigator<T, E> {

    E navigate(T e);
}
