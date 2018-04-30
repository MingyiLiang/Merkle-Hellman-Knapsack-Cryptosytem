package MerkleHellmanKnapsackCryptoProject;
import java.math.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Random;
/**
 * Implement key generation, envryption and decryption 
 * using MHK cryptosystem
 * @author mingyiliang
 *
 */

public class MerkleHellmanKnapsack {
	private SinglyBigLinkedList w;
	private SinglyBigLinkedList b;
	private BigInteger q;
	private BigInteger r;
	private int maxBinaryLength = 640; // the Maximum nodes of the whole list
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	/**
	 * default the constructor
	 */
	public MerkleHellmanKnapsack() {
		
		generateKeys();
		
	}
	
	     // generate keys of w,q,r
	private void generateKeys() {
		//generate public and private keys
		// w is a super increasing big integer list
		w = new SinglyBigLinkedList();
		b = new SinglyBigLinkedList();
		
		// add 1 to w
		w.addNodeEnd(new BigInteger("1"));
		// generate w
		for (int i = 1; i < 640; i++) {
			w.addNodeEnd(superIncreasingNum(w));
		}
		
		// create value for q, q is more than the sum of w
		q = superIncreasingNum(w);
		
		//generate value for r,r is the r is a coprime less than q
		Random random = new Random();
		do {
            r = q.subtract(new BigInteger(random.nextInt(1000) + ""));
        } while ((r.compareTo(new BigInteger("0")) > 0) && (q.gcd(r).intValue() != 1));
		
		//r = q.subtract(BigInteger.ONE);
		// generate public key b
		for (int i = 0; i < 640; i++) {
            b.addNodeEnd(w.get(i).getData().multiply(r).mod(q));
            
        }
		
	}
	
	//function to generate super increasing number
	private BigInteger superIncreasingNum(SinglyBigLinkedList wList) {
		BigIntegerNode node;
		// sum of the big integer linkedlist
		BigInteger sum = new BigInteger("0");
		
		int i = 0;
		while ((node = wList.get(i)) != null) {
            sum = sum.add(node.getData());
            i++;
        }
		
		Random random = new Random();
		return sum.add(new BigInteger(random.nextInt(5)+1+""));
		
	}
	
	 /**
     * encrypt the message
     * the length of string should be less than 80
     * the message is encoded in 8 bits
     */
	
	public String encrypt(String message) {
		
		if (message.length() > 80) {
			throw new IndexOutOfBoundsException("Maximum message length allowed is " + 80 );
		}
		if (message.length() <= 0) {
			throw new Error("it can't be an empty string.");
		}
		//convert input to binary 
		String binaryInput = new BigInteger(message.getBytes(UTF8)).toString(2);
		// add 0 to the left of the binary input
		if (binaryInput.length() < 640) {
			binaryInput = String.format("%0"+ (640 - binaryInput.length()) + "d", 0) + binaryInput;
			
		}
				
		// produce final encrypted message
		BigInteger result = BigInteger.ZERO;
		int i = 0;
		while (i < binaryInput.length()) {
			result = result.add(b.get(i).getData().multiply(new BigInteger(binaryInput.substring(i,i+1))));
			i ++;
		}
			
		return result.toString();
	}
	
	/**
	 * this function used to decrypt the message
	 * @param cipherMsg
	 * @return
	 */
	//decrypt  the given message
	
	public String decrypt(String cipherMsg) {
		BigInteger s = new BigInteger(cipherMsg).mod(q).multiply(r.modInverse(q)).mod(q);
		
		// define a list that contain all the byte message 
		byte[] binaryList = new byte[640];
		for (int i = 639; i >= 0; i=i-1) {
			BigInteger value = w.get(i).getData();
			
			if (value.compareTo(s) > 0) {
				binaryList[i] = 0;				
			}else {
				binaryList[i] = 1;
				s = s.subtract(value);
			}
		}

		
		// covert bity to string
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 640; i++) {
		      sb.append(binaryList[i]);
		}

		return new String(new BigInteger(sb.toString(), 2).toByteArray());
			
	}
	 
	
	/**
	 * this is a test for MerkelHellmanKnapsackEncryption
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MerkleHellmanKnapsack crypto = new MerkleHellmanKnapsack();
		System.out.println("Public and private keys have been generated.\n");
        Scanner input = new Scanner(System.in);
        String message;
        
        while (true) {
        	System.out.println("Enter a string and I will encrypt it as single large integer:");
        message = input.nextLine();
        if (message.length() > 640 )
            System.out.printf("\nYour message should have at most %d characters! Please try again.\n\n",80);
        else if (message.length() <= 0)
            System.out.println("\nYou message should not be empty! Please try again.\n\n");
        else break;
        }
        input.close();
        System.out.println("\nClear text:");
        System.out.println(message);
        System.out.println("\nNumber of clear text bytes = " + message.getBytes().length);

        String encrypted = crypto.encrypt(message);
        System.out.println("\n\"" + message + "\"" + " is encrypted as:");
        System.out.println(encrypted);
        
       

        System.out.println("\nResult of decryption:");
        System.out.println(crypto.decrypt(encrypted));
       	
		
	}


}
