package egt.interactive.testing_framework.resources.test_ng;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class BaseTest {

    protected int num;

    @BeforeTest
    public void changeNumValue() {
	num = 5;
    }

    @DataProvider(name = "getIntPrivate")
    private Object[][] getIntegerPrivate() {
	return new Object[][] { { new Integer(9) } };
    }

    @DataProvider(name = "getIntPackage")
    Object[][] getIntegerPackage() {
	return new Object[][] { { new Integer(10) } };
    }

    @DataProvider(name = "getIntProtected")
    protected Object[][] getIntegerProtected() {
	return new Object[][] { { new Integer(30) } };
    }

    @DataProvider(name = "getIntPublic")
    public Object[][] getInteger() {
	return new Object[][] { { new Integer(20) } };
    }
}
