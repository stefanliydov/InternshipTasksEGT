package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class ClearTests {

    private static final int ONE = 1;
    private static final int ZERO = 0;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData(){
	return MyDataProvider.getMapDataProvider();
    }
    
    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(70, 250);
    }

    @Test(dataProvider = "getClassStrategy")
    public void clearShouldClearAllElements(Map<Integer,Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put(i, i);
	}

	map.clear();

	for (int i = ONE; i < this.size; i++) {

	    final boolean keyResult = map.containsKey(i);
	    final boolean valueResult = map.containsValue(i);
	    Assert.assertFalse(keyResult);
	    Assert.assertFalse(valueResult);
	}

    }

    @Test(dataProvider = "getClassStrategy")
    public void clearShouldResetSize(Map<Integer,Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put(i, i);
	}

	map.clear();

	final int result = map.size();

	Assert.assertEquals(ZERO, result);
    }

    @Test(dataProvider = "getClassStrategy")
    public void clearOnAnEmptyMapShouldDoNothing(Map<Integer,Integer> map) {
	map.clear();

	final int result = map.size();

	Assert.assertEquals(ZERO, result);
    }
}
