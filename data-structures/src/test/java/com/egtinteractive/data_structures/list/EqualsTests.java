package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.List;
import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class EqualsTests {

    private final static int ZERO = 0;
    private int size;

    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getDoubleListDataProvider();
    }

    @BeforeTest
    public void setSize() {
	this.size = NumberGenerator.generate(1000, 4000);

    }

    @Test(dataProvider = "getClass")
    public void equalsShouldReturnTrueIfTwoListsAreEmpty(List<String> list1, List<Integer> list2) {
	final boolean result = list1.equals(list2);
	Assert.assertTrue(result);
	final boolean result2 = list2.equals(list1);
	Assert.assertTrue(result2);

    }

    @Test(dataProvider = "getClass")
    public void equalsShouldReturnTrueIfTwoListsHaveTheSameValues(List<String> list1, List<String> list2) {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    list1.add(String.valueOf(num));
	    list2.add(String.valueOf(num));
	}

	final int index = NumberGenerator.generate(0, 200);
	list1.add(index, null);
	list2.add(index, null);

	final boolean result = list1.equals(list2);
	Assert.assertTrue(result);
	final boolean result2 = list2.equals(list1);
	Assert.assertTrue(result2);
    }

    @Test(dataProvider = "getClass")
    public void equalsShouldReturnFalseIfTwoListsDontHaveTheSameValues(List<String> list1, List<String> list2) {
	for (int i = ZERO; i < this.size; i++) {
	    final int num1 = NumberGenerator.generate(30000);
	    final int num2 = NumberGenerator.generate(30000);
	    list1.add(String.valueOf(num1));
	    list2.add(String.valueOf(num2));
	}

	final int index = NumberGenerator.generate(0, 200);
	list1.add(index, null);
	list1.add(index, null);

	final boolean result = list1.equals(list2);
	Assert.assertFalse(result);
	final boolean result2 = list2.equals(list1);
	Assert.assertFalse(result2);
    }

    @Test(dataProvider = "getClass")
    public void equalsShouldReturnFalseIfTwoListsHaveTheSameValuesWithJustOneDifference(List<String> list1,
	    List<String> list2) {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    list1.add(String.valueOf(num));
	    list2.add(String.valueOf(num));
	}
	list1.add(NumberGenerator.generate(500), "A");
	final boolean result = list1.equals(list2);
	Assert.assertFalse(result);
	final boolean result2 = list2.equals(list1);
	Assert.assertFalse(result2);
    }

    @Test(dataProvider = "getClass")
    public void equalsShouldReturnFalseForTreesWithDifferentDataTypes(List<String> list1, List<Integer> list2) {
	for (int i = ZERO; i < this.size; i++) {
	    final int num = NumberGenerator.generate(30000);
	    list1.add(String.valueOf(num));
	    list2.add(num);
	}

	final int index = NumberGenerator.generate(0, 200);
	list1.add(index, null);
	list2.add(index, null);

	final boolean result = list1.equals(list2);
	Assert.assertFalse(result);
	final boolean result2 = list2.equals(list1);
	Assert.assertFalse(result2);
    }

}
