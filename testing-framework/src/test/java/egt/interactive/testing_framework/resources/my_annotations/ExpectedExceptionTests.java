package egt.interactive.testing_framework.resources.my_annotations;

import egt.interactive.testing_framework.annotations.Test;

public class ExpectedExceptionTests {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldPassIfRightMistakeIsThrown() {
	throw new IllegalArgumentException();
    }

    
}
