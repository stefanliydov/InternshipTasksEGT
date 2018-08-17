package com.egtinteractive.xml.serialization;

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
    public Object[][] getXmlMapper() {
	return MyDataProvider.getXmlMapper();
    }

    @Test(dataProvider = "getMapper")
    public void listOfCharsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList('a', 'b', 'c')));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericListClass>" + System.lineSeparator()
		+ "<list>" + System.lineSeparator() + "<Character>a</Character>" + System.lineSeparator()
		+ "<Character>b</Character>" + System.lineSeparator() + "<Character>c</Character>"
		+ System.lineSeparator() + "</list>" + System.lineSeparator()
		+ "</com.egtinteractive.resources.GenericListClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfDoublesShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList(2.21, 3.22, 4.23)));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericListClass>" + System.lineSeparator()
		+ "<list>" + System.lineSeparator() + "<Double>2.21</Double>" + System.lineSeparator()
		+ "<Double>3.22</Double>" + System.lineSeparator() + "<Double>4.23</Double>" + System.lineSeparator()
		+ "</list>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericListClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfIntsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList(4, 8, 64)));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericListClass>" + System.lineSeparator()
		+ "<list>" + System.lineSeparator() + "<Integer>4</Integer>" + System.lineSeparator()
		+ "<Integer>8</Integer>" + System.lineSeparator() + "<Integer>64</Integer>" + System.lineSeparator()
		+ "</list>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericListClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfObjectsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(
		new GenericListClass<>(Arrays.asList(new TestClass(5), new TestClass(25), new TestClass(125))));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericListClass>" + System.lineSeparator()
		+ "<list>" + System.lineSeparator() + "<com.egtinteractive.resources.TestClass>"
		+ System.lineSeparator() + "<num>5</num>" + System.lineSeparator()
		+ "</com.egtinteractive.resources.TestClass>" + System.lineSeparator()
		+ "<com.egtinteractive.resources.TestClass>" + System.lineSeparator() + "<num>25</num>"
		+ System.lineSeparator() + "</com.egtinteractive.resources.TestClass>" + System.lineSeparator()
		+ "<com.egtinteractive.resources.TestClass>" + System.lineSeparator() + "<num>125</num>"
		+ System.lineSeparator() + "</com.egtinteractive.resources.TestClass>" + System.lineSeparator()
		+ "</list>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericListClass>";

	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void listOfStringsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericListClass<>(Arrays.asList("aa", "ab", "ac")));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericListClass>" + System.lineSeparator()
		+ "<list>" + System.lineSeparator() + "<String>aa</String>" + System.lineSeparator()
		+ "<String>ab</String>" + System.lineSeparator() + "<String>ac</String>" + System.lineSeparator()
		+ "</list>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericListClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }
}
