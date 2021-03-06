package com.egtinteractive.xml.deserialization;

import static com.egtinteractive.resources.MapperReflection.getMapperIO;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.resources.MyDataProvider;
import com.egtinteractive.resources.TestingIO;
import com.egtinteractive.resources.test_classes.A;
import com.egtinteractive.resources.test_classes.B;

public class ArrayFieldsTests {
    @DataProvider(name = "getMapper")
    public Object[][] getXmlMapper() {
	return MyDataProvider.getXmlMapper();
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfFloatsShouldBeShownCorrectly(final ObjectMapper mapper) {
	final int size = ThreadLocalRandom.current().nextInt(10000);
	final Float[] arr = new Float[size];

	for (int i = 0; i < size; i++) {
	    arr[i] = (float) (ThreadLocalRandom.current().nextDouble(Float.MIN_VALUE, Float.MAX_VALUE + 1));
	}

	final A a = new A();
	a.setArrWr(arr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(arr, result.getArrWr());
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfIntsShouldBeShownCorrectly(final ObjectMapper mapper) {
	final int size = ThreadLocalRandom.current().nextInt(10000);
	final int[] arr = new int[size];

	for (int i = 0; i < size; i++) {
	    arr[i] = ThreadLocalRandom.current().nextInt();
	}

	final A a = new A();
	a.setArrPr(arr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(arr, result.getArrPr());
    }

    @Test(dataProvider = "getMapper")
    public void arrayOfObjectsShouldBeShownCorrectly(final ObjectMapper mapper) {

	final int size = ThreadLocalRandom.current().nextInt(10000);
	final B[] arr = new B[size];

	for (int i = 0; i < size; i++) {
	    arr[i] = new B(ThreadLocalRandom.current().nextInt());
	}

	final A a = new A();
	a.setArrObj(arr);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(arr, result.getArrObj());
    }

}
