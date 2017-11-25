/**
 * The testing environment is its own package because other programmers will want
 * to write their own code to use my linked list object with.
 */
package testPackage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * In order to use the linked list, it needs to be imported from its package.
 */
import dLListPackage.DoublyLinkedList;

/**
 * @author RyderDale
 * Professor: David North
 * For: Data Structures and Algorithms
 * Program 1: DoublyLinkedList
 * Due: 1/26/16
 */
public class DLLTest 
{
	/**
	 * The main method tests the functionality of my doubly linked list class
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args)
	{
		
		// Create and populate list
		DoublyLinkedList countries = new DoublyLinkedList();
		countries.addFirst("USA");
		countries.addLast("Germany");
		countries.addFirst("France");
		countries.addLast("England");
		countries.addFirst("Belgium");
				
		// Create and print the array forward
		int size = countries.getSize();
		String[] countryArray = new String[size]; 
		countryArray = countries.toArrayFromFirst();
		System.out.println("Print Array Forward: ");
		printArray(countryArray);
		
		// Print the array backward
		countryArray = countries.toArrayFromLast();
		System.out.println("\nPrint Array Backward: ");
		printArray(countryArray);
		
		// Deep Clone and print copied list forward
		try
		{
			DoublyLinkedList countriesCopy = countries.clone();
			System.out.print("\nDeepCopy Forward: \n" + countriesCopy.toString());
		}
		catch(CloneNotSupportedException exception)
		{
			System.out.println("Cloning failure.");
		}
	}
	
	/**
	 * Prints the array created by the DoublyLinkedList conversion
	 * @param array
	 */
	public static void printArray(String[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
	}
}
