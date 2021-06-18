package com.message.security;

/**
 * The {@code KeyPairNumberOutOfBounds} class which is also a customised exception handler for
 * {@link Encrypt} and {@link Decrypt} class of {@link com.message.security} package.
 */

public class KeyPairNumberOutOfBounds extends Throwable {
    /**
     * Internal constructor, prints the error and error stack
     * @param message Error Handling Message
     */
    public KeyPairNumberOutOfBounds(String message) {
        super(message);
    }
}
