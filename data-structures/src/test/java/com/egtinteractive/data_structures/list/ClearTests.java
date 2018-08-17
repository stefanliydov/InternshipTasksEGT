package com.egtinteractive.data_structures.list;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.list.List;
import com.egtinteractive.data_structures.resources.MyDataProvider;
import com.egtinteractive.data_structures.resources.NumberGenerator;

public class ClearTests {

    private static final int ZERO = 0;
    private int size;

    @DataProvider(name = "getClass")
    public Object[][] getData() {
	return MyDataProvider.getListDataProvider();
    }

    @BeforeTest
    public void setSize() {
	this.size = NumberGenerator.generate(50, 150);
    }

    @Test(dataProvider = "getClass")
    public void clearShouldResetSize(List<Integer> list) {
	for (int i = ZERO; i < this.size; i++) {
	    list.add(i);
	}

	list.clear();

	final int result = list.size();

	Assert.assertEquals(result, ZERO);
    }

}
