package edu.cmu.andrew.mliang1;


//create a node class
public class Node {
	private String data;
	private Node nextNode;
	
	/**
	 * default constructor for Node
	 */
    public Node() {
    	   data = null;
    	   nextNode = null;
    }
    
    // constructor
    public Node(String data, Node nextNode) {
    	    this.data = data;
    	    this.nextNode = nextNode;
    }
    
    // function to set data to current node
    public void setData(String data) {
    	     this.data = data;
    }
    
    // function to set link to the next node
    public void setnextNode (Node nextNode) {
    	      this.nextNode = nextNode;
    }
    
    // get the data in the current node
    public String getData() {
    	      return this.data;
    }
    
    // get the link in the current node
    public Node getnextNode() {
    	      if (this.nextNode == null) {
    	    	      return null;
    	      }
    	    	  return this.nextNode;
    	 }
    
  
    
    
    
    
    
    
    
    
}
