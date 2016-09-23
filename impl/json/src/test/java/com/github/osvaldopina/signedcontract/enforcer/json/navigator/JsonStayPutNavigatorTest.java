package com.github.osvaldopina.signedcontract.enforcer.json.navigator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonStayPutNavigatorTest {

    private JsonStayPutNavigator jsonStayPutNavigator;


    @Test
    public void navigate() throws Exception {
        jsonStayPutNavigator = new JsonStayPutNavigator();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"prop1\" : { \"sub-prop\" : 125 }  }");

        assertSame(document, jsonStayPutNavigator.navigate(document));
    }

}