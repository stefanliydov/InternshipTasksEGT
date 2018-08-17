package com.egtinteractive.enums;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.egtinteractive.XMLObject;
import com.google.common.base.Defaults;

public enum Format {

    JSON {

	@Override
	public String serialize(final Object obj) {
	    final List<Field> declaredFields = Arrays.asList(obj.getClass().getDeclaredFields()).stream()
		    .filter(x -> !x.getName().equals("this$0")).collect(Collectors.toList());
	    final StringBuilder sb = new StringBuilder();
	    sb.append("{").append(System.lineSeparator());
	    for (int i = 0; i < declaredFields.size(); i++) {
		final Field field = declaredFields.get(i);
		field.setAccessible(true);
		final String fieldName = field.getName();
		sb.append("\"").append(fieldName).append("\":");
		final Object value = getFieldValue(obj, field);
		sb.append(getFormatValueRepresentation(value));
		if (i < declaredFields.size() - 1) {
		    sb.append(",").append(System.lineSeparator());
		}
	    }
	    sb.append(System.lineSeparator()).append("}");
	    return sb.toString();
	}

	private String getFormatValueRepresentation(final Object value) {
	    if (value == null) {
		return null;
	    }
	    final Class<?> type = value.getClass();
	    if (type.isPrimitive() || value instanceof Number || type == Boolean.class || type == Character.class) {
		if (type == char.class || type == Character.class) {
		    return "'" + value + "'";
		}
		return String.valueOf(value);
	    } else if (type == String.class) {
		return "\"" + value + "\"";
	    } else if (value instanceof Collection) {
		final Collection<?> collection = (Collection<?>) value;
		final List<String> elements = new LinkedList<>();
		for (Object collectionObject : collection) {
		    elements.add(getFormatValueRepresentation(collectionObject));
		}
		return "[" + String.join(", ", elements) + "]";

	    } else if (type.isArray()) {
		final Object[] objs = getArray(value);
		final List<String> elements = new LinkedList<>();
		for (Object collectionObject : objs) {
		    elements.add(getFormatValueRepresentation(collectionObject));
		}
		return "[" + String.join(", ", elements) + "]";
	    } else {
		return this.serialize(value);
	    }
	}

	@Override
	public Object deserialize(final String str, final Class<?> objClass) {
	    final Constructor<?> constr = objClass.getDeclaredConstructors()[0];
	    final Parameter[] constructorParams = constr.getParameters();
	    final Field[] declaredFields = objClass.getDeclaredFields();
	    final String numRegex = "\"%s\":((\\d+(\\.\\d+)?))|(null)";
	    final String stringRegex = "\"%s\":\".*?\"";
	    final Object[] constructorDefaultValues = getConstructorDefaultValues(constructorParams);
	    Object obj = getObjectInstance(constr, constructorDefaultValues);
	    for (final Field field : declaredFields) {
		// final Object value = getValue(field, str, obj);
		final Pattern pattern = Pattern.compile(String.format(stringRegex, field.getName()));
		final Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
		    System.out.println(matcher.group());
		}

		field.setAccessible(true);
		// setObjectValue(field, obj, value);
	    }

	    return obj;
	}

	private Object getValue(Field field, String str, Object obj) {
	    JSONObject jo = getJsonObj(str);
	    final Object rawTypeValue = jo.get(field.getName());
	    final Object rawType = rawTypeValue.getClass();
	    final Object value;
	    final Class<?> type = field.getType();
	    if (rawType == JSONArray.class) {
		if (type.isArray()) {
		    JSONArray jsonArray = (JSONArray) rawTypeValue;
		    Object[] arr = new Object[jsonArray.size()];
		    if (jsonArray != null) {
			int len = jsonArray.size();
			for (int i = 0; i < len; i++) {
			    arr[i] = (jsonArray.get(i).toString());
			}
		    }

		    value = arr;

		} else {
		    Collection<Object> list = new ArrayList<>();
		    JSONArray jsonArray = (JSONArray) rawTypeValue;
		    if (jsonArray != null) {
			int len = jsonArray.size();
			for (int i = 0; i < len; i++) {
			    list.add(jsonArray.get(i).toString());
			}
		    }

		    value = list;
		}
	    } else if (rawType == JSONObject.class) {
		value = this.deserialize(String.valueOf(rawTypeValue), type);
	    } else {
		value = getValue(String.valueOf(rawTypeValue), type);
	    }

	    return value;
	}

