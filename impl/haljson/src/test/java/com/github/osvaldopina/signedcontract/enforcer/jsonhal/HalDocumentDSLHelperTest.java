package com.github.osvaldopina.signedcontract.enforcer.jsonhal;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.osvaldopina.signedcontract.enforcer.Clause;
import com.github.osvaldopina.signedcontract.enforcer.CloneErrorOnlyFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static com.github.osvaldopina.signedcontract.enforcer.json.jsonpath.JsonPathDSLHelper.is;
import static com.github.osvaldopina.signedcontract.enforcer.jsonhal.HalDocumentDSLHelper.*;


public class HalDocumentDSLHelperTest {


    private String hal;

    @Before
    public void setUp() {
        StringBuffer tmp = new StringBuffer();


        tmp.append("{ \n");
        tmp.append("    \"prop1\":  \"property-1-value\", \n");
        tmp.append("    \"_links\": {\n");
        tmp.append("        \"urn:irs:test\": {\n");
        tmp.append("            \"href\": \"http://localhost{/var1,var2*}\",\n");
        tmp.append("            \"templated\": \"true\"\n");
        tmp.append("        },\n");
        tmp.append("        \"other\": {\n");
        tmp.append("            \"href\": \"http://uri\"\n");
        tmp.append("        }\n");
        tmp.append("    },\n");
        tmp.append("    \"_embedded\": {\n");
        tmp.append("        \"emb1\": {\n");
        tmp.append("            \"embedded-prop1\": \"embedded-property-2-value\"\n");
        tmp.append("        },\n");
        tmp.append("        \"emb-array\": [\n");
        tmp.append("            {\"embedded-prop-index-0\": \"embedded-prop-index-0-value\"},\n");
        tmp.append("            {\"embedded-prop-index-1\": \"embedded-prop-index-1-value\"}\n");
        tmp.append("        ]\n");
        tmp.append("    }\n");
        tmp.append("}");

        hal = tmp.toString();

    }


    @Test
    public void test() {


        Clause<JsonNode> halDocument =
                halDocument(hal,
                        resource(
                                is("$.prop1", "property-2-value")
                        ),
                        embeds(
                                embedded("emb1",
                                        validRel(),
                                        links(
                                                link("emb1-rel",
                                                        validRel()
                                                )
                                        ),
                                        resource(
                                                is("$.embedded-prop1", "embedded-property")
                                        )
                                ),
                                embeddedArray("emb-array",
                                        embeddedItem(
                                                resource(
                                                        is("$.embedded-prop1", "embedded-property-2-value")
                                                )
                                        ),
                                        embeddedItem(
                                                resource(
                                                        is("$.embedded-prop1", "embedded-property-2-value")
                                                )
                                        ),
                                        embeddedItem(
                                                resource(
                                                        is("$.embedded-prop1", "embedded-property-2-value")
                                                )
                                        )
                                )
//                                ,
//                                embedded("emb2",
//                                        resource(
//                                                is("$.embedded-prop1", "embedded-property-2-value")
//                                        )
//                                )
                        ),
                        links(
                                link("urn:irs:test",
                                        validRel(),
                                        templated(
                                                variable("var1",
                                                        pathExpansion()),
                                                variable("var2",
                                                        queryExpansion(),
                                                        exploded())
                                        )
                                ),
                                link("other",
                                        templated(
                                                variable("var1")
                                        )
                                )
                        )
                );

        HalDocumentErrorPrintWalker halDocumentErrorPrintWalker =
                new HalDocumentErrorPrintWalker(new PrintWriter(System.out));

        Clause<JsonNode> onlyErrors = halDocument.cloneClause(CloneErrorOnlyFilter.INSTANCE);

        halDocumentErrorPrintWalker.walk(onlyErrors);


    }
}