package egt.interactive.testing_framework.Test;

import static egt.interactive.testing_framework.enums.TestingAnnotations.*;

public class MyAnnotations extends Test {

    public MyAnnotations() {
	super(MY_ANNOTATIONS.getTestAnnotation(), MY_ANNOTATIONS.getBeforeTestAnnotation(),
		MY_ANNOTATIONS.getAfterAnnotation(), MY_ANNOTATIONS.getDataProviderAnnotation());

    }

}
