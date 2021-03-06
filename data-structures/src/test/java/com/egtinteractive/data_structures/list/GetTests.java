package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.List;
import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class GetTests {

    private static final int ZERO = 0;
    
    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getListDataProvider();
    }

    @Test(dataProvider = "getClass")
    public void getShouldReturnCorrectElement(List<Integer> list) {
	final int num1 = NumberGenerator.generate(10000);
	final int num2 = NumberGenerator.generate(10000);
	final int num3 = NumberGenerator.generate(10000);

	list.add(num1);
	list.add(num2);
	list.add(num3);

	final int result = list.get(1);
	Assert.assertEquals(result, num2);
    }

    @Test(dataProvider = "getClass")
    public void getShouldWorkCorrectlyWithAlotOfElements(List<Integer> list) {
	final int size = NumberGenerator.generate(700, 1000);
	final Integer[] arr = new Integer[size];

	for (int i = ZERO; i < size; i++) {
	    final int num = NumberGenerator.generate(10000);
	    list.add(num);
	    arr[i] = num;
	}

	for (int i = ZERO; i < size; i++) {
	    Assert.assertEquals(arr[i], list.get(i));
	}

    }
    
    @Test(dataProvider = "getClass")
    public void addNullInTheMiddleGetShouldWork(List<Integer> list) {
	final int size = NumberGenerator.generate(50,200);
	for (int i = ZERO; i < size; i++) {
	    list.add(i);
	}
	final int middleIndex = size / 2;
	list.add(middleIndex, null);

	final Integer result = list.get(middleIndex);
	Assert.assertEquals(result, null);

    }

}
