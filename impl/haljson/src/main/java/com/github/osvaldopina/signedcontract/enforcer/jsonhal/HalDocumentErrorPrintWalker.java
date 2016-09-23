package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.damnhandy.uri.template.UriTemplate;
import com.github.osvaldopina.signedcontract.enforcer.BranchClause;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.EnforcementError;
import com.github.osvaldopina.signedcontract.enforcer.uritemplate.UriTemplatedVariableClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.walker.ClauseWalker;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HalDocumentErrorPrintWalker implements ClauseWalker {

    private PrintStream printStream;


    public HalDocumentErrorPrintWalker(PrintStream printStream) {
        this.printStream = printStream;
    }


    @Override
    public void walk(Clause<?> clause) {
//        System.out.println("┘├ └ |");
//        System.out.println("┘│├ └ |");
        walk(clause,0, printStream);
    }

    private void walk(Clause<?> clause, int level, PrintStream printStream) {
        for(String str: getClauseStringRepresentation(clause)) {
            printStream.print(levelPad(level));
            printStream.print(str);
            printStream.print("\n");
        }
        if (clause instanceof BranchClause) {
            BranchClause<?, ?> branchClause = (BranchClause<?, ?>) clause;
            for (Clause<?> subClause : branchClause.getSubClauses()) {
                walk(subClause, level+1, printStream);
            }
        }
    }

    private String levelPad(int level) {
        StringBuilder tmp = new StringBuilder();

        for(int i=0; i < level; i++) {
            tmp.append("   ");
         }

        return tmp.toString();
    }

    private List<String> getClauseStringRepresentation(Clause<?> clause) {
        if (clause.getEnforcer() instanceof HalDocumentClauseEnforcer) {
            return Arrays.asList("hal document");
        }
        else if (clause.getEnforcer() instanceof HalResourceClauseEnforcer) {
            return Arrays.asList("resource");
        }
        else if (clause.getEnforcer() instanceof HalLinkListClauseEnforcer) {
            if (clause.getErrors().isEmpty()) {
                return Arrays.asList("links");
            }
            else {
                return getErrorListAsString(clause);

            }
        }
        else if (clause.getEnforcer() instanceof HalEmbeddedListClauseEnforcer) {
            if (clause.getErrors().isEmpty()) {
                return Arrays.asList("embedded");
            }
            else {
                return getErrorListAsString(clause);

            }
        }
        else if (clause.getEnforcer() instanceof HalLinkClauseEnforcer) {
            if (clause.getErrors().isEmpty()) {
                return Arrays.asList("link [" + ((HalLinkClauseEnforcer) clause.getEnforcer()).getRel() + "]");
            }
            else {
                return getErrorListAsString(clause);
            }
        }
        else if (clause.getEnforcer() instanceof HalEmbeddedResourceClauseEnforcer) {
            if (clause.getErrors().isEmpty()) {
                return Arrays.asList("embedded [" + ((HalEmbeddedResourceClauseEnforcer) clause.getEnforcer()).getRel() + "]");
            }
            else {
                return getErrorListAsString(clause);
            }
        }
        else if (clause.getEnforcer() instanceof HalEmbeddedArrayClauseEnforcer) {
            if (clause.getErrors().isEmpty()) {
                return Arrays.asList("embedded [" + ((HalEmbeddedArrayClauseEnforcer) clause.getEnforcer()).getRel() + "]");
            }
            else {
                return getErrorListAsString(clause);
            }
        }
        else if (clause.getEnforcer() instanceof HalEmbeddedArrayItemClauseEnforcer) {
            if (clause.getErrors().isEmpty()) {
                return Arrays.asList("[" + ((HalEmbeddedArrayItemClauseEnforcer) clause.getEnforcer()).getIndex() + "]");
            }
            else {
                return getErrorListAsString(clause);
            }
        }
        else if (clause.getEnforcer() instanceof HalLinkTemplatedUriClauseEnforcer) {
            UriTemplate uriTemplate = (UriTemplate) ((BranchClause<?,?>) clause).getSubClauses().get(0).getDocumentClause();
            return Arrays.asList("uri template [" + uriTemplate.getTemplate() + "]");
        }
        else if (clause.getEnforcer() instanceof UriTemplatedVariableClauseEnforcer) {
            return Arrays.asList("variable [" + ((UriTemplatedVariableClauseEnforcer) clause.getEnforcer()).getVariableName() + "]");
        }
        else {
            return getErrorListAsString(clause);
        }
    }

    private List<String> getErrorListAsString(Clause<?> clause) {
        List<String> errors = new ArrayList<String>();
        for(EnforcementError enforcementError: clause.getErrors()) {
            errors.add(enforcementError.toString());
        }
        return errors;
    }
}