	private Object getValue(final String rawTypeValue, final Class<?> type) {
	    if (type == boolean.class || type == Boolean.class) {
		return Boolean.valueOf(rawTypeValue);
	    } else if (type == byte.class || type == Byte.class) {
		return Byte.valueOf(rawTypeValue);
	    } else if (type == short.class || type == Short.class) {
		return Short.valueOf(rawTypeValue);
	    } else if (type == int.class || type == Integer.class) {
		return Integer.valueOf(rawTypeValue);
	    } else if (type == long.class || type == Long.class) {
		return Long.valueOf(rawTypeValue);
	    } else if (type == double.class || type == Double.class) {
		return Double.valueOf(rawTypeValue);
	    } else if (type == float.class || type == Float.class) {
		return Float.valueOf(rawTypeValue);
	    } else if (type == char.class || type == Character.class) {
		if (rawTypeValue.length() > 1) {
		    throw new IllegalArgumentException("Was passed string, expected char");
		}
		return rawTypeValue.charAt(0);
	    } else if (type == String.class) {
		return rawTypeValue;
	    }

	    return null;
	}

	private JSONObject getJsonObj(String str) {
	    try {
		return (JSONObject) new JSONParser().parse(str);
	    } catch (ParseException e) {
		throw new RuntimeException(e);
	    }
	}

	private Object[] getConstructorDefaultValues(final Parameter[] constructorParams) {
	    return Arrays.stream(constructorParams).map(x -> Defaults.defaultValue(x.getType())).toArray();
	}

	private Object getObjectInstance(Constructor<?> constr, Object[] constructorDefaultValues) {
	    try {
		return constr.newInstance(constructorDefaultValues);
	    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException e) {
		throw new RuntimeException(e);
	    }
	}

