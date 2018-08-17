package com.egtinteractive.resources;

public class MyDataProvider {

    public static Object[][] getJsonMapper() {
	return new Object[][] { { MapperFactory.createJsonObjectMapper() } };
    }

    public static Object[][] getXmlMapper() {
	return new Object[][] { { MapperFactory.createXmlObjectMapper() } };
    }
}
