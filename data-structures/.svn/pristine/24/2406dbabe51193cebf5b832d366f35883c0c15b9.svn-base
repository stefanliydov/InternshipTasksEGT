package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class ContainsKeyTests {

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
    public void containsShouldReturnTrueWhenMappingExists(Map<Character, Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put((char) i, i);
	}
	for (int i = ONE; i < this.size; i++) {
	    final boolean result = map.containsKey((char) i);
	    Assert.assertTrue(result);
	}
    }

    @Test(dataProvider = "getClassStrategy")
    public void containsShouldReturnFalseIfMappingDoesNotExists(Map<Character, Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put((char) i, i);
	}

	final int secondSize = NumberGenerator.generate(300, 400);
	for (int i = this.size; i < secondSize; i++) {
	    final boolean result = map.containsKey((char) i);
	    Assert.assertFalse(result);
	}
    }

    @Test(dataProvider = "getClassStrategy")
    public void containsShouldReturnFalseInAnEmptyMap(Map<Character, Integer> map) {
	Assert.assertFalse(map.containsKey((char) NumberGenerator.generate(30)));
    }

}
