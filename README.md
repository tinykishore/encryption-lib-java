<h1>An Encryption System Package</h1>
<h3>com.message.security</h3>

<p>
    This package contains 4 classes that helps encrypt and decrypt a message
    with limited features. The encryption system is pretty basic, it just 
    manipulates ASCII values of each character of a given string.
    The four classes are :

    Encrypt.java
    Decrypt.java
    Utility.java
    KeyPairNumberOutOfBounds.java

</p>

<h3>Mechanism:</h3>
<p>
    1. It takes a string.<br>
    2. It converts each character of the String into ASCII value and store it inside an array.<br>
    3. Adds a number to each ASCII value.<br>
    4. Reverse the array.<br>
    5. Subtracts a number from the last half part of the array.<br>
    6. Converts the array into a String and that is our encrypted String.

_Decryption follows the exact same rules but in reversed order._

</p>

<h3>Demonstration :</h3>
<p> 

1. A String : "Hello World" -

`String message = "Hello World";`

2. Now we have to convert it to ASCII values and store it inside of an array -

`byte[] b = message.getBytes(StandardCharsets.UTF_8);`

3. Now we have : H e l l o <space> W o r l d

`b = {72,101,108,108,111,32,87,111,114,108,100}`

4. Now we add, let's say, 3 to every index -

`b = {75,104,111,111,114,35,90,114,117,111,103}`

5. Now we have to reverse this array

` b = {103,111,117,114,90,35,114,111,111,104,75}`

6. Now we have to subtract the last half index an integer, let's say 2. Here the array size is 11/2 = 5, so we start
   from index 5.

` b = {103,111,117,114,90,33,112,109,109,102,73}`

7. Now if we convert to String we get :

`g o u r Z ! p m m f I`

_**This is our encrypted string**_

</p>
<hr>
<h4>
For More Information and Detailed documentation <br>
Visit : <a href="https://tinykishore.github.io/Encryption-Feature/">Full Documentation (JavaDoc)</a>
</h4>

