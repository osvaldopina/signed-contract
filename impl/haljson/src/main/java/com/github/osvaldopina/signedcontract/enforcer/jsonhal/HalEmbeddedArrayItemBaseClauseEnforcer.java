package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;

public interface HalEmbeddedArrayItemBaseClauseEnforcer extends ClauseEnforcer<JsonNode> {

    void setIndex(int index);

    void setRel(String rel);

    boolean consumesIndex();

}
