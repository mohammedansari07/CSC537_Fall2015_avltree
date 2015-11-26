
package avltree;

import java.util.Random;


public class Driver 
{
	public static void main(String[] args)
	{
		BinaryTree theTree = new BinaryTree();
		Random r = new Random();
		for(int i = 0; i <100; i++)
		{
			theTree.add(new PatientRecord("Patient", "" + i, r.nextInt(81)+5));
		}
		
		//theTree.getSmallest().display();
		//theTree.getLargest().display();
		//theTree.getPayload().display();
		System.out.println(theTree.isBalanced());
	}
}
