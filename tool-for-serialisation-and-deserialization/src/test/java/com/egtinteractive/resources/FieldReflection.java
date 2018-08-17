package com.egtinteractive.resources;

import java.lang.reflect.Field;

public class FieldReflection {

    public static Object getFieldValue(final String fieldName, final Object obj) {
	try {
	    Field field = obj.getClass().getDeclaredField(fieldName);
	    field.setAccessible(true);
	     Object object = field.get(obj);
	     return object;
	} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
	    throw new RuntimeException(e);
	}
    }

}
