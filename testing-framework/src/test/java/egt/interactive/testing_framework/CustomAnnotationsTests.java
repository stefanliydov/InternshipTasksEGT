package egt.interactive.testing_framework;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.testing_framework.resources.MyDataProvider;
import egt.interactive.testing_framework.resources.TestingIO;
import egt.interactive.testing_framework.resources.my_annotations.BeforeTestExecutionTest;
import egt.interactive.testing_framework.resources.my_annotations.CorrectnessTest;
import egt.interactive.testing_framework.resources.my_annotations.DataProviderCorrectnessTest;
import egt.interactive.testing_framework.resources.my_annotations.ExpectedExceptionNotFoundTest;
import egt.interactive.testing_framework.resources.my_annotations.ExpectedExceptionTests;
import egt.interactive.testing_framework.resources.my_annotations.IncorrectTest;
import egt.interactive.testing_framework.resources.my_annotations.MultipleAssertsTests;
import egt.interactive.testing_framework.resources.my_annotations.UnexpectedExceptionTest;

public class CustomAnnotationsTests {

    private final TestingIO io = new TestingIO();

    @DataProvider(name = "claimer")
    public Object[][] getClaimer() {
	return MyDataProvider.getMethodsClaimerCustomAnnotations(io);
    }

    @Test(dataProvider = "claimer")
    public void correctTestShouldPass(final MethodsClaimer claimer) {
	claimer.loadClasses(CorrectnessTest.class.getName());
	claimer.run();
	assertTrue(io.map.get("testThatPasses"));

    }

    @Test(dataProvider = "claimer")
    public void dataProvidersShouldPassParameters(final MethodsClaimer claimer) {
	claimer.loadClasses(DataProviderCorrectnessTest.class.getName());
	claimer.run();
	assertTrue(io.map.get("testProvider"));

    }

    @Test(dataProvider = "claimer")
    public void incorrectTestShouldntPass(final MethodsClaimer claimer) {
	claimer.loadClasses(IncorrectTest.class.getName());
	claimer.run();
	assertFalse(io.map.get("incorrectTest"));

    }

    @Test(dataProvider = "claimer")
    public void shouldCatchCorrectErrorThrown(final MethodsClaimer claimer) {
	claimer.loadClasses(ExpectedExceptionTests.class.getName());
	claimer.run();
	assertTrue(io.map.get("shouldPassIfRightMistakeIsThrown"));

    }

    @Test(dataProvider = "claimer")
    public void shouldNotCatchWrongError(final MethodsClaimer claimer) {
	claimer.loadClasses(UnexpectedExceptionTest.class.getName());
	claimer.run();
	assertFalse(io.map.get("throwWrongException"));
    }

    @Test(dataProvider = "claimer")
    public void beforeTestShouldBeExecuted(final MethodsClaimer claimer) {
	claimer.loadClasses(BeforeTestExecutionTest.class.getName());
	claimer.run();
	assertTrue(io.map.get("numShouldBeFive"));
    }

    @Test(dataProvider = "claimer")
    public void shouldGiveWrongAnswerIfNoErrorIsThrown(final MethodsClaimer claimer) {
	claimer.loadClasses(ExpectedExceptionNotFoundTest.class.getName());
	claimer.run();
	assertFalse(io.map.get("shouldGiveWrongAnswerIfNoErrorIsThrown"));

    }

    @Test(dataProvider = "claimer")
    public void multipleAssertsWithOneBeingWrongShouldReturnFailed(final MethodsClaimer claimer) {
	claimer.loadClasses(MultipleAssertsTests.class.getName());
	claimer.run();
	assertFalse(io.map.get("multipleAssertThenOneWrongShouldGiveFalse"));
    }

    @Test(dataProvider = "claimer")
    public void multipleClassesLoadedShouldHaveTestsRunCorrectly(final MethodsClaimer claimer) {
	io.map = new HashMap<>();
	List<String> classes = new ArrayList<>();
	classes.add(MultipleAssertsTests.class.getName());
	classes.add(ExpectedExceptionNotFoundTest.class.getName());
	classes.add(BeforeTestExecutionTest.class.getName());
	classes.add(CorrectnessTest.class.getName());
	classes.add(ExpectedExceptionTests.class.getName());
	claimer.loadClasses(classes);
	claimer.run();
	assertFalse(io.map.get("multipleAssertThenOneWrongShouldGiveFalse"));
	assertFalse(io.map.get("multipleAssertThenOneWrongShouldGiveFalse"));
	assertFalse(io.map.get("shouldGiveWrongAnswerIfNoErrorIsThrown"));
	assertTrue(io.map.get("numShouldBeFive"));
	assertTrue(io.map.get("shouldPassIfRightMistakeIsThrown"));
	io.map = new HashMap<>();

    }

}
