package egt.interactive.testing_framework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import egt.interactive.testing_framework.resources.MyDataProvider;
import egt.interactive.testing_framework.resources.test_ng.InvalidTest;

public class ExceptionTests {

    @DataProvider(name = "claimer")
    public Object[][] getClaimer() {
	return MyDataProvider.getMethodsClaimer();
    }

    @Test(dataProvider = "claimer", expectedExceptions = RuntimeException.class)
    public void runWithoutFirstLoadingClassesShouldThrowException(final MethodsClaimer claimer) {
	claimer.run();
    }

    @Test(dataProvider = "claimer", expectedExceptions = RuntimeException.class)
    public void runMethodsWithMoreThanOneSpecialAnnotationShouldThrowException(final MethodsClaimer claimer) {
	claimer.loadClasses(InvalidTest.class.getName());
	claimer.run();
    }

}
