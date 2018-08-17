package egt.interactive.testing_framework.exceptions;

public class TestingError extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 5065145335983287465L;

    public TestingError() {
	super();
    }

    public TestingError(final String message) {
	super(message);
    }

    public TestingError(final String message, final Throwable cause) {
	super(message, cause);
    }

    public TestingError(final Throwable cause) {
	super(cause);
    }
}
