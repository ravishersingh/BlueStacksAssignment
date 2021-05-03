package com;

final public class params {
    final public static String osName = System.getProperty("os.name");
    /**Seconds*/
    final public static int maxFinderTimeOut = 5;
    /**Milliseconds*/
    final public static long driverImplicitWait = 500;
    /**Milliseconds*/
    final public static long pageLoadTimeout = 10000;
    final public static int pageLoadTimeoutSeconds = 30;

    public static final String projectDir = System.getProperty("user.dir");
}
