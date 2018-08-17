package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class ContainsValueTests {

    private static final int ONE = 1;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData() {
	return MyDataProvider.getMapDataProvider();
    }

    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(150, 200);
    }

    @Test(dataProvider = "getClassStrategy")
    public void containsShouldReturnTrueWhenValueExists(Map<Character, Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put((char) i, i);
	}
	for (int i = ONE; i < this.size; i++) {
	    final boolean result = map.containsValue(i);
	    Assert.assertTrue(result);
	}
    }

    @Test(dataProvider = "getClassStrategy")
    public void containsShouldReturnFalseIfValueDoesNotExists(Map<Character, Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put((char) i, i);
	}

	final int secondSize = NumberGenerator.generate(300, 500);
	for (int i = this.size; i < secondSize; i++) {
	    final boolean result = map.containsValue(i);
	    Assert.assertFalse(result);
	}
    }

    @Test(dataProvider = "getClassStrategy")
    public void containsShouldReturnFalseInAnEmptyMap(Map<Character, Integer> map) {
	Assert.assertFalse(map.containsValue(NumberGenerator.generate(30)));

    }

    @Test(dataProvider = "getClassStrategy")
    public void containsShouldFindNullValue(Map<String, Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put(String.valueOf(i), i);
	}
	map.put("ABC", null);
	final boolean result = map.containsValue(null);
	Assert.assertTrue(result);
    }
}
