package com.message.security;

import java.util.Random;

/**
 * The {@code Utility} class contains several methods and a couple of class fields. It cannot be instantiated.
 * This class helps the {@link Encrypt} and {@link Decrypt} class of {@link com.message.security} class. All
 * the core encrypting and decrypting operation methods are defined in this class. Set the keypair using
 * {@link Utility#setKeyPair(int, int)} before using this class and do not forget to call
 * {@link Utility#resetKeyPair()} after finishing.
 * @see Encrypt
 * @see Decrypt
 * @author tinykishore
 */

public final class Utility {
    // Keypair variables
    protected static int k1;
    protected static int k2;

    /**
     * Internal Constructor, do not instantiate Utility class.
     * @exception IllegalStateException if instantiated.
     */
    private Utility() {
        throw new IllegalStateException();
    }

    /**
     * This method is for ENCRYPTION<br>
     * This method changes the ASCII value of each characters of a String. It adds Integer {@code k1} to
     * each character.
     * @param data String as Byte Array ({@code byte[]})
     * @return Modified String
     * @see Encrypt#encrypt(String)
     */
    protected static byte[] keyOneEncrypt(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] + k1);
        }
        return data;
    }

    /**
     * This method is for DECRYPTION<br>
     * This method changes the ASCII value of each characters of a String. It subtracts Integer {@code k1} to
     * each character.
     * @param data String as Byte Array ({@code byte[]})
     * @return Modified String
     * @see Decrypt#decrypt(String)
     */
    protected static byte[] keyOneDecrypt(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] - k1);
        }
        return data;
    }

    /**
     * This method is for ENCRYPTION<br>
     * It changes the ASCII value of each characters of a String. It subtracts Integer {@code k2} to
     * the last half part character of the String.
     * @param data String as Byte Array ({@code byte[]})
     * @return Modified String
     * @see Encrypt#encrypt(String)
     */
    protected static byte[] keyTwoEncrypt(byte[] data) {
        for (int i = (data.length) / 2; i < data.length; i++) {
            data[i] = (byte) (data[i] - k2);
        }
        return data;
    }

    /**
     * This method is for DECRYPTION<br>
     * It changes the ASCII value of each characters of a String. It adds Integer {@code k2} to
     * the last half part character of the String.
     * @param data String as Byte Array ({@code byte[]})
     * @return Modified String
     * @see Decrypt#decrypt(String)
     */
    protected static byte[] keyTwoDecrypt(byte[] data) {
        for (int i = (data.length) / 2; i < data.length; i++) {
            data[i] = (byte) (data[i] + k2);
        }
        return data;
    }

    /**
     * This method reverse a String and returns that String.
     * @param data String as Byte Array ({@code byte[]})
     * @return Modified String
     * @see Encrypt#encrypt(String)
     * @see Decrypt#decrypt(String)
     */
    protected static byte[] reverseBytes(byte[] data) {
        byte[] reversed = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            reversed[i] = data[data.length - 1 - i];
        }
        return reversed;
    }

    /**
     * This is a instantiation of {@code Random} class, with seed {@code System.currentTimeMillis()}.
     * It helps generate random numbers according to current system date and time.
     *
     * @see Utility#generateRandomKeyOne()
     * @see Utility#generateRandomKeyTwo()
     */
    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * This method generates random number within a given bound.
     * <pre>
     *     lowerBound < N < upperBound
     * </pre>
     * Where, N is the random number.<br>
     * @apiNote It is deprecated because a bug was found in {@link Encrypt#encrypt(String)} followed by
     * private method {@code encryptSystem} and {@link Decrypt#decrypt(String)} followed by private
     * method {@code decryptSystem} that this program works only within a certain range value of keypair,
     * and both of the keys have different range. So an unified random value generator  method will result
     * failure in decrypting a String correctly. This bug occurs because of the limitation of ASCII range (0-127).
     * @param lowerBound Lowest accepted value
     * @param upperBound Highest accepted value
     * @return A Random Integer
     */
    @Deprecated
    protected static int generateRandomFactor(int lowerBound, int upperBound){
        while (true){
            int factor = random.nextInt(upperBound);
            if(factor > lowerBound) return factor;
        }
    }

    /**
     * Random Key-1 generator method, which generates a random integer within a fixed range.
     * @return Random Integer
     */
    protected static int generateRandomKeyOne(){
        while (true){
            int key = random.nextInt(6);
            if(key != 0) return key;
        }
    }

    /**
     * Random Key-2 generator method, which generates a random integer within a fixed range.
     * @return Random Integer
     */
    protected static int generateRandomKeyTwo(){
        while (true){
            int key = random.nextInt(20);
            if(key != 0) return key;
        }
    }

    /**
     * Resets the Keypair of this class. (Sets both of the keys to 0)
     */
    protected static void resetKeyPair(){
        k1 = 0;
        k2 = 0;
    }

    /**
     * Sets the Keypair to the given values.
     * @param keyA Key-1 Value
     * @param keyB Key-2 Value
     */
    protected static void setKeyPair(int keyA, int keyB){
        k1 = keyA;
        k2 = keyB;
    }

}
