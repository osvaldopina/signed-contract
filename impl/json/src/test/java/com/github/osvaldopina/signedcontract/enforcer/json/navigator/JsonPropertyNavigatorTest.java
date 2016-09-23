package com.github.osvaldopina.signedcontract.enforcer.json.navigator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonPropertyNavigatorTest {

    private JsonPropertyNavigator jsonPropertyNavigator;

    @Test
    public void navigateExistingProperty() throws Exception {

        jsonPropertyNavigator = new JsonPropertyNavigator("prop1");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : { \"sub-prop\" : 125 }  }");

        JsonNode navigatedDocument = jsonPropertyNavigator.navigate(document);

        assertTrue(navigatedDocument.has("sub-prop"));
    }

    @Test
    public void navigateNonExistingProperty() throws Exception {

        jsonPropertyNavigator = new JsonPropertyNavigator("prop2");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : { \"sub-prop\" : 125 }  }");

        JsonNode navigatedDocument = jsonPropertyNavigator.navigate(document);

        assertNull(navigatedDocument);



    }
}