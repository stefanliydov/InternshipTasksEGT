package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.List;
import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class ContainsTests {
    private static final int ZERO = 0;
    private int size;

    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getListDataProvider();
    }

    @BeforeTest
    public void setSize() {
	size = NumberGenerator.generate(25, 50);
    }

    @Test(dataProvider = "getClass")
    public void containsShouldReturnTrueIfContains(List<String> list) {

	for (int i = ZERO; i < size; i++) {
	    list.add(new String(i + ""));
	}

	for (int i = ZERO; i < size; i++) {
	    Assert.assertTrue(list.contains(new String(i + "")));
	}

    }

    @Test(dataProvider = "getClass")
    public void containsShouldReturnFalseIfNotContains(List<Integer> list) {
	for (int i = ZERO; i < size; i++) {
	    list.add(i);
	}

	for (int i = ZERO; i < size; i++) {
	    final int num = NumberGenerator.generate(51, 200);
	    Assert.assertFalse(list.contains(num));
	}
    }

    @Test(dataProvider = "getClass")
    public void containsOnAnEmptyListShouldReturnFalse(List<Integer> list) {
	final boolean result = list.contains(NumberGenerator.generate(25));
	Assert.assertFalse(result);
    }

    @Test(dataProvider = "getClass")
    public void addNullContainsShouldWork(List<Integer> list) {
	final int size = NumberGenerator.generate(50, 200);
	for (int i = ZERO; i < size; i++) {
	    list.add(i);
	}
	final int middleIndex = size / 2;
	list.add(middleIndex, null);

	final boolean result = list.contains(null);
	Assert.assertTrue(result);

    }
}
