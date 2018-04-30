package MerkleTree;

public class MerkleTreeNode {



		//create a node class
		
			private String data;
			private MerkleTreeNode nextNode;
			/**
			 * default constructor for Node
			 */
		  public MerkleTreeNode() {
		  	   data = null;
		  	   nextNode = null;
		  }
		  
		  // constructor
		  public MerkleTreeNode(String data, MerkleTreeNode nextNode) {
		  	    this.data = data;
		  	    this.nextNode = nextNode;
		  }
		  
		  // function to set data to current node
		  public void setData(String data) {
		  	     this.data = data;
		  }
		  
		  // function to set link to the next node
		  public void setnextNode (MerkleTreeNode nextNode) {
		  	      this.nextNode = nextNode;
		  }
		  
		  // get the data in the current node
		  public String getData() {
		  	      return this.data;
		  }
		  
		  // get the link in the current node
		  public MerkleTreeNode getnextNode() {
		  	      if (this.nextNode == null) {
		  	    	      return null;
		  	      }
		  	    	  return this.nextNode;
		  	 }
		  
		  public String toString() {
		  	      return this.data.toString();
		  
		 }
		  
	
	
	
	
	
}
