package egt.interactive.testing_framework.resources.test_ng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IncorrectTest {

    @Test
     void incorrectTest() {
	Assert.assertEquals(1, 2);
    }

}
