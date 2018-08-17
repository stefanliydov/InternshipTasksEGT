package egt.interactive.testing_framework.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPackage {
    private final List<TestMethod> testMethods;
    private final List<Method> beforeTestMethods;
    private final List<Method> afterTestMethods;
    private final Map<String, Method> dataProviders;

    public TestPackage() {
	this.testMethods = new ArrayList<>();
	this.beforeTestMethods = new ArrayList<>();
	this.afterTestMethods = new ArrayList<>();
	this.dataProviders = new HashMap<>();
    }

    public void addTestMethod(final TestMethod method) {
	this.testMethods.add(method);
    }

    public void addBeforeTestMethod(final Method method) {
	this.beforeTestMethods.add(method);
    }

    public void addAfterTestMethod(final Method method) {
	this.afterTestMethods.add(method);
    }

    public void addDataProviderMethod(final String name, final Method method) {
	this.dataProviders.put(name, method);
    }

    public List<TestMethod> getTestMethods() {
	return Collections.unmodifiableList(testMethods);
    }

    public List<Method> getBeforeTestMethods() {
	return Collections.unmodifiableList(beforeTestMethods);
    }

    public List<Method> getAfterTestMethods() {
	return Collections.unmodifiableList(afterTestMethods);
    }

    public Map<String, Method> getDataProviders() {
	return Collections.unmodifiableMap(dataProviders);
    }

}
