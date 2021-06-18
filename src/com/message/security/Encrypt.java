package com.message.security;

import java.nio.charset.StandardCharsets;

/**
 * The {@code Encrypt} class contains several useful class fields and methods. It can be instantiated
 * in two ways. The facility provided by the {@code Encrypt} class is encrypt a String
 * with a keypair. It uses another class {@code Utility} for basic encrypting operations.
 * All the class fields and methods are either {@code private} or {@code protected}
 * except the constructor and {@code encrypt()} method. Use the {@code Decrypt} class from
 * {@link com.message.security} package to decrypt.<br><br>
 *
 * The encryption system uses 2 types of keypair (also called, factors)- Default keypair and
 * User-defined keypair. Default keypair is set randomly by system and User-defined keypair is set by
 * the user and remain constant. If an object is instantiated like this :
 * <pre>
 *     Encrypt e = new Encrypt();
 * </pre>
 * Then this object will encrypt with default keypair which changes randomly every time the program
 * starts. And if, an object is instantiated like this:
 * <pre>
 *     Encrypt e = new Encrypt(key1, key2);
 * </pre>
 * Where {@code key1} and {@code key2} are bounded integers, then that object will encrypt with user defined keypair
 * and the keys are {key1, key2}.
 *
 * @see Utility
 * @see Decrypt
 * @author tinykishore
 */

public final class Encrypt {

    /**
     * This {@code State} enum stores two constants.
     * <pre>
     *     DEFAULT
     *     USER_DEFINED
     * </pre>
     * This two keywords determine what type of encryption it will be.
     */
    private enum State{
        DEFAULT, USER_DEFINED
    }

    // Current State of the object, Which is either DEFAULT or USER_DEFINED
    private final State currentState;

    // Current Default Keypair of object
    private final int defaultKeyOne;
    private final int defaultKeyTwo;

    /**
     * This getter-method returns the object's Default Key-1.
     * @return defaultFactorOne
     */
    public int getDefaultKeyOne() {
        return defaultKeyOne;
    }

    /**
     * This getter-method returns the current object's Default Key-2.
     * @return defaultFactorTwo
     */
    public int getDefaultKeyTwo() {
        return defaultKeyTwo;
    }

    // Current User-defined keypair of object
    private final int userKeyOne;
    private final int userKeyTwo;

    /**
     * This getter-method returns the current object's state.
     * @return current state
     */
    protected State getCurrentState() {
        return currentState;
    }

    /**
     * Internal constructor for default encryption keypair. Call this constructor when using
     * Default keypair. Instantiating with this constructor will set the following attributes
     * of object.
     * <pre>
     *      state               -   USER_DEFINED
     *      defaultFactorOne    -   Utility.generateRandomKeyOne();
     *      defaultFactorTwo    -   Utility.generateRandomKeyOne();
     *      factorOne           -   0   (INTEGER)
     *      factorTwo           -   0   (INTEGER)
     * </pre>
     * This class uses {@code Utility} class to generate a random number within a defined range
     * @see Utility#generateRandomKeyOne()
     * @see Utility#generateRandomKeyTwo()
     */
    public Encrypt() {
        currentState = State.DEFAULT;
        defaultKeyOne = Utility.generateRandomKeyOne();
        defaultKeyTwo = Utility.generateRandomKeyTwo();
        userKeyOne = 0;
        userKeyTwo = 0;
    }

    /**
     * Internal constructor for user defined encryption keypair. Call this constructor when using
     * User Defined keypair. Instantiating with this constructor will set the following attributes
     * of object.
     * <pre>
     *      state               -   USER_DEFINED
     *      this.userKeyOne     -   userKeyOne
     *      this.userKeyTwo     -   userKeyTwo
     *      defaultFactorOne    -   0   (INTEGER)
     *      defaultFactorTwo    -   0   (INTEGER)
     * </pre>
     * @throws KeyPairNumberOutOfBounds When exceed range
     * @param userKeyOne User Defined Key 1
     * @param userKeyTwo User Defined Key 2
     *
     */
    public Encrypt(int userKeyOne, int userKeyTwo) throws KeyPairNumberOutOfBounds {
        // Check if value is out of range
        if(userKeyOne <=0 || userKeyOne > 5 || userKeyTwo <=0 || userKeyTwo > 20)
            throw new KeyPairNumberOutOfBounds("Keypair value range exceeded");

        currentState = State.USER_DEFINED;
        this.userKeyOne = userKeyOne;
        this.userKeyTwo = userKeyTwo;
        defaultKeyOne = 0;
        defaultKeyTwo = 0;
    }

    /**
     * Returns the Keypair of current encrypt object as a String only if the state is :
     * {@code DEFAULT}. Otherwise, returns {@code null}.
     * @apiNote This method is deprecated due to security reasons and no longer needed.
     * @return Keypair as a String
     */
    @Deprecated
    protected String toKeyString(){
        if(getCurrentState() == State.DEFAULT){
            return "KEY :: {" + getDefaultKeyOne() + ","
                    + getDefaultKeyTwo() + "}";
        } else return null;
    }

    /**
     * This method is for encrypting a String. Calls {@link Encrypt#encryptSystem(String)} for encrypting.
     * @param message The String that is to be encrypted
     * @return Encrypted message.
     */
    public String encrypt(String message){
        return encryptSystem(message);
    }

    /**
     * This method takes a String {@code data} and check the associated object's {@code state}.
     * Depending on the state, the method sets the keypair of encryption by calling
     * {@link Utility#setKeyPair(int, int)}. Then calls the necessary methods of {@link Utility} class
     * to encrypt the String {@code data}. After encrypting, it calls {@link Utility#resetKeyPair()}
     * to clear the keypair.
     * @param data The String that is to be encrypted
     * @return Encrypted Message
     * @see Utility#keyOneEncrypt(byte[])
     * @see Utility#keyTwoEncrypt(byte[])
     * @see Utility#reverseBytes(byte[])
     * @see Utility#setKeyPair(int, int)
     * @see Utility#resetKeyPair()
     */
    private String encryptSystem(String data){
        // Determine the state and set keypair
        if(getCurrentState() == State.DEFAULT)
            Utility.setKeyPair(this.defaultKeyOne, this.defaultKeyTwo);
        else
            Utility.setKeyPair(this.userKeyOne, this.userKeyTwo);

        // Main Operations:
        byte[] k1 = Utility.keyOneEncrypt(data.getBytes(StandardCharsets.UTF_8));
        byte[] reverseBytes = Utility.reverseBytes(k1);
        byte[] k2 = Utility.keyTwoEncrypt(reverseBytes);

        // Reset the Keypair
        Utility.resetKeyPair();

        return new String(k2);
    }
}