package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class AddTests {

    private final static int ZERO = 0;
    private final static int ONE = 1;
    private int size;

    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getListDataProvider();
    }

    @BeforeTest
    public void setSize() {
	this.size = NumberGenerator.generate(1000, 4000);
    }

    @Test(dataProvider = "getClass")
    public void addShouldAddElementAtTheEnd(List<Integer> list) {
	final Integer[] arr = new Integer[size];
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    list.add(num);
	    arr[i] = num;
	}
	for (int i = ZERO; i < size; i++) {
	    Assert.assertEquals(arr[i], list.get(i));
	}
    }

    @Test(dataProvider = "getClass")
    public void addShouldIncreaseSize(List<Integer> list) {
	for (int i = ONE; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    list.add(num);
	    Assert.assertEquals(list.size(), i);
	}

    }

    @Test(dataProvider = "getClass")
    public void addAtSpecificIndexShouldAddCorrectly(List<Integer> list) {
	final java.util.List<Integer> arrayList = new java.util.ArrayList<>();

	final int num1 = NumberGenerator.generate(1000);

	list.add(ZERO, num1);
	arrayList.add(ZERO, num1);

	for (int i = ONE; i < size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    final int index = NumberGenerator.generate(i);
	    list.add(index, num);
	    arrayList.add(index, num);
	}

	for (int i = ZERO; i < size; i++) {
	    Assert.assertEquals(list.get(i), arrayList.get(i));
	}
    }

    @Test(dataProvider = "getClass")
    public void addNullAndThenContainsOperationShouldWork(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}
	list.add(2, null);
	list.add(0, null);
	list.add(10, null);
	list.add(15, null);
	list.add(this.size / 2, null);

	Assert.assertTrue(list.contains(this.size - 1));

    }

    @Test(dataProvider = "getClass")
    public void addAtSpecificIndexShouldAddCorrectlyWithStrings(List<String> list) {
	final java.util.List<String> arrayList = new java.util.ArrayList<>();

	final int num1 = NumberGenerator.generate(1000);

	list.add(ZERO, String.valueOf(num1));
	arrayList.add(ZERO, String.valueOf(num1));

	for (int i = ONE; i < size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    final int index = NumberGenerator.generate(i);
	    list.add(index, String.valueOf(num));
	    arrayList.add(index, String.valueOf(num));
	}

	for (int i = ZERO; i < size; i++) {
	    Assert.assertEquals(list.get(i), arrayList.get(i));
	}
    }

    @Test(dataProvider = "getClass")
    public void addNullAndThenRemoveOperationShouldWork(List<String> list) {
	list.add("a");
	list.add("e");
	list.add("b");
	list.add("c");
	final boolean res = list.remove("c");
	Assert.assertTrue(res);
    }

}
