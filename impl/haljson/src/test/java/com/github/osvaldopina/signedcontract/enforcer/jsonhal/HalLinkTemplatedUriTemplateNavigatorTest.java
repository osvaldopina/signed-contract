package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;
import org.junit.Test;

import static org.junit.Assert.*;

public class HalLinkTemplatedUriTemplateNavigatorTest {


    private HalLinkTemplatedUriTemplateNavigator halLinkTemplatedUriTemplateNavigator;

    @Test
    public void navigate() throws Exception {

        halLinkTemplatedUriTemplateNavigator = new HalLinkTemplatedUriTemplateNavigator();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\": \"template{var1}\" }");

        UriTemplate uriTemplate = halLinkTemplatedUriTemplateNavigator.navigate(document);

        assertEquals("template{var1}",uriTemplate.getTemplate());

    }

    @Test(expected = MalformedUriTemplateException.class)
    public void navigateInvalidUriTemplate() throws Exception {

        halLinkTemplatedUriTemplateNavigator = new HalLinkTemplatedUriTemplateNavigator();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ \"href\": \"template{var-1}\" }");

        halLinkTemplatedUriTemplateNavigator.navigate(document);

    }

    @Test(expected = SignedContractException.class)
    public void navigateNoHRefProperty() throws Exception {

        halLinkTemplatedUriTemplateNavigator = new HalLinkTemplatedUriTemplateNavigator();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode document = mapper.readTree("{ }");

        UriTemplate uriTemplate = halLinkTemplatedUriTemplateNavigator.navigate(document);

    }


}