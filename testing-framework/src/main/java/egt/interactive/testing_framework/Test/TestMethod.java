package egt.interactive.testing_framework.Test;

import java.lang.reflect.Method;

public class TestMethod {

    private final Method method;
    private final String dataProvider;
    private final Class<? extends Throwable>[] expectedException;

    public TestMethod(final Method method, final String dataProvider,
	    final Class<? extends Throwable>[] expectedException) {
	super();
	this.method = method;
	this.dataProvider = dataProvider;
	this.expectedException = expectedException;
    }

    public Method getMethod() {
	return method;
    }

    public String getDataProvider() {
	return dataProvider;
    }

    public Class<? extends Throwable>[] getExpectedException() {
	return expectedException;
    }

}
