package egt.interactive.testing_framework.resources.my_annotations;

import org.testng.Assert;

import egt.interactive.testing_framework.annotations.Test;

public class ExpectedExceptionNotFoundTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldGiveWrongAnswerIfNoErrorIsThrown() {
	Assert.assertEquals("1", "1");
    }
}
