package com.egtinteractive.data_structures.map;

import java.util.Iterator;
import java.util.Objects;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class IteratorTests {

    private static final int ONE = 1;
    private int size;

    @DataProvider(name = "getClassStrategy")
    public Object[][] getData() {
	return MyDataProvider.getMapDataProvider();
    }

    @BeforeClass
    public void setSize() {
	this.size = NumberGenerator.generate(150, 300);
    }

    @Test(dataProvider = "getClassStrategy")
    public void iteratorOnAnEmptyMapShouldDoNothing(Map<String, Integer> map) {

	StringBuilder sb = new StringBuilder();
	for (MyMap.Entry<String, Integer> entry : map) {
	    sb.append(entry.getKey()).append(entry.getValue()).append(System.lineSeparator());
	}
	Assert.assertEquals(sb.toString(), "");
    }

    @Test(dataProvider = "getClassStrategy")
    public void iteratorOnAFullMapShouldReturnAllAndCorrectResult(Map<String, Integer> map) {
	java.util.Map<String, Integer> jMap = new java.util.HashMap<>();

	for (int i = 0; i < this.size; i++) {
	    map.put(String.valueOf(i), i);
	    jMap.put(String.valueOf(i), i);
	}

	int counter = 0;

	for (MyMap.Entry<String, Integer> entry : map) {
	    String key = entry.getKey();
	    Integer value = entry.getValue();
	    Assert.assertTrue(jMap.containsKey(key));
	    Assert.assertTrue(jMap.containsValue(value));
	    counter++;
	}
	Assert.assertEquals(counter, this.size);
    }

    @Test(dataProvider = "getClassStrategy")
    public void removeOnAFullMapShouldReturnAllAndCorrectResult(Map<String, Integer> map) {
	for (int i = 0; i < this.size; i++) {
	    map.put(String.valueOf(i), i);

	}
	Iterator<MyMap.Entry<String, Integer>> myIterator = map.iterator();
	int pos = map.size();
	while (pos-- > 0) {
	    myIterator.next();
	    myIterator.remove();
	    Assert.assertEquals(map.size(), pos);
	}

    }

    @Test(dataProvider = "getClassStrategy")
    public void iteratorOnAFullMapShouldReturnAllAndInOrder(Map<String, Integer> map) {
	java.util.List<String> list = new java.util.ArrayList<>();

	for (int i = 0; i < 10; i++) {
	    map.put(String.valueOf(i), i);
	    list.add(String.valueOf(i));
	}
	int counter = 0;
	final Iterator<MyMap.Entry<String, Integer>> mapItr = map.iterator();
	final Iterator<String> listItr = list.iterator();
	int pos = map.size();
	while (pos-- > 0) {
	    Assert.assertTrue(Objects.equals(mapItr.next().getKey(), listItr.next()));
	    counter++;
	}
	Assert.assertEquals(counter, 10);
    }

    @Test(dataProvider = "getClassStrategy")
    public void iteratorOnAFullMapShouldReturnAndRemoveAllAndInOrder(Map<String, Integer> map) {
	java.util.List<String> list = new java.util.ArrayList<>();

	for (int i = 0; i < 10; i++) {
	    map.put(String.valueOf(i), i);
	    list.add(String.valueOf(i));
	}

	final Iterator<MyMap.Entry<String, Integer>> mapItr = map.iterator();
	final Iterator<String> listItr = list.iterator();
	int pos = map.size();
	while (pos-- > 0) {
	    Assert.assertTrue(Objects.equals(mapItr.next().getKey(), listItr.next()));
	    mapItr.remove();
	    listItr.remove();

	}
	
	for (int i = 0; i < 10; i++) {
	    Assert.assertFalse(map.containsKey(String.valueOf(i)));
	
	}
	
	Assert.assertEquals(map.size(), 0);
    }
}
