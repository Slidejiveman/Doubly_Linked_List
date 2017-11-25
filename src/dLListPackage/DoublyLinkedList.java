/**
 * The list package contains only the list to encourage reuse.
 */
package dLListPackage;

/**
 * @author RyderDale
 * Professor: David North
 * For: Data Structures and Algorithms
 * Program 1: DoublyLinkedList
 * Due: 1/26/16
 */
public class DoublyLinkedList implements Cloneable
{
	/**
	 * The nested node class is private, meaning it cannot be accessed without using the DoublyLinkedList class.
	 * Furthermore, the node is designed to work specifically with strings in this instance.
	 * @param <String>
	 */
	private static class Node<String>
	{
		private String element;
		private Node<String> previous;
		private Node<String> next;
		
		/**
		 * The constructor for the node class assigns the element and pointers to other nodes passed into it.
		 * The order of the parameters is element of the node, pointer to the node previous, pointer to the node
		 * subsequent.
		 * 
		 * @param element
		 * @param previous
		 * @param next
		 */
		public Node(String element, Node<String> previous, Node<String>next)
		{
			setElement(element);
			setPrevious(previous);
			setNext(next);
		}
		
		// Setters and Getters for the nested node class-------------------------
		public String getElement()
		{
			return this.element;
		}
		public Node<String> getPrevious()
		{
			return this.previous;
		}
		public Node<String> getNext()
		{
			return this.next;
		}
		public void setElement(String element)
		{
			this.element = element;
		}
		public void setPrevious(Node<String> previous)
		{
			this.previous = previous;
		}
		public void setNext(Node<String> next)
		{
			this.next = next;
		}
	}
	//----End of Nested Node class -----------------------------------------------
	private Node<String> header;
	private Node<String> trailer;
	private int size = 0;				// Size is included to count nodes in list
	
	/**
	 * The DoublyLinkedList constructor assigns default values to the header and the trailer.
	 * The header and trailer serve as sentinels, which drastically decrease the difficulty
	 * of insertion algorithms. Additionally, the setNext() method sets the trailer as the
	 * second node in the list.
	 */
	public DoublyLinkedList()
	{
		header = new Node<String>(null, null, null);
		trailer = new Node<String>(null, header, null);
		header.setNext(trailer);
	}
	
	//----Functional Methods-----------------------------------------------------
	/**
	 * Returns a copy of the current size of the list
	 * @return size
	 */
	public int getSize()
	{
		return this.size;
	}
	/**
	 * Determines whether or not the list has any nodes in it.
	 * @return true or false
	 */
	public boolean isEmpty()
	{
		return (this.size == 0);
	}
	/**
	 * Collects a copy of the value succeeding the header.
	 * @return null or a copy of first element's value
	 */
	public String getFirst()
	{
		if(isEmpty())
			return null;
		else
			return header.getNext().getElement();
	}
	/**
	 * Collects a copy of the value preceding the trailer
	 * @return null or a copy of last element's value
	 */
	public String getLast()
	{
		if(isEmpty())
			return null;
		else
			return trailer.getPrevious().getElement();
	}
	
	//----Main Algorithm Methods----------------------------------------
	/**
	 * Adds an element behind the header sentinel
	 * @param element
	 */
	public void addFirst(String element)
	{
		addNode(element, header, header.getNext());
	}
	/**
	 * Adds an element before the trailer sentinel
	 * @param element
	 */
	public void addLast(String element)
	{
		addNode(element, trailer.getPrevious(), trailer);
	}
	/**
	 * Removes the element behind the header
	 * @return the element removed
	 */
	public String removeFirst()
	{
		if(isEmpty())
			return null;
		else
			return removeNode(header.getNext());
	}
	/**
	 * Removes the element in front of the trailer
	 * @return the element removed
	 */
	public String removeLast()
	{
		if(isEmpty())
			return null;
		else
			return removeNode(trailer.getPrevious());
	}
	
	//----Private helper methods--------------------------------------
	/**
	 * The addElement function adds a new node with an element that is passed in. It then
	 * points the new node to the node that precedes it and succeeds it.
	 * @param element
	 * @param previous
	 * @param next
	 */
	private void addNode(String element, Node<String> previous, Node<String> next)
	{
		Node<String> newNode = new Node<String>(element, previous, next);
		previous.setNext(newNode);
		next.setPrevious(newNode);
		this.size++;
	}
	/**
	 * The removeNode helper function deletes a node, removing it from the list and decreasing the list's size
	 * @param node
	 * @return the element of the deleted node
	 */
	private String removeNode(Node<String> node)
	{
		Node<String> previous = node.getPrevious();
		Node<String> next = node.getNext();
		previous.setNext(next);
		next.setPrevious(previous);
		this.size--;
		return node.getElement();
	}
	
	//----Extra, Original Functions--------------------------------------------------------
	/**
	 * Creates an array of strings that contains the list of elements in order from head to tail
	 * @return listArray
	 */
	public String[] toArrayFromFirst()
	{
		// Create a holder to keep track of where the head needs to return
		Node<String> holder = header;
		String[] listArray = new String[size];
		for(int i = 0; i < size; i++)
		{
			listArray[i] = getFirst();
			this.header = header.getNext();
		}
		
		// Reset the header to its original sentinel position
		this.header = holder;
		return listArray;
	}
	/**
	 * Creates an array of strings that contains the list of elements printed in order from tail 
	 * to head
	 * @return listArray
	 */
	public String[] toArrayFromLast()
	{
		Node<String> holder = header;
		String[] listArray = new String[size];
		for(int i = 0; i < size; i++)
		{
			listArray[i] = getLast();
			this.trailer = trailer.getPrevious();
		}
		this.trailer = holder;
		return listArray;
	}
	/**
	 * Returns a copy of the list that is a deep clone, meaning that it is a different list that
	 * points to copies of what the original list contains
	 * @return deepCopy
	 */
	public DoublyLinkedList clone() throws CloneNotSupportedException
	{
		DoublyLinkedList deepCopy = (DoublyLinkedList) super.clone();
		if(deepCopy.size > 0)
		{
			Node<String> deepHolder = deepCopy.header;
			Node<String> holder = this.header;
			int loopControl = 0;
			while(loopControl < this.size)
			{
				deepCopy.header.getNext().setElement(this.getFirst());
				deepCopy.header = deepCopy.header.getNext();
				this.header = this.header.getNext();
				loopControl++;
			}
			deepCopy.header = deepHolder;
			this.header = holder;
		}
		return deepCopy;
	}
	
	/**
	 * The toString function prints the contents of the list to which it
	 * is appended.
	 * 
	 * @return returnString
	 */
	public String toString()
	{
		String returnString = "";
		Node<String> holder = this.header;
		
		for(int i = 0; i < getSize(); i++)
		{
			returnString += getFirst() + " ";
			this.header = this.header.getNext();
		}
		this.header = holder;
		
		return returnString;
	}
}
	
