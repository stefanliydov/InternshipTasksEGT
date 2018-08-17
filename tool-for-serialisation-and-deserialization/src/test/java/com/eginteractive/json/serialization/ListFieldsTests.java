package com.eginteractive.json.serialization;

import static com.egtinteractive.resources.MapperReflection.getMapperIO;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.resources.GenericListClass;
import com.egtinteractive.resources.MyDataProvider;
import com.egtinteractive.resources.TestClass;
import com.egtinteractive.resources.TestingIO;

public class ListFieldsTests {
    @DataProvider(name = "getMapper")
    public Object[][] getJsonMapper() {
	return MyDataProvider.getJsonMapper();
    }

    @Test(dataProvider = "getMapper")
    public void listOfCharsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList('a', 'b', 'c')));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"list\":['a', 'b', 'c']" + System.lineSeparator()
		+ "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfDoublesShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList(2.21, 3.22, 4.23)));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"list\":[2.21, 3.22, 4.23]"
		+ System.lineSeparator() + "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfIntsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList(4, 8, 64)));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"list\":[4, 8, 64]" + System.lineSeparator()
		+ "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfObjectsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(
		new GenericListClass<>(Arrays.asList(new TestClass(5), new TestClass(25), new TestClass(125))));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"list\":[{" + System.lineSeparator()
		+ "\"num\":5" + System.lineSeparator() + "}, {" + System.lineSeparator() + "\"num\":25"
		+ System.lineSeparator() + "}, {" + System.lineSeparator() + "\"num\":125" + System.lineSeparator()
		+ "}]" + System.lineSeparator() + "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfStringsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList("aa", "ab", "ac")));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "{" + System.lineSeparator() + "\"list\":[\"aa\", \"ab\", \"ac\"]"
		+ System.lineSeparator() + "}";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }
}
