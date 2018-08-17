package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class EqualsTests {

    private static final int ONE = 1;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData(){
	return MyDataProvider.getDoubleMapDataProvider();
    }
    
    
    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(150, 700);
    }


    @Test(dataProvider = "getClassStrategy")
    public void equalsOnEmptyMapsShouldReturnTrue(Map<String,Integer> map1, Map<String,Integer> map2) {
	Assert.assertTrue(map1.equals(map2));
	Assert.assertTrue(map2.equals(map1));
    }
    
    @Test(dataProvider = "getClassStrategy")
    public void equalsOnMapsWithSameContentShouldReturnTrue(Map<String,Integer> map1, Map<String,Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    map1.put(String.valueOf(i), i);
	    map2.put(String.valueOf(i), i);
	}
	
	final boolean result1 = map1.equals(map2);
	final boolean result2 = map2.equals(map1);
	Assert.assertTrue(result1);
	Assert.assertTrue(result2);
	
    }
    
    @Test(dataProvider = "getClassStrategy")
    public void equalsOnMapsWithDifferentValuesShouldReturnFalse(Map<String,Integer> map1, Map<String,Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    final int num1 = NumberGenerator.generate(30000);
	    final int num2 = NumberGenerator.generate(30000);
	    map1.put(String.valueOf(i), num1);
	    map2.put(String.valueOf(i), num2);
	}
	
	map1.put("ABC", null);
	map2.put("ABC", null);
	
	final boolean result1 = map1.equals(map2);
	final boolean result2 = map2.equals(map1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);

    }
    
    @Test(dataProvider = "getClassStrategy")
    public void equalsOnMapsWithDifferentKeysShouldReturnFalse(Map<String,Integer> map1, Map<String,Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    final int num1 = NumberGenerator.generate(30000);
	    final int num2 = NumberGenerator.generate(30000);
	    map1.put(String.valueOf(num1), i);
	    map2.put(String.valueOf(num2), i);
	}
	
	map1.put("ABC", null);
	map2.put("ABC", null);
	
	
	final boolean result1 = map1.equals(map2);
	final boolean result2 = map2.equals(map1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);

    }
    @Test(dataProvider = "getClassStrategy")
    public void equalsOnMapsWithSlightDIfferenceInContentShouldRetunFalse(Map<String,Integer> map1, Map<String,Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	    map1.put(String.valueOf(i), i);
	    map2.put(String.valueOf(i), i);
	}
	map1.put("ABC",3123123);
	
	final boolean result1 = map1.equals(map2);
	final boolean result2 = map2.equals(map1);
	Assert.assertFalse(result1);
	Assert.assertFalse(result2);

    }
    
    @Test(dataProvider = "getClassStrategy")
    public void equalsOnMapsWithSameKeysAndAllNullValuesShouldReturnTrue(Map<String,Integer> map1, Map<String,Integer> map2) {
	for (int i = ONE; i < this.size; i++) {
	   
	    map1.put(String.valueOf(i), null);
	    map2.put(String.valueOf(i), null);
	}
	
	final boolean result1 = map1.equals(map2);
	final boolean result2 = map2.equals(map1);
	Assert.assertTrue(result1);
	Assert.assertTrue(result2);

    }
}
