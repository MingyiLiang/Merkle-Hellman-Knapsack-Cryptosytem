package MerkleHellmanKnapsackCryptoProject;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;
//auther Mingyi Liang
public class MerkleHellman {
    
	private BigInteger[] w,b;
	private BigInteger q,r; 
	private Random rand = new Random();
	private static final int MAX_CHAR = 80;// the maximum characters 
	private static final int BINARY_LENGTH = MAX_CHAR * 8;// the length of the maximum bits
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	/**
	 * constructor
	 * @param args
	 */
	public MerkleHellman() {
		genKeys();
	}
	
	private void genKeys() {
		int maxBits = 50;  //maximum number of bits of a randomly generated big integer
		// generate public and private key
		
		// w is a super increasing big integer list
		w = new BigInteger[BINARY_LENGTH];
		// add 1 to w
		w[0] = new BigInteger(maxBits, rand).add(BigInteger.ONE);
		BigInteger sum = new BigInteger(w[0].toByteArray());  // sum of the w BigInteger array
		// generate super increasing integer
		
		for (int i = 1; i < w.length; i++) {
			w[i] = sum.add(new BigInteger(maxBits, rand).add(BigInteger.ONE));
            sum = sum.add(w[i]);
		 }
		// q is a random number large than the sum of w
		q = sum.add(new BigInteger(maxBits, rand).add(BigInteger.ONE));
		// r is a coprime less than q
		r = q.subtract(BigInteger.ONE);
		// b is public key
		b = new BigInteger[BINARY_LENGTH];
		for (int i = 0; i < b.length; i++) {
			b[i] = w[i].multiply(r).mod(q);
		 }
	}
		
	
	    /**
	     * encrypt the message
	     * the length of string should be less than 80
	     * the message is encoded in 8 bits
	     */
		
		public String encryptMsg(String message) {
			if (message.length() > MAX_CHAR) {
				throw new IndexOutOfBoundsException("Maximum message length allowed is " + MAX_CHAR + ".");
			}
			if (message.length() <= 0) {
				throw new Error("Cannot encrypt an empty string.");
			}
			//convert message to binary string
			String msgBinary = new BigInteger(message.getBytes(UTF8)).toString(2);
			// pad 0 to the left
			if (msgBinary.length() < BINARY_LENGTH) {
	            msgBinary = String.format("%0" + (BINARY_LENGTH - msgBinary.length()) + "d", 0) + msgBinary;
	        }
			// produce final encrypted message
			BigInteger result = BigInteger.ZERO;
			for (int i = 0; i < msgBinary.length(); i++) {
	            result = result.add(b[i].multiply(new BigInteger(msgBinary.substring(i, i+1))));
	        }

	        return result.toString();
		}
		//decrypt  the given message
		public String decryptMsg(String ciphertext) {
			BigInteger tmp = new BigInteger(ciphertext).mod(q).multiply(r.modInverse(q)).mod(q);
			byte[] decrypted_binary = new byte[w.length];// the decrypted message in binary
			for (int i = w.length - 1; i >= 0; i--) {
	            if (w[i].compareTo(tmp) <= 0) {  // found the largest element in w which is less than or equal to tmp
	                tmp = tmp.subtract(w[i]);
	                decrypted_binary[i] = 1;
	            } else {
	                decrypted_binary[i] = 0;
	            }
	        }
			
			// covert bity to string
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < decrypted_binary.length; i++) {
			      sb.append(decrypted_binary[i]);
			}

			return new String(new BigInteger(sb.toString(), 2).toByteArray());			
			
			
		}
	    /**
	     * test drive
	     * @param args
	     */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MerkleHellman crypto = new MerkleHellman();
		System.out.println("Public and private keys have been generated.\n");
        Scanner input = new Scanner(System.in);
        String message;
        
        while (true) {
        	System.out.println("Enter a string and I will encrypt it as single large integer:");
        message = input.nextLine();
        if (message.length() > MAX_CHAR)
            System.out.printf("\nYour message should have at most %d characters! Please try again.\n\n", MAX_CHAR);
        else if (message.length() <= 0)
            System.out.println("\nYou message should not be empty! Please try again.\n\n");
        else break;
        }
        input.close();
        System.out.println("\nClear text:");
        System.out.println(message);
        System.out.println("\nNumber of clear text bytes = " + message.getBytes().length);

        String encrypted = crypto.encryptMsg(message);
        System.out.println("\n\"" + message + "\"" + " is encrypted as:");
        System.out.println(encrypted);

        System.out.println("\nResult of decryption:");
        System.out.println(crypto.decryptMsg(encrypted));
        
        
        
		
		
	}

}
