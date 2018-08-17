package egt.interactive.testing_framework.resources.my_annotations;

import egt.interactive.testing_framework.annotations.Test;

public class UnexpectedExceptionTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void throwWrongException() {
	throw new NullPointerException();
    }
}
