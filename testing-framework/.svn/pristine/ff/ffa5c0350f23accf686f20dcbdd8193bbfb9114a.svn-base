package egt.interactive.testing_framework.resources.my_annotations;

import org.testng.Assert;

import egt.interactive.testing_framework.annotations.BeforeTest;
import egt.interactive.testing_framework.annotations.Test;

public class BeforeTestExecutionTest {

    private int num;

    @BeforeTest
    private void changeNumValue() {
	num = 5;
    }

    @Test
    void numShouldBeFive() {
	Assert.assertEquals(num, 5);
    }

}
