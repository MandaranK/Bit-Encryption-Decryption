/**
 * Mandaran Krishnakumar
 * This Java Class encrypts a String message into an array of bytes, and decrypts an array of bytes into a String message
 */
import java.util.Random;

public class BitCryption {

	public static void main(String[] args) {
		// all methods accept capital letters, numbers, symbols, etc.
		String decrypted = "Hello World!";	// input message to be encrypted (using XOR operation between bytes)
		byte[] encrypted = new byte[] {72, 120, -12, -104, 3, 105, -40, 47, -65, 125, -47, 39, 
				0, 29, -104, -12, 108, 73, -113, 64, -51, 17, -75, 6};	// input encrypted message to be decrypted (using another XOR)
		
		System.out.println(printArray(encrypt(decrypted)));		// printing the encrypted message (array of bytes)
		System.out.println(decrypt(encrypted));					// printing the decrypted message
		
		// Decrypting a message can be done by doing another XOR operation. For example, if x is 0 and y is 1, x^y would be 1.
		// 1^1 is 0. Everytime another (every 2nd time) XOR operation is done, it will return back to its original byte number.
		
		// I created an array with the same length as the length of String message and added random byte numbers to the array.
		
		// I used the method nextBytes() to fill the array with random bytes. So each time the encrypt method is run, 
		// the array will be filled with a new set of random numbers (bytes).
		
		// The random array should be able to be kept secret. The description above the method tells the user what it does. 
		// If the numbers are put into a text editor, it will show random random symbols. 

	}
	
	/**
	 * Encrypts a message using bytes (encrypted using an array of random byte keys)
	 * @param x the String message to be encrypted
	 * @return the array of encrypted bytes and the random byte keys used, all in one byte array
	 */
	public static byte[] encrypt(String x)
	{
		Random random = new Random();	// initializing random
		byte[] xBytes = x.getBytes();	// converting String x into an array of bytes
		byte[] randBytes = new byte[xBytes.length];		// creating random bytes array
		random.nextBytes(randBytes);	// filling the random bytes array with random bytes
		byte[] encryptedBytes = new byte[xBytes.length];	// creating the encrypted bytes array
		for(int i = 0; i < encryptedBytes.length; i++)
		{
			encryptedBytes[i] = (byte) (xBytes[i] ^ randBytes[i]);	// XOR the message bytes with random bytes
		}
		byte[] byteReturn = new byte[encryptedBytes.length + randBytes.length];	// creating the byte array to return
		for(int j = 0; j < encryptedBytes.length; j++)
		{
			byteReturn[j] = encryptedBytes[j];	// adding the encrypted bytes to the return array
		}
		int rand_index = 0;		// the index of the random bytes array
		for(int j = encryptedBytes.length; j < (encryptedBytes.length + randBytes.length); j++)
		{
			
			byteReturn[j] = randBytes[rand_index];	// adding the random bytes to the return array (after the encrypted bytes)
			rand_index++;	// increasing the index of the random bytes array by 1
		}
		
		return byteReturn;
		
	}
	
	/**
	 * Decrypts an array of bytes (with the random keys) into a String message
	 * @param mesg an array of encrypted bytes and the random byte keys used, all in a byte array
	 * @return the decrypted String message using the array of bytes
	 */
	public static String decrypt(byte[] mesg)
	{
		byte[] decryptedBytes = new byte[mesg.length / 2];	// length of decrypted bytes is half the size of mesg
		
		int rand_index = mesg.length / 2;	// index of random bytes starts halfway in the array
		for(int i = 0; i < decryptedBytes.length; i++)
		{
			// XOR between the encrypted bytes and the random bytes, and adding it to the decrypted bytes array
			decryptedBytes[i] = (byte) (mesg[i] ^ mesg[rand_index]);
			rand_index++;
		}
		
		String decrypted = new String(decryptedBytes);		// converting the decrypted bytes to a String message
		
		return decrypted;
		
	}
	
	/**
	 * Converts a byte[] array into a String (so it's able to print the array)
	 * @param array a byte[] array to be converted into a String
	 * @return a String that looks like the input byte[] array (for the function of printing it)
	 */
	public static String printArray(byte[] array)
	{
		String result = "[";	// start the String of the array with a opening bracket
		for(int i = 0; i < array.length; i++)
		{
			if(i == array.length-1)
				result += array[i];	// if it's at the last element of the array, no need for a ", "
			else
				result += array[i] + ", ";	// add a comma and space after each element of the array
		}
		result += "]";	// end the String of the array with a closing bracket
		return result;
	}

}
