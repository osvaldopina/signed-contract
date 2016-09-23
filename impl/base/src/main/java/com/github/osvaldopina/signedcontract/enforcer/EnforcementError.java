package com.github.osvaldopina.signedcontract.enforcer;

public class EnforcementError {

    private String message;
    
    private boolean fatal = false;


    public EnforcementError(String message) {
        this.message = message;
    }

    public EnforcementError(String message, boolean fatal) {
        this.message = message;
        this.fatal = fatal;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

    public boolean isFatal() {
        return fatal;
    }
}
