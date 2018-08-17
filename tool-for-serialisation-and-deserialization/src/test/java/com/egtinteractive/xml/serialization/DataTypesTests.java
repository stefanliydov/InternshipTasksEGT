package com.egtinteractive.xml.serialization;

import static com.egtinteractive.resources.MapperReflection.getMapperIO;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.resources.GenericFieldClass;
import com.egtinteractive.resources.MyDataProvider;
import com.egtinteractive.resources.TestingIO;

public class DataTypesTests {

    @DataProvider(name = "getMapper")
    public Object[][] getXmlMapper() {
	return MyDataProvider.getXmlMapper();
    }

    @Test(dataProvider = "getMapper")
    public void intShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(5));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>5</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void byteShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(2));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>2</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void shortShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(7));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>7</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void longShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(3000));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>3000</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void floatShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(5.913f));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>5.913</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void doubleShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(3.14d));
	TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>3.14</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void booleanShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(true));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>true</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void charShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>('h'));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>h</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void stringShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>("This is a sentence"));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>This is a sentence</value>" + System.lineSeparator()
		+ "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void nullShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericFieldClass<>(null));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericFieldClass>" + System.lineSeparator()
		+ "<value>null</value>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericFieldClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

}
