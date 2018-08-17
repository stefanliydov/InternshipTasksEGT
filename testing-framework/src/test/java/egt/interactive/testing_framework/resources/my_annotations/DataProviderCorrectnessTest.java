package egt.interactive.testing_framework.resources.my_annotations;

import org.testng.Assert;

import egt.interactive.testing_framework.annotations.DataProvider;
import egt.interactive.testing_framework.annotations.Test;

public class DataProviderCorrectnessTest {

    @DataProvider(name = "geInt")
    public Object[][] getInteger() {
	return new Object[][] { { new Integer(5) } };
    }

    @Test(dataProvider = "geInt")
    public void testProvider(final Integer integer) {
	Assert.assertEquals(integer, Integer.valueOf(5));
    }
}
