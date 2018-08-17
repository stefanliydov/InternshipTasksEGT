package egt.interactive.testing_framework.resources.test_ng;

import org.testng.annotations.Test;

public class ExpectedExceptionTests {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldPassIfRightMistakeIsThrown() {
	throw new IllegalArgumentException();
    }
}
