package com.message.security;

import java.nio.charset.StandardCharsets;

/**
 * The {@code Decrypt} class contains several useful class fields and methods. It can be instantiated
 * in two ways. The facility provided by the {@code Decrypt} class is decrypt a String
 * with a keypair which is encrypted by the {@code Encrypt} class from {@link com.message.security}
 * package. It uses another class {@code Utility} for basic decrypting operations.
 * All the class fields and methods are either {@code private} or
 * {@code protected} except the constructor and {@code decrypt} method.<br><br>
 *
 * If an String, encrypted with Default keypair, is to be decrypted, use the following constructor:
 * <pre>
 *     Decrypt d = new Decrypt(encrypt_object);
 * </pre>
 * This will automatically detect the keypair and decrypt the message. But if an String with
 * User defined keypair is to be decrypted, use the following constructor:
 * <pre>
 *     Decrypt d = new Decrypt(key1, key2);
 * </pre>
 * Where {@code key1} and {@code key2} are the user defined key which were used to encrypt the String. The object
 * will not be able to decrypt the String if keypair do not match. It cannot detect the User-defined
 * keypair.
 * @see Utility
 * @see Encrypt
 * @author tinykishore
 */

public final class Decrypt {
    // Keypair for decryption
    private final int keyOne;
    private final int keyTwo;

    /**
     * This getter-method returns the current object's key-1.
     * @return First factor for decryption
     */
    protected int getKeyOne() {
        return keyOne;
    }

    /**
     * This getter-method returns the current object's key-2.
     * @return Second factor for decryption
     */
    protected int getKeyTwo() {
        return keyTwo;
    }

    /**
     * Internal constructor for decrypting Default keypair encryption. This will assign the
     * decrypting objects keypair with the encrypting object's keypair.
     * @param e object of Encrypt class which was used to encrypt the String.
     */
    public Decrypt(Encrypt e) {
        this.keyOne = e.getDefaultKeyOne();
        this.keyTwo = e.getDefaultKeyTwo();
    }

    /**
     * Internal constructor for decrypting User-defined keypair encryption. This constructor
     * will take two integer parameter which is the keypair. Using that keypair, the decrypt
     * object will decrypt a String.
     * @throws KeyPairNumberOutOfBounds When Exceed Range
     * @param userKeyOne Key 1
     * @param userKeyTwo Key 2
     */
    public Decrypt(int userKeyOne, int userKeyTwo) throws KeyPairNumberOutOfBounds {
        if(userKeyOne <=0 || userKeyOne > 5 || userKeyTwo <=0 || userKeyTwo > 20)
            throw new KeyPairNumberOutOfBounds("Keypair value range exceeded");

        this.keyOne = userKeyOne;
        this.keyTwo = userKeyTwo;
    }

    /**
     * Returns the Keypair of current decrypt object as a String.
     * This method is deprecated due to security reasons and no longer needed.
     * @return Keypair as a String
     */
    @Deprecated
    protected String toKeyString() {
        return "KEY :: {" + getKeyOne() + "," + getKeyTwo() + "}";
    }

    /**
     * This method is for decrypting a String. Calls {@link Decrypt#decryptSystem(String)} for
     * decrypting.
     * @param message The String that is to be decrypted
     * @return Decrypted message.
     */
    public String decrypt(String message){
        return decryptSystem(message);
    }

    /**
     * This method takes a String as parameter and sets the keypair of decryption by calling
     * {@link Utility#setKeyPair(int, int)}. Then calls the necessary methods of {@link Utility}
     * class to decrypt the String data. After decrypting, it calls {@link Utility#resetKeyPair()}
     * to clear the keypair.
     * @param data The String that is to be decrypted
     * @return Decrypted Message
     * @see Utility#keyOneDecrypt(byte[])
     * @see Utility#keyTwoDecrypt(byte[])
     * @see Utility#reverseBytes(byte[])
     * @see Utility#setKeyPair(int, int)
     * @see Utility#resetKeyPair()
     */
    private String decryptSystem(String data){
        // Set Keypair
        Utility.setKeyPair(keyOne, keyTwo);

        // Main Operations:
        byte[] k2 = Utility.keyTwoDecrypt(data.getBytes(StandardCharsets.UTF_8));
        byte[] reverseBytes = Utility.reverseBytes(k2);
        byte[] k1 = Utility.keyOneDecrypt(reverseBytes);

        // Reset the Keypair
        Utility.resetKeyPair();
        return new String(k1);
    }

}
