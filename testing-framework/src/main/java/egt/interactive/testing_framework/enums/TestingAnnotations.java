package egt.interactive.testing_framework.enums;

import java.lang.annotation.Annotation;

import egt.interactive.testing_framework.annotations.AfterTest;
import egt.interactive.testing_framework.annotations.BeforeTest;
import egt.interactive.testing_framework.annotations.DataProvider;
import egt.interactive.testing_framework.annotations.Test;

public enum TestingAnnotations {

    TEST_NG {
	@Override
	public Class<? extends Annotation> getTestAnnotation() {
	    return org.testng.annotations.Test.class;
	}

	@Override
	public Class<? extends Annotation> getDataProviderAnnotation() {
	    return org.testng.annotations.DataProvider.class;
	}

	@Override
	public Class<? extends Annotation> getBeforeTestAnnotation() {
	    return org.testng.annotations.BeforeTest.class;

	}

	@Override
	public Class<? extends Annotation> getAfterAnnotation() {
	    return org.testng.annotations.AfterTest.class;

	}
    },
    MY_ANNOTATIONS {
	@Override
	public Class<? extends Annotation> getTestAnnotation() {
	    return Test.class;
	}

	@Override
	public Class<? extends Annotation> getDataProviderAnnotation() {
	    return DataProvider.class;
	}

	@Override
	public Class<? extends Annotation> getBeforeTestAnnotation() {
	    return BeforeTest.class;
	}

	@Override
	public Class<? extends Annotation> getAfterAnnotation() {
	    return AfterTest.class;
	}
    };

    public abstract Class<? extends Annotation> getTestAnnotation();

    public abstract Class<? extends Annotation> getDataProviderAnnotation();

    public abstract Class<? extends Annotation> getBeforeTestAnnotation();

    public abstract Class<? extends Annotation> getAfterAnnotation();

}
