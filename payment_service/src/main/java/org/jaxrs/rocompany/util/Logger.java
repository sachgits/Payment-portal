package org.jaxrs.rocompany.util;

public class Logger {

    public static final void info(final String message) {
        System.out.println("INFO: " + message);
    }

    public static final void error(final String message) {
        System.err.println("ERROR: " + message);
    }

    public static final void error(final String message, final Throwable th) {
        System.err.println("ERROR: " + message);
        th.printStackTrace();
    }
}
