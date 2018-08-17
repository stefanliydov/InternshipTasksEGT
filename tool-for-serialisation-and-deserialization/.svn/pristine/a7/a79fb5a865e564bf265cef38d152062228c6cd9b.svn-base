package com.egtinteractive.resources;

import java.lang.reflect.Field;

import com.egtinteractive.ObjectMapper;

public class MapperReflection {

    public static TestingIO getMapperIO(final ObjectMapper mapper) {
	try {
	    Field field = mapper.getClass().getDeclaredField("io");
	    field.setAccessible(true);
	    return (TestingIO) field.get(mapper);

	} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
	    throw new RuntimeException(e);
	}
    }

}
