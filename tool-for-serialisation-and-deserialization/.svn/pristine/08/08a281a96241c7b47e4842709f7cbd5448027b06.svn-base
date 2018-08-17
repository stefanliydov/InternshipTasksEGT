package com.egtinteractive.xml.deserialization;

import static com.egtinteractive.resources.MapperReflection.getMapperIO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.ObjectMapper;
import com.egtinteractive.resources.MyDataProvider;
import com.egtinteractive.resources.TestingIO;
import com.egtinteractive.resources.test_classes.A;
import com.egtinteractive.resources.test_classes.B;

public class ListFieldsTests {
    @DataProvider(name = "getMapper")
    public Object[][] getXmlMapper() {
	return MyDataProvider.getXmlMapper();
    }

    @Test(dataProvider = "getMapper")
    public void listOfIntegersShouldBeShownCorrectly(final ObjectMapper mapper) {
	final List<Integer> list = new ArrayList<>();
	final int size = ThreadLocalRandom.current().nextInt(10000);

	for (int i = 0; i < size; i++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}

	final A a = new A();
	a.setListSeries(list);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(list, result.getListSeries());

    }

    @Test(dataProvider = "getMapper")
    public void listOfObjectsShouldBeShownCorrectly(final ObjectMapper mapper) {
	final List<B> list = new ArrayList<>();
	final int size = ThreadLocalRandom.current().nextInt(100);

	for (int i = 0; i < size; i++) {
	    list.add(new B(ThreadLocalRandom.current().nextInt()));
	}

	final A a = new A();
	a.setListObj(list);

	mapper.serialize(a);
	final TestingIO io = getMapperIO(mapper);
	A result = (A) mapper.deserialize(io.sb.toString(), A.class);
	Assert.assertEquals(list, result.getListObj());
    }

}
