package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.List;
import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class SetTests {

    private static final int ZERO = 0;
    private int size;

    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getListDataProvider();
    }

    @BeforeTest
    public void setSize() {
	this.size = NumberGenerator.generate(2500, 8000);
    }

    @Test(dataProvider = "getClass")
    public void setShouldOverwriteCorrectly(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}

	for (int i = ZERO; i < this.size; i++) {
	    final int index = NumberGenerator.generate(this.size);
	    final int num = NumberGenerator.generate(size);
	    list.set(index, num);

	    final int result = list.get(index);
	    Assert.assertEquals(result, num);
	}
    }

    @Test(dataProvider = "getClass")
    public void addNullAndThenSetOperationsShouldWork(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}
	list.add(2, null);
	list.add(0, null);
	list.add(10, null);
	list.add(15, null);
	list.add(this.size / 2, null);

	list.set(10, 30);
    }

}
