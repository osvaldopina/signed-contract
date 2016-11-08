package com.github.osvaldopina.signedcontract.enforcer.jsonhal.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.CloneErrorOnlyFilter;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;
import com.github.osvaldopina.signedcontract.enforcer.jsonhal.HalDocumentClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.jsonhal.HalDocumentErrorPrintWalker;
import com.github.osvaldopina.signedcontract.enforcer.jsonhal.HalDocumentPartClauseEnforcer;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.util.MatcherAssertionErrors;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class SpringTestAdapter implements ResultMatcher {

    private HalDocumentClauseEnforcer halDocumentClauseEnforcer;

    public <E extends HalDocumentPartClauseEnforcer> SpringTestAdapter(E... halClauses) {
        halDocumentClauseEnforcer = new HalDocumentClauseEnforcer(Arrays.asList(halClauses));
    }

    @Override
    public void match(MvcResult result) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode documentJsonNode = mapper.readTree(result.getResponse().getContentAsString());
            Clause<JsonNode> halDocument = halDocumentClauseEnforcer.enforce(documentJsonNode);

            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);

            HalDocumentErrorPrintWalker halDocumentErrorPrintWalker = new HalDocumentErrorPrintWalker(new PrintWriter(stringWriter));

            Clause<JsonNode> onlyErrors = halDocument.cloneClause(CloneErrorOnlyFilter.INSTANCE);

            if (onlyErrors != null) {

                halDocumentErrorPrintWalker.walk(onlyErrors);

                AssertionErrors.fail("\n" + stringWriter.toString());

            }
        } catch (IOException e) {
            throw new SignedContractException("Error reading document " + e, e);
        }
    }

    public static <E extends HalDocumentPartClauseEnforcer> ResultMatcher halDocument(E... halClauses) {
        return new SpringTestAdapter(halClauses);
    }
}
