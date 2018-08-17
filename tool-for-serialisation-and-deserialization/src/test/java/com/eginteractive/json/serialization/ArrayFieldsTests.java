package com.eginteractive.json.serialization;

import static com.egtinteractive.resources.MapperReflection.getMapperIO;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.resources.GenericArrayClass;
import com.egtinteractive.resources.MyDataProvider;
import com.egtinteractive.resources.TestClass;
import com.egtinteractive.resources.TestingIO;

public class ArrayFieldsTests {
    @DataProvider(name = "getMapper")
    public Object[][] getJsonMapper() {
	return MyDataProvider.getJsonMapper();
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfCharsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new Character[] { 'a', 'b', 'c' }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"arr\":['a', 'b', 'c']" + System.lineSeparator()
		+ "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfDoublesShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new Double[] { 2.21, 3.22, 4.23 }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"arr\":[2.21, 3.22, 4.23]"
		+ System.lineSeparator() + "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfIntsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new Integer[] { 4, 8, 64 }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"arr\":[4, 8, 64]" + System.lineSeparator()
		+ "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfObjectsShouldBeShownCorrectly(final ObjectMapper mapper) {

	mapper.serialize(
		new GenericArrayClass<>(new Object[] { new TestClass(5), new TestClass(25), new TestClass(125) }));

	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"arr\":[{" + System.lineSeparator() + "\"num\":5"
		+ System.lineSeparator() + "}, {" + System.lineSeparator() + "\"num\":25" + System.lineSeparator()
		+ "}, {" + System.lineSeparator() + "\"num\":125" + System.lineSeparator() + "}]"
		+ System.lineSeparator() + "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfStringsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new String[] { "aa", "ab", "ac" }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"arr\":[\"aa\", \"ab\", \"ac\"]"
		+ System.lineSeparator() + "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

}
