package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import static org.junit.Assert.*;

public class JSON_TYPETest {

    @Test
    public void fromJavaTypeObjectString() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : \"value\" } ", "$.prop1");

        assertEquals(JSON_TYPE.STRING, JSON_TYPE.fromJavaType(value));


    }

    @Test
    public void fromJavaTypeClassString() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : \"value\" } ", "$.prop1");

        assertEquals(JSON_TYPE.STRING, JSON_TYPE.fromJavaType(value.getClass()));

    }

    @Test
    public void fromJavaTypeObjectNumberInteger() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : 100 } ", "$.prop1");

        assertEquals(JSON_TYPE.NUMBER, JSON_TYPE.fromJavaType(value));


    }

    @Test
    public void fromJavaTypeClassNumberInteger() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : 100 } ", "$.prop1");

        assertEquals(JSON_TYPE.NUMBER, JSON_TYPE.fromJavaType(value.getClass()));

    }

    @Test
    public void fromJavaTypeObjectNumberDecimal() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : 100.34 } ", "$.prop1");

        assertEquals(JSON_TYPE.NUMBER, JSON_TYPE.fromJavaType(value));


    }

    @Test
    public void fromJavaTypeClassNumberDecimal() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : 100.34 } ", "$.prop1");

        assertEquals(JSON_TYPE.NUMBER, JSON_TYPE.fromJavaType(value.getClass()));

    }

    @Test
    public void fromJavaTypeObjectBoolean() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : true } ", "$.prop1");

        assertEquals(JSON_TYPE.BOOLEAN, JSON_TYPE.fromJavaType(value));


    }

    @Test
    public void fromJavaTypeClassBoolean() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : true } ", "$.prop1");

        assertEquals(JSON_TYPE.BOOLEAN, JSON_TYPE.fromJavaType(value.getClass()));

    }

    @Test
    public void fromJavaTypeObjectArray() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : [] } ", "$.prop1");

        assertEquals(JSON_TYPE.ARRAY, JSON_TYPE.fromJavaType(value));


    }

    @Test
    public void fromJavaTypeClassArray() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : [] } ", "$.prop1");

        assertEquals(JSON_TYPE.ARRAY, JSON_TYPE.fromJavaType(value.getClass()));

    }

    @Test
    public void fromJavaTypeObjectObject() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : {}  } ", "$.prop1");

        assertEquals(JSON_TYPE.OBJECT, JSON_TYPE.fromJavaType(value));


    }

    @Test
    public void fromJavaTypeClassObject() throws Exception {

        Object value = JsonPath.read("{ \"prop1\" : {} } ", "$.prop1");

        assertEquals(JSON_TYPE.OBJECT, JSON_TYPE.fromJavaType(value.getClass()));

    }

}