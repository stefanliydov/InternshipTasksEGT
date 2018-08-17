package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class SizeTests {

    private static final int ONE = 1;
    private static final int ZERO = 0;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData() {
	return MyDataProvider.getMapDataProvider();
    }

    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(70, 250);
    }
    @Test(dataProvider = "getClassStrategy")
    public void sizeShouldReturnCorrectSizeWithMultipleElements(Map<Character,Integer> map) {
	for (int i = ONE; i <= this.size; i++) {
	    map.put((char) i, i);
	}
	final int result = map.size();
	Assert.assertEquals(result, this.size);
    }

    @Test(dataProvider = "getClassStrategy")
    public void sizeShouldReturnZeroOnAnEmptyMap(Map<Character,Integer> map) {
	final int result = map.size();
	Assert.assertEquals(result, ZERO);
    }

    @Test(dataProvider = "getClassStrategy")
    public void sizeShouldReturnCorrectSizeWithOneButHeavilyChainedElement(Map<Character,Integer> map) {
	final int newSize = this.size * this.size;
	int counter = ZERO;
	for (int i = ONE; i < newSize; i += 16) {
	    map.put((char) i, i);
	    counter++;
	}
	final int result = map.size();
	Assert.assertEquals(result, counter);
    }

}
