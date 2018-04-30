package MerkleHellmanKnapsackCryptoProject;

import java.math.BigInteger;

public class BigIntegerNode {
	//create a node class
	
		private BigInteger data;
		private BigIntegerNode nextNode;
		/**
		 * default constructor for Node
		 */
	  public BigIntegerNode() {
	  	   data = null;
	  	   nextNode = null;
	  }
	  
	  // constructor
	  public BigIntegerNode(BigInteger data, BigIntegerNode nextNode) {
	  	    this.data = data;
	  	    this.nextNode = nextNode;
	  }
	  
	  // function to set data to current node
	  public void setData(BigInteger data) {
	  	     this.data = data;
	  }
	  
	  // function to set link to the next node
	  public void setnextNode (BigIntegerNode nextNode) {
	  	      this.nextNode = nextNode;
	  }
	  
	  // get the data in the current node
	  public BigInteger getData() {
	  	      return this.data;
	  }
	  
	  // get the link in the current node
	  public BigIntegerNode getnextNode() {
	  	      if (this.nextNode == null) {
	  	    	      return null;
	  	      }
	  	    	  return this.nextNode;
	  	 }
	  
	  public String toString() {
	  	      return this.data.toString();
	  
	 }
	  
	  
	  
	  
	  
	  
	  
	  
	

}
