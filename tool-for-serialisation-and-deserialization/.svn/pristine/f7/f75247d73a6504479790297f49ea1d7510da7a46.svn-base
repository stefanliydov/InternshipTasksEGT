package com.egtinteractive.resources;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.enums.Format;

public class MapperFactory {

    public static ObjectMapper createJsonObjectMapper() {
	return new ObjectMapper(new TestingIO(), Format.JSON);
    }

    public static ObjectMapper createXmlObjectMapper() {
	return new ObjectMapper(new TestingIO(), Format.XML);
    }
}
