package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class HalLinkPropertyValueClauseEnforcerTest {


    private HalLinkPropertyValueClauseEnforcer halLinkPropertyValueClauseEnforcer;


    @Test
    public void enforceNoDefaultProperty() throws Exception {

        halLinkPropertyValueClauseEnforcer = new HalLinkPropertyValueClauseEnforcer("prop1",123);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"rel\" : { \"prop1\": 123 }}");

        JsonNode link = document.get("rel");

        Clause<JsonNode> halDocumentClause  = halLinkPropertyValueClauseEnforcer.enforce(link);

        assertTrue(halDocumentClause.getErrors().isEmpty());

    }

    @Test
    public void enforceNoDefaultPropertyDifferentValues() throws Exception {

        halLinkPropertyValueClauseEnforcer = new HalLinkPropertyValueClauseEnforcer("prop1",124);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\": 123 }");

        Clause<JsonNode> halDocumentClause  = halLinkPropertyValueClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link property prop1 to be [124] but it was [123]",
                halDocumentClause.getErrors().get(0).toString()
        );

    }

    @Test
    public void enforceNoDefaultPropertyDifferentTypes() throws Exception {

        halLinkPropertyValueClauseEnforcer = new HalLinkPropertyValueClauseEnforcer("prop1","124");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\": 124 }");

        Clause<JsonNode> halDocumentClause  = halLinkPropertyValueClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link property prop1 to be [\"124\"] but it was [124]",
                halDocumentClause.getErrors().get(0).toString()
        );

    }

    @Test
    public void enforceNotInformedDefaultEqualsValue() throws Exception {

        halLinkPropertyValueClauseEnforcer = new HalLinkPropertyValueClauseEnforcer("prop1",false,false);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ }");

        Clause<JsonNode> halDocumentClause  = halLinkPropertyValueClauseEnforcer.enforce(document);

        assertEquals(0, halDocumentClause.getErrors().size());

    }

    @Test
    public void enforceNotInformedDefaultDifferentValue() throws Exception {

        halLinkPropertyValueClauseEnforcer = new HalLinkPropertyValueClauseEnforcer("prop1",true,false);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ }");

        Clause<JsonNode> halDocumentClause  = halLinkPropertyValueClauseEnforcer.enforce(document);

        assertEquals(1, halDocumentClause.getErrors().size());
        assertEquals(
                "Was expecting link property prop1 to be [true] but it was [false]",
                halDocumentClause.getErrors().get(0).toString()
        );

    }

}