package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.List;
import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class IndexOfTests {

    private static final int ZERO = 0;
    private int size;

    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getListDataProvider();
    }

    @BeforeTest
    public void setSize() {
	this.size = NumberGenerator.generate(500, 1500);
    }

    @Test(dataProvider = "getClass")
    public void indexOfShouldReturnCorrectIndex(List<Integer> list) {

	int counter = ZERO;
	for (int i = ZERO; i < this.size; i++) {
	    list.add(counter);

	    final int result = list.indexOf(counter);
	    Assert.assertEquals(result, i);
	    counter += NumberGenerator.generate(5, 100);
	}
    }

    @Test(dataProvider = "getClass")
    public void indexOfOnAnEmptyListShouldReturnCorrect(List<Integer> list) {
	final int result = list.indexOf(ZERO);
	Assert.assertEquals(result, -1);
    }

    @Test(dataProvider = "getClass")
    public void indexOfForANonExistingElementShouldReturnCorrentAnswer(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}

	final int result = list.indexOf(NumberGenerator.generate(1500, 30000));
	Assert.assertEquals(result, -1);
    }

    @Test(dataProvider = "getClass")
    public void addNullAndThenIndexOfOperationsShouldWork(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}
	list.add(2, null);
	list.add(0, null);
	list.add(10, null);
	list.add(15, null);
	list.add(this.size / 2, null);

	final Integer result = list.get(20);
	Assert.assertEquals(list.indexOf(result), 20);

    }

    @Test(dataProvider = "getClass")
    public void addNullInTheMiddleIndexOfShouldWork(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}
	final int middleIndex = this.size / 2;
	list.add(middleIndex, null);

	final int result = list.indexOf(null);
	Assert.assertEquals(result, middleIndex);

    }
    
    @Test(dataProvider = "getClass")
    public void addNullAndThenIndexOfOperationsShouldWorkWithString(List<String> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(String.valueOf(i));
	}
	list.add(2, null);
	list.add(0, null);
	list.add(10, null);
	list.add(15, null);
	list.add(this.size / 2, null);

	final String result = list.get(20);
	Assert.assertEquals(list.indexOf(result), 20);

    }

}
