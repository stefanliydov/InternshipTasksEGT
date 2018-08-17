package egt.interactive.testing_framework.resources.test_ng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExpectedExceptionNotFoundTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldGiveWrongAnswerIfNoErrorIsThrown() {
	Assert.assertEquals("1", "1");
    }
}
