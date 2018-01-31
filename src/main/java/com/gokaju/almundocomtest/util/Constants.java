package com.gokaju.almundocomtest.util;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public class Constants {

    private static long currentCallId;

    public static long getCurrentCallId() {
        return currentCallId;
    }

    public static long getNextCallId() {
        return ++currentCallId;
    }

}
