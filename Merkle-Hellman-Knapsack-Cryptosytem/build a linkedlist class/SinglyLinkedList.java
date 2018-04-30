package edu.cmu.andrew.mliang1;


/**
 * create a singly linked list 
 * @author mingyiliang
 *
 */

public class SinglyLinkedList {
	private Node head;
	private Node tail;
	public int size;
	private Node iterator;
	
	// Default constructor for BigIntegerLinkedList
	 
	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * function to check if this singly linked list is empty
	 * @param args
	 */
	public boolean hasNext() {
		if(this.head == null) {
			return true;
		}else {
			return false;
		}
	}
     
	/**
	 * add a new nod to the end of a list
	 */
	public void addNodeEnd(String newVal) {
		Node newNode = new Node(newVal, null);
		if (head == null) {
			head = newNode;
			tail = head;
		}else {
			tail.setnextNode(newNode);
			tail = newNode;
		}
	}
	
	/**
	 * add a new nod to the begining of a list
	 * @param args
	 */
	public void addNodeBegin(String newVal) {
		Node newNode = new Node(newVal, null);
		if (head == null) {
			head = newNode;
			tail = head;
		}else {
			newNode.setnextNode(head);
			head = newNode;
		}
	}
	
	/**
	 * function to count the number of nodes in the list
	 * @param args
	 */
	
	public int getSize() {
		return size;
	}
	
	
	/**
	 * function to return the node in given position
	 * @param args
	 */
	public Node get(int index) {
        if (head == null) {
            return null;
        }

        Node cursor = head;
        for (int i = 0; i < index; i++) {
            cursor = cursor.getnextNode();
            if (cursor == null) {
                return null;
            }
        }
        return cursor;
    }
	
	/**
	 * reset function
	 */
	
	public void reset()
    {
        iterator = head;
    }
	
	/**
	 * next()function
	 */
	public Object next()
    {
		Object result = iterator.getData();
        iterator = iterator.getnextNode();
        return result;
    }
}

