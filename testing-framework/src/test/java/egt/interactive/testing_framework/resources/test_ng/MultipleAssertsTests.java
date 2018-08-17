package egt.interactive.testing_framework.resources.test_ng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MultipleAssertsTests {

    @Test
    void multipleAssertThenOneWrongShouldGiveFalse() {
	Assert.assertEquals("2", "2");
	Assert.assertEquals("4", "4");
	Assert.assertEquals("5", "5");
	Assert.assertEquals("2", "1");
	Assert.assertEquals("2", "2");
    }

}