	private void setObjectValue(final Field field, final Object obj, final Object value) {
	    try {
		field.set(obj, value);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	    }

	}
    },
    XML

    {

	@Override
	public String serialize(final Object obj) {
	    List<Field> declaredFields = Arrays.asList(obj.getClass().getDeclaredFields()).stream()
		    .filter(x -> !x.getName().equals("this$0")).collect(Collectors.toList());
	    final StringBuilder sb = new StringBuilder();
	    sb.append("<").append(obj.getClass().getName()).append(">").append(System.lineSeparator());

	    for (Field field : declaredFields) {
		field.setAccessible(true);
		final String fieldName = field.getName();
		final Object value = getFieldValue(obj, field);
		sb.append(getFormatValueRepresentation(value, fieldName)).append(System.lineSeparator());

	    }
	    sb.append("</").append(obj.getClass().getName()).append(">");
	    return sb.toString();
	}

	private String getFormatValueRepresentation(final Object value, final String fieldName) {
	    if (value == null) {
		return "<" + fieldName + ">" + null + "</" + fieldName + ">";
	    }
	    final Class<?> type = value.getClass();
	    if (value.getClass().isPrimitive() || value instanceof Number || value.getClass() == Boolean.class
		    || value.getClass() == Character.class || value.getClass() == String.class) {
		return "<" + fieldName + ">" + value + "</" + fieldName + ">";
	    } else if (value instanceof Collection) {
		final Collection<?> collection = (Collection<?>) value;
		final StringBuilder sb = new StringBuilder();
		for (Object collectionObject : collection) {
		    final String simpleName = collectionObject.getClass().getSimpleName();
		    sb.append(getFormatValueRepresentation(collectionObject, simpleName))
			    .append(System.lineSeparator());
		}
		return "<" + fieldName + ">" + System.lineSeparator() + sb.toString() + "</" + fieldName + ">";

	    } else if (type.isArray()) {
		final Object[] objs = getArray(value);
		final StringBuilder sb = new StringBuilder();
		for (Object collectionObject : objs) {
		    final String simpleName = collectionObject.getClass().getSimpleName();
		    sb.append(getFormatValueRepresentation(collectionObject, simpleName))
			    .append(System.lineSeparator());
		}
		return "<" + fieldName + ">" + System.lineSeparator() + sb.toString() + "</" + fieldName + ">";
	    } else {
		return this.serialize(value);
	    }
	}

	@Override
	public Object deserialize(final String str, final Class<?> objClass) {

	    final Map<String, Field> declaredFields = Arrays.stream(objClass.getDeclaredFields())
		    .collect(Collectors.toMap(x -> x.getName(), x -> x));

	    final Constructor<?> constr = objClass.getDeclaredConstructors()[0];
	    final Parameter[] constructorParams = constr.getParameters();
	    final Object[] constructorDefaultValues = getConstructorDefaultValues(constructorParams);
	    final Object newObj = getObjectInstance(constr, constructorDefaultValues);

	    final XMLObject xml = new XMLObject(str);

	    for (XMLObject xmlObj : xml.getChildNodes()) {
		final Field field = declaredFields.get(xmlObj.getName());
		if (field != null) {
		    field.setAccessible(true);
		    final Class<?> targetType = field.getType();
		    final Object value = getCorrectValue(field, xmlObj.getValue(), targetType);
		    setObjectValue(field, newObj, value);
		}

	    }

	    return newObj;
	}

	private Object getCorrectValue(final Field field, final String value, final Class<?> targetType) {
	    if(value.equals("null")){
		return null;
	    }
	    if (targetType == boolean.class || targetType == Boolean.class) {
		return Boolean.valueOf(value);
	    } else if (targetType == byte.class || targetType == Byte.class) {
		return Byte.valueOf(value);
	    }else if (targetType == short.class || targetType == Short.class) {
		return Short.valueOf(value);
	    } else if (targetType == int.class || targetType == Integer.class) {
		return Integer.valueOf(value);
	    } else if (targetType == long.class || targetType == Long.class) {
		return Long.valueOf(value);
	    } else if (targetType == float.class || targetType == Float.class) {
		return Float.valueOf(value);
	    } else if (targetType == double.class || targetType == Double.class) {
		return Double.valueOf(value);
	    } else if (targetType == char.class || targetType == Character.class) {
		if (value.length() > 1) {
		    throw new RuntimeException("Character cannot be longer than 1 symbol!");
		}
		return value.charAt(0);
	    } else if (targetType == String.class) {
		return value;
	    }

	    final String xmlTarget = "<" + field.getName() + ">" + value + "</" + field.getName() + ">";

	    if (targetType.isArray()) {

		final Class<?> componentType = targetType.getComponentType();
		final XMLObject xml = new XMLObject(xmlTarget);
		final List<String> xmlObjs = new LinkedList<>();
		for (XMLObject xmlObj : xml.getChildNodes()) {
		    xmlObjs.add(xmlObj.getValue());
		}
		if (componentType.isPrimitive()) {
		    return getPrimitiveArray(componentType, xmlObjs);

		}

		final List<Object> elements = new ArrayList<>();
		for (String xmlObj : xmlObjs) {
		    elements.add(getCorrectValue(field, xmlObj, componentType));
		}

		return getGenericArray(componentType, elements);

	    }

	    if (Collection.class.isAssignableFrom(targetType)) {
		final List<Object> elements = new ArrayList<>();
		final XMLObject xml = new XMLObject(xmlTarget);
		final ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
		final Class<?> targetValue = (Class<?>) stringListType.getActualTypeArguments()[0];
		for (XMLObject xmlObj : xml.getChildNodes()) {
		    elements.add(getCorrectValue(field, xmlObj.getValue(), targetValue));
		}

		return elements;
	    }

	    return this.deserialize(xmlTarget, targetType);
	}

	private <T> T[] getGenericArray(final Class<T> clazz, final List<Object> input) {
	    @SuppressWarnings("unchecked")
	    final T[] res = (T[]) Array.newInstance(clazz, input.size());

	    int i = 0;
	    for (Object t : input) {
		res[i] = (T) t;
		i++;
	    }
	    return res;
	}

	private Object getPrimitiveArray(final Class<?> componentType, final List<String> xml) {

	    if (componentType == boolean.class) {
		final boolean[] arr = new boolean[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Boolean.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == byte.class) {
		final byte[] arr = new byte[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Byte.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == short.class) {
		final short[] arr = new short[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Short.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == int.class) {
		final int[] arr = new int[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Integer.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == long.class) {
		final long[] arr = new long[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Long.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == float.class) {
		final float[] arr = new float[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Float.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == double.class) {
		final double[] arr = new double[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    arr[i] = Double.valueOf(xml.get(i));
		}
		return arr;
	    } else if (componentType == char.class) {
		final char[] arr = new char[xml.size()];
		for (int i = 0; i < xml.size(); i++) {
		    final String element = xml.get(i);
		    if (element.length() > 1) {
			throw new IllegalArgumentException("Character cannot be more than 1 symbol long!");
		    }
		    arr[i] = element.charAt(0);
		}
		return arr;
	    }
	    return null;
	}

	private Object[] getConstructorDefaultValues(final Parameter[] constructorParams) {
	    return Arrays.stream(constructorParams).map(x -> Defaults.defaultValue(x.getType())).toArray();
	}

	private Object getObjectInstance(final Constructor<?> constr, final Object[] constructorDefaultValues) {
	    try {
		return constr.newInstance(constructorDefaultValues);
	    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException e) {
		throw new RuntimeException(e);
	    }
	}

	private void setObjectValue(final Field field, final Object obj, final Object value) {
	    try {
		field.set(obj, value);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		throw new RuntimeException(e);
	    }

	}
    };

    public abstract String serialize(final Object obj);

    public abstract Object deserialize(final String str, final Class<?> objClass);

    protected Object getFieldValue(final Object obj, final Field field) {
	try {
	    return field.get(obj);
	} catch (IllegalArgumentException | IllegalAccessException e) {
	    throw new RuntimeException(e);
	}
    }

    protected Object[] getArray(final Object val) {
	final int arrlength = Array.getLength(val);
	final Object[] outputArray = new Object[arrlength];
	for (int i = 0; i < arrlength; ++i) {
	    outputArray[i] = Array.get(val, i);
	}
	return outputArray;
    }

}
