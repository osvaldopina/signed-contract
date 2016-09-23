package com.github.osvaldopina.signedcontract.enforcer.json.jsonpath;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonValuePrinterTest {


    @Test
    public void printString() {
        assertEquals("[\"value\"]", JsonValuePrinter.print("value"));
    }

    @Test
    public void printOtherType() {
        assertEquals("[123]", JsonValuePrinter.print(123));
    }
}