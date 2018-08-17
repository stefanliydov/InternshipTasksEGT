package egt.interactive.testing_framework.resources.my_annotations;

import org.testng.Assert;

import egt.interactive.testing_framework.annotations.Test;

public class CorrectnessTest {

    @Test
    public void testThatPasses() {
	Assert.assertTrue(true);
    }

}
