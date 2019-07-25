package com.github.zuihou.jobs.utils;

/**
 * 断言
 *
 * @author zuihou
 * @date 2019/07/05
 */
public class Assert {


    /**
     * Asserts that an object isn't null. If it is an {@link AssertionError} is
     * thrown with the given message.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     *                okay)
     * @param object  Object to check or <code>null</code>
     */
    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }


    /**
     * Asserts that a condition is true. If it isn't it throws an
     * {@link AssertionError} with the given message.
     *
     * @param message   the identifying message for the {@link AssertionError} (<code>null</code>
     *                  okay)
     * @param condition condition to be checked
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }


    /**
     * Fails a test with the given message.
     *
     * @param message the identifying message for the {@link AssertionError} (<code>null</code>
     *                okay)
     * @see AssertionError
     */
    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }

}
