package org.jaxrs.rocompany.util;

public class Logger {

    public static final void into(final String message) {
        System.out.println("INFO: " + message);
    }

    public static final void error(final String message) {
        System.out.println("ERROR: " + message);
    }
}
