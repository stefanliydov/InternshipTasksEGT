package com.egtinteractive.xml.deserialization;

import static com.egtinteractive.resources.MapperReflection.getMapperIO;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.resources.MyDataProvider;
import com.egtinteractive.resources.TestingIO;
import com.egtinteractive.resources.test_classes.A;

public class DataTypesTests {
    @DataProvider(name = "getMapper")
    public Object[][] getXmlMapper() {
	return MyDataProvider.getXmlMapper();
    }

    @Test(dataProvider = "getMapper")
    public void intShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Integer valueWr = ThreadLocalRandom.current().nextInt();
	final int valuePr = ThreadLocalRandom.current().nextInt();

	final A a = new A();
	a.setIntPr(valuePr);
	a.setIntegerWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getIntPr());
	Assert.assertEquals(valueWr, result.getIntegerWr());

    }

    @Test(dataProvider = "getMapper")
    public void byteShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Byte valueWr = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE + 1);
	final byte valuePr = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE + 1);

	final A a = new A();
	a.setBytePr(valuePr);
	a.setByteWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getBytePr());
	Assert.assertEquals(valueWr, result.getByteWr());

    }

    @Test(dataProvider = "getMapper")
    public void shortShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Short valueWr = (short) ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);
	final short valuePr = (short) ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);

	final A a = new A();
	a.setShortPr(valuePr);
	a.setShortWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getShortPr());
	Assert.assertEquals(valueWr, result.getShortWr());

    }

    @Test(dataProvider = "getMapper")
    public void longShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Long valueWr = ThreadLocalRandom.current().nextLong();
	final long valuePr = ThreadLocalRandom.current().nextLong();

	final A a = new A();
	a.setLongPr(valuePr);
	a.setLongWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getLongPr());
	Assert.assertEquals(valueWr, result.getLongWr());

    }

    @Test(dataProvider = "getMapper")
    public void floatShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Float valueWr = (float) ThreadLocalRandom.current().nextDouble(Float.MIN_VALUE, Float.MAX_VALUE + 1);
	final float valuePr = (float) ThreadLocalRandom.current().nextDouble(Float.MIN_VALUE, Float.MAX_VALUE + 1);

	final A a = new A();
	a.setFloatPr(valuePr);
	a.setFloatWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getFloatPr());
	Assert.assertEquals(valueWr, result.getFloatWr());

    }

    @Test(dataProvider = "getMapper")
    public void doubleShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Double valueWr = ThreadLocalRandom.current().nextDouble();
	final double valuePr = ThreadLocalRandom.current().nextDouble();

	final A a = new A();
	a.setDoublePr(valuePr);
	a.setDoubleWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getDoublePr());
	Assert.assertEquals(valueWr, result.getDoubleWr());

    }

    @Test(dataProvider = "getMapper")
    public void booleanShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Boolean valueWr = ThreadLocalRandom.current().nextDouble(0, 2) < 0.5;
	final boolean valuePr = ThreadLocalRandom.current().nextDouble(0, 2) < 0.5;

	final A a = new A();
	a.setBoolPr(valuePr);
	a.setBoolWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getBoolPr());
	Assert.assertEquals(valueWr, result.getBoolWr());
    }

    @Test(dataProvider = "getMapper")
    public void charShouldBeShownCorrectly(final ObjectMapper mapper) {
	final Character valueWr = UUID.randomUUID().toString().charAt(0);
	final char valuePr = UUID.randomUUID().toString().charAt(0);

	final A a = new A();
	a.setCharPr(valuePr);
	a.setCharWr(valueWr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(valuePr, result.getCharPr());
	Assert.assertEquals(valueWr, result.getCharWr());

    }

    @Test(dataProvider = "getMapper")
    public void stringShouldBeShownCorrectly(final ObjectMapper mapper) {
	final String value = UUID.randomUUID().toString();

	final A a = new A();
	a.setWord(value);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(value, result.getWord());

    }

    @Test(dataProvider = "getMapper")
    public void nullShouldBeShownCorrectly(final ObjectMapper mapper) {
	final String value = null;

	final A a = new A();
	a.setWord(value);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(value, result.getWord());
    }

}
