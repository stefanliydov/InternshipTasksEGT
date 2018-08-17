package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class HashCodeTests {

    private static final int ONE = 1;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData() {
	return MyDataProvider.getDoubleMapDataProvider();
    }

    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(150, 700);
    }

    @Test(dataProvider = "getClassStrategy")
    public void hashCodeOnEmptyMapsShouldReturnSameResult(Map<String, Integer> map1, Map<String, Integer> map2) {
	Assert.assertEquals(map1.hashCode(), map2.hashCode());

    }

    @Test(dataProvider = "getClassStrategy")
    public void hashCodeOnMapsWithSameContentShouldReturnSameResult(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    map1.put(String.valueOf(i), i);
	    map2.put(String.valueOf(i), i);
	}

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	Assert.assertEquals(result1, result2);

    }

    @Test(dataProvider = "getClassStrategy")
    public void hashCodeOnMapsWithDifferentValuesShouldReturnDifferentResult(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    final int num1 = NumberGenerator.generate(30000);
	    final int num2 = NumberGenerator.generate(30000);
	    map1.put(String.valueOf(i), num1);
	    map2.put(String.valueOf(i), num2);
	}

	map1.put("ABC", null);
	map2.put("ABC", null);

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	Assert.assertNotEquals(result1, result2);

    }

    @Test(dataProvider = "getClassStrategy")
    public void hashCodeOnMapsWithDifferentKeysShouldReturnDifferentResults(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    final int num1 = NumberGenerator.generate(30000);
	    final int num2 = NumberGenerator.generate(30000);
	    map1.put(String.valueOf(num1), i);
	    map2.put(String.valueOf(num2), i);
	}

	map1.put("ABC", null);
	map2.put("ABC", null);

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	Assert.assertNotEquals(result1, result2);

    }

    @Test(dataProvider = "getClassStrategy")
    public void hashCodesOnMapsWithSlightDIfferenceInContentShouldRetunDifferentResult(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    map1.put(String.valueOf(i), i);
	    map2.put(String.valueOf(i), i);
	}
	map1.put("ABC", 3123123);

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	Assert.assertNotEquals(result1, result2);

    }

    @Test(dataProvider = "getClassStrategy")
    public void hashCodeOnMapsWithSameKeysButAllNullValuesShouldReturnSameResult(Map<String, Integer> map1,
	    Map<String, Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    map1.put(String.valueOf(i), null);
	    map2.put(String.valueOf(i), null);
	}

	final int result1 = map1.hashCode();
	final int result2 = map2.hashCode();
	Assert.assertEquals(result1, result2);

    }
}
