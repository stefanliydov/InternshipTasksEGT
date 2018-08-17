package com.egtinteractive.xml.serialization;

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
    public Object[][] getXmlMapper() {
	return MyDataProvider.getXmlMapper();
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfCharsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new Character[] { 'a', 'b', 'c' }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericArrayClass>" + System.lineSeparator()
		+ "<arr>" + System.lineSeparator() + "<Character>a</Character>" + System.lineSeparator()
		+ "<Character>b</Character>" + System.lineSeparator() + "<Character>c</Character>"
		+ System.lineSeparator() + "</arr>" + System.lineSeparator()
		+ "</com.egtinteractive.resources.GenericArrayClass>";

	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfDoublesShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new Double[] { 2.21, 3.22, 4.23 }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericArrayClass>" + System.lineSeparator()
		+ "<arr>" + System.lineSeparator() + "<Double>2.21</Double>" + System.lineSeparator()
		+ "<Double>3.22</Double>" + System.lineSeparator() + "<Double>4.23</Double>" + System.lineSeparator()
		+ "</arr>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericArrayClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfIntsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new Integer[] { 4, 8, 64 }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericArrayClass>" + System.lineSeparator()
		+ "<arr>" + System.lineSeparator() + "<Integer>4</Integer>" + System.lineSeparator()
		+ "<Integer>8</Integer>" + System.lineSeparator() + "<Integer>64</Integer>" + System.lineSeparator()
		+ "</arr>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericArrayClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfObjectsShouldBeShownCorrectly(final ObjectMapper mapper) {

	mapper.serialize(
		new GenericArrayClass<>(new Object[] { new TestClass(5), new TestClass(25), new TestClass(125) }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericArrayClass>" + System.lineSeparator()
		+ "<arr>" + System.lineSeparator() + "<com.egtinteractive.resources.TestClass>" + System.lineSeparator()
		+ "<num>5</num>" + System.lineSeparator() + "</com.egtinteractive.resources.TestClass>"
		+ System.lineSeparator() + "<com.egtinteractive.resources.TestClass>" + System.lineSeparator()
		+ "<num>25</num>" + System.lineSeparator() + "</com.egtinteractive.resources.TestClass>"
		+ System.lineSeparator() + "<com.egtinteractive.resources.TestClass>" + System.lineSeparator()
		+ "<num>125</num>" + System.lineSeparator() + "</com.egtinteractive.resources.TestClass>"
		+ System.lineSeparator() + "</arr>" + System.lineSeparator()
		+ "</com.egtinteractive.resources.GenericArrayClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfStringsShouldBeShownCorrectly(final ObjectMapper mapper) {
	mapper.serialize(new GenericArrayClass<>(new String[] { "aa", "ab", "ac" }));
	final TestingIO io = getMapperIO(mapper);
	final String expectedResult = "<com.egtinteractive.resources.GenericArrayClass>" + System.lineSeparator()
		+ "<arr>" + System.lineSeparator() + "<String>aa</String>" + System.lineSeparator()
		+ "<String>ab</String>" + System.lineSeparator() + "<String>ac</String>" + System.lineSeparator()
		+ "</arr>" + System.lineSeparator() + "</com.egtinteractive.resources.GenericArrayClass>";
	Assert.assertEquals(io.sb.toString(), expectedResult);
    }

}
