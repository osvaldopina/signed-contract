package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;

public class HalLinkTemplatedUriTemplateNavigator implements Navigator<JsonNode, UriTemplate> {

    @Override
    public UriTemplate navigate(JsonNode document) {

        if (document.has("href")) {
            String href = document.get("href").asText();
            return UriTemplate.fromTemplate(href);
        }
        else {
            throw new SignedContractException("hal link without href property is not valid");
        }
    }


}
