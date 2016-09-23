package com.github.osvaldopina.signedcontract.enforcer;

public class SignedContractException extends RuntimeException {

    public SignedContractException(String message) {
        super(message);
    }

    public SignedContractException(String message, Throwable cause) {
        super(message, cause);
    }

}
