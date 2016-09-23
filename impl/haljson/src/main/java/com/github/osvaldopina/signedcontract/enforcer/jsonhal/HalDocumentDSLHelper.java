package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.SignedContractException;
import com.github.osvaldopina.signedcontract.enforcer.json.jsonpath.JsonPathClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.uritemplate.UriTemplateDSLHelper;
import com.github.osvaldopina.signedcontract.enforcer.uritemplate.UriTemplateVariablePropertyClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.uritemplate.UriTemplatedVariableClauseEnforcer;

import java.io.IOException;
import java.util.Arrays;

public class HalDocumentDSLHelper {

    public static <E extends HalDocumentPartClauseEnforcer> Clause<JsonNode> halDocument(String document, E... halClauses) {

        HalDocumentClauseEnforcer halDocumentClauseEnforcer = new HalDocumentClauseEnforcer(Arrays.asList(halClauses));

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode documentJsonNode = mapper.readTree(document);
            return halDocumentClauseEnforcer.enforce(documentJsonNode);
        } catch (IOException e) {
            throw new SignedContractException("Error reading document " + e, e);
        }
    }

    public static HalLinkListClauseEnforcer links(HalLinkClauseEnforcer... halLinkClauseEnforcers) {
        return new HalLinkListClauseEnforcer(Arrays.asList(halLinkClauseEnforcers));
    }

    public static HalResourceClauseEnforcer resource(JsonPathClauseEnforcer... jsonPathClauseEnforcers) {
        return new HalResourceClauseEnforcer(Arrays.asList(jsonPathClauseEnforcers));
    }

    public static <E extends HalDocumentPartClauseEnforcer> HalEmbeddedListClauseEnforcer embeds(HalBaseEmbeddedResourceClauseEnforcer... subDocuments) {
        return new HalEmbeddedListClauseEnforcer(Arrays.asList(subDocuments));
    }

    public static <E extends HalEmbeddedPartClauseEnforcer> HalBaseEmbeddedResourceClauseEnforcer embedded(String rel, E... subDocuments) {
        return new HalEmbeddedResourceClauseEnforcer(rel, Arrays.asList(subDocuments));
    }

    public static HalEmbeddedArrayClauseEnforcer embeddedArray(String rel, HalEmbeddedArrayItemClauseEnforcer... subDocuments) {
        return new HalEmbeddedArrayClauseEnforcer( rel, Arrays.asList(subDocuments));
    }

    public static  HalEmbeddedArrayItemClauseEnforcer embeddedItem(HalEmbeddedPartClauseEnforcer... subDocuments) {
        return new HalEmbeddedArrayItemClauseEnforcer(Arrays.asList(subDocuments));
    }

    public static <E extends HalLinkPropertyClauseEnforcer> HalLinkClauseEnforcer link(String rel, E... linkPropertyClauses) {
        return new HalLinkClauseEnforcer(rel, Arrays.asList(linkPropertyClauses));
    }

    public static HalLinkValidRelClauseEnforcer validRel() {
        return new HalLinkValidRelClauseEnforcer();
    }

    public static HalLinkPropertyClauseEnforcer validHref() {
        return new HalLinkValidHRefClauseEnforcer();
    }

    public static HalLinkPropertyClauseEnforcer templated(UriTemplatedVariableClauseEnforcer... variables) {

        return new HalLinkPropertyAggregatorClause(Arrays.asList(
                new HalLinkPropertyValueClauseEnforcer("templated", "true"),
                new HalLinkTemplatedUriClauseEnforcer(Arrays.asList(variables))

        ));
    }

    public static HalLinkPropertyClauseEnforcer notTemplated() {
        return new HalLinkPropertyValueClauseEnforcer("templated", "false", false);
    }

    public static HalLinkPropertyClauseEnforcer hasName(String name) {
        return new HalLinkPropertyValueClauseEnforcer("name", name);
    }

    public static HalLinkPropertyClauseEnforcer hasProfile(String profile) {
        return new HalLinkPropertyValueClauseEnforcer("profile", profile);
    }

    public static HalLinkPropertyClauseEnforcer hasHrefLang(String hrefLang) {
        return new HalLinkPropertyValueClauseEnforcer("hreflang", hrefLang);
    }

    public static UriTemplatedVariableClauseEnforcer variable(String variable,
                                                              UriTemplateVariablePropertyClauseEnforcer... uriTemplateVariablePropertyClauseEnforcers) {
        return UriTemplateDSLHelper.variable(variable, uriTemplateVariablePropertyClauseEnforcers);
    }

    public static UriTemplateVariablePropertyClauseEnforcer exploded() {
        return UriTemplateDSLHelper.exploded();
    }

    public static UriTemplateVariablePropertyClauseEnforcer defaulExpansion() {
        return UriTemplateDSLHelper.defaulExpansion();
    }

    public static UriTemplateVariablePropertyClauseEnforcer pathExpansion() {
        return UriTemplateDSLHelper.pathExpansion();
    }

    public static UriTemplateVariablePropertyClauseEnforcer queryExpansion() {
        return UriTemplateDSLHelper.queryExpansion();
    }

    public static UriTemplateVariablePropertyClauseEnforcer matrixExpansion() {
        return UriTemplateDSLHelper.matrixExpansion();
    }

    public static UriTemplateVariablePropertyClauseEnforcer continuationExpansion() {
        return UriTemplateDSLHelper.continuationExpansion();
    }

    public static UriTemplateVariablePropertyClauseEnforcer fragmentExpansion() {
        return UriTemplateDSLHelper.fragmentExpansion();
    }
}
