package com.egtinteractive.data_structures.map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class GetTests {
    private static final int ONE = 1;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData(){
	return MyDataProvider.getMapDataProvider();
    }
    
    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(100, 300);
    }

    @Test(dataProvider = "getClassStrategy")
    public void getShouldReturnCorrectValueForMultipleChainderElements(Map<Character,Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put((char) i, i);
	}
	for (int i = ONE; i < this.size; i++) {
	    final int result = map.get((char) i);
	    Assert.assertEquals(result, i);
	}

    }

    @Test(dataProvider = "getClassStrategy")
    public void getShouldReturnNullOnAnEmptyMap(Map<Character,Integer> map) {
	Assert.assertNull(map.get((char)NumberGenerator.generate(30)));
    }

    @Test(dataProvider = "getClassStrategy")
    public void getShouldReturnNullIfElementIsNotPresent(Map<Character,Integer> map) {
	for (int i = ONE; i < this.size; i++) {
	    map.put((char) i, i);
	}
	
	for(int i = this.size +ONE; i<this.size*2;i++){
	Assert.assertNull(map.get((char)i));
	}
    }

}
