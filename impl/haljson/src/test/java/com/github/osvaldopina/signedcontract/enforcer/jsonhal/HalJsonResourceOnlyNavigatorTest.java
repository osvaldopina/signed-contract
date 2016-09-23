package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class HalJsonResourceOnlyNavigatorTest {

    private HalJsonResourceOnlyNavigator halJsonResourceOnlyNavigator;

    @Test
    public void navigate() throws Exception {

        halJsonResourceOnlyNavigator = new HalJsonResourceOnlyNavigator();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"_links\": {} , \"_embedded\": {} , \"prop1\" : 1, \"prop2\" : 2 }");

        JsonNode halResourceOnly = halJsonResourceOnlyNavigator.navigate(document);

        assertTrue(document.has("_links"));
        assertTrue(document.get("_links") instanceof ObjectNode);
        assertTrue(document.has("_embedded"));
        assertTrue(document.get("_embedded") instanceof ObjectNode);
        assertTrue(document.has("prop1"));
        assertTrue(document.get("prop1") instanceof NumericNode);
        assertTrue(document.has("prop2"));
        assertTrue(document.get("prop2") instanceof NumericNode);

        assertFalse(halResourceOnly.has("_links"));
        assertFalse(halResourceOnly.has("_embedded"));
        assertTrue(halResourceOnly.has("prop1"));
        assertTrue(halResourceOnly.get("prop1") instanceof NumericNode);
        assertTrue(halResourceOnly.has("prop2"));
        assertTrue(halResourceOnly.get("prop2") instanceof NumericNode);

    }
}