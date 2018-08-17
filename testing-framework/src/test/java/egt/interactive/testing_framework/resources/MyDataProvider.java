package egt.interactive.testing_framework.resources;

import egt.interactive.testing_framework.MethodsClaimer;
import egt.interactive.testing_framework.Test.MyAnnotations;
import egt.interactive.testing_framework.Test.TestNG;
import egt.interactive.testing_framework.io.IO;

public class MyDataProvider {

    public static Object[][] getMethodsClaimerCustomAnnotations(final IO io) {
	return new Object[][] { { new MethodsClaimer(io, new MyAnnotations()) } };
    }

    public static Object[][] getMethodsClaimerTestng(final IO io) {
	return new Object[][] { { new MethodsClaimer(io, new TestNG()) } };
    }

    public static Object[][] getMethodsClaimer() {
	return new Object[][] { { new MethodsClaimer(new TestNG()) } };
    }
}
