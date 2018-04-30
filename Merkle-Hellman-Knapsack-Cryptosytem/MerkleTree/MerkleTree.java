package MerkleTree;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;

/**
 * implement Merkle tree by using singlylinkedlist
 * @author mingyiliang
 *
 */

public class MerkleTree {
	
	/**
	 * build the whole tree with a likned list
	 * @param inputMsg
	 * @return the upperroot of the merkle tree
	 * @throws NoSuchAlgorithmException
	 */
	public String markleRoot (LinkedListTree inputMsg) throws NoSuchAlgorithmException {
		LinkedListTree sq = new LinkedListTree();
		sq = inputMsg;
		while(sq.getSize()>1) {
			if (sq.getSize() % 2 == 0) {
				sq = upperList(sq);
			}else {
				sq.addNodeEnd(sq.get(sq.getSize()-1).getData());
				sq = upperList(sq);
			}
		}
		return sq.get(0).getData();

	}
	
	/**
	 * creat the upper list 
	 * @param inputList
	 * @return the hash of data
	 * @throws NoSuchAlgorithmException
	 */
	public LinkedListTree upperList (LinkedListTree inputList) throws NoSuchAlgorithmException {
		LinkedListTree sb = new LinkedListTree();
		for(int i = 0; i < inputList.getSize()-1; i+=2) {
			sb.addNodeEnd(h(inputList.get(i).getData()+inputList.get(i+1).getData()));
		}
		return sb;
	}
	

	/**
	 * change String of word to hash string
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String h(String text) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256"); byte[] hash =
		digest.digest(text.getBytes(StandardCharsets.UTF_8)); StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= 31; i++) {
		byte b = hash[i];
		sb.append(String.format("%02X", b)); }
		return sb.toString(); }
		
	
	/**
	 * read txt and csv data
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MerkleTree treeRoot = new MerkleTree();
		LinkedListTree inputTree = new LinkedListTree();
		String message;
		
		try{
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("CrimeLatLonXY.csv");
            //For example.txt
            //CrimeLatLonXY.csv
            //CrimeLatLonXY1990_Size2.csv
            //CrimeLatLonXY1990_Size3.csv
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                // Print the content on the console
                inputTree.addNodeEnd(h(strLine));
                                
            }
            //Close the input stream
            message = treeRoot.markleRoot(inputTree);
            System.out.println(message);
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
