package avltree;
public class BinaryTree 
 {
 	private static BinaryTree topRoot;
 	private PatientRecord payload;
 	private BinaryTree left;
 	private BinaryTree right;
 	
 	
 	public BinaryTree()
 	{
 		this.payload = null;
 		this.left = null;
 		this.right = null;
 		BinaryTree.topRoot = this;
 	}
 	
 	private BinaryTree(boolean isChild)
 	{
 		if(isChild)
 		{
 			this.payload = null;
 			this.left = null;
 			this.right = null;
 		}
 	}
 	
 	public boolean isBalanced()
 	{
 		int leftDepth = 0;
 		int rightDepth = 0;
 		if(this.left != null)
 		{
 			leftDepth = this.left.getMaxDepth();
 		}
 		if(this.right != null)
 		{
 			rightDepth = this.right.getMaxDepth();
 		}
 		return Math.abs(leftDepth - rightDepth) <= 1;
 	}
 	
 	public int getMaxDepth()
 	{
 		int leftDepth = -1;
 		int rightDepth = -1;
 		
 		if(this.left != null)
 		{
 			leftDepth = 1 +  this.left.getMaxDepth();
 		}
 		if(this.right != null)
 		{
 			rightDepth = 1 +  this.right.getMaxDepth();
 		}
 		return Math.max(leftDepth, rightDepth);
 	}
 
 	public PatientRecord getPayload()
 	{
 		return this.payload;
 	}
 	
 	public PatientRecord getSmallest()
 	{
 		if(this.left == null)
 		{
 			return this.payload;
 		}
 		else
 		{
 			return this.left.getSmallest();
 		}
 	}
 	
 	public PatientRecord getLargest()
 	{
 		if(this.right == null)
 		{
 			return this.payload;
 		}
 		else
 		{
 			return this.right.getLargest();
 		}
 	}
 	
 	private void rebalance()
 	{
 		int leftDepth = -1;
 		int rightDepth = -1;
 		
 		if(this.left != null)
 		{
 			leftDepth = this.left.getMaxDepth();
 		}
 		if(this.right != null)
 		{
 			rightDepth = this.right.getMaxDepth();
 		}
 		
 		BinaryTree prevTree = null;
 		BinaryTree currTree = this;
 		int numSteps = -1;
 		String first = "";
 		String second = "";
 
 		if(leftDepth > rightDepth)
 		{
 			first = "Left";
 			numSteps = leftDepth - 1;
 		}
 		else
 		{
 			first = "Right";
 			numSteps = rightDepth - 1;
 		}	
 		//run down the tree
 		for(int i = 0; i < numSteps; i++  )
 		{
 			leftDepth = -1;
 			rightDepth = -1;
 			if(currTree.left != null)
 			{
 				leftDepth = currTree.left.getMaxDepth();
 			}
 			if(currTree.right != null)
 			{
 				rightDepth = currTree.right.getMaxDepth();
 			}
 			if(leftDepth > rightDepth)
 			{
 				prevTree = currTree;
 				currTree = currTree.left;
 			}
 			else
 			{
 				prevTree = currTree;
 				currTree = currTree.right;
 			}
 		}
 		
 		//what is the final out of balance directions?
 		BinaryTree a = prevTree;
 		BinaryTree b = currTree;
 		BinaryTree c = null;
 		
 		leftDepth = -1;
 		rightDepth = -1;
 		if(currTree.left != null)
 		{
 			leftDepth = currTree.left.getMaxDepth();
 		}
 		if(currTree.right != null)
 		{
 			rightDepth = currTree.right.getMaxDepth();
 		}
 		if(leftDepth > rightDepth)
 		{
 			second = "Left";
 			c = b.left;
 		}
 		else
 		{
 			second = "Right";
 			c = b.right;
 		}
 		
 		System.out.println("Algorithm to apply is: " +  first +  " - "   +second);
 		this.applyAlgorithm(first, second, a, b, c);
 	}
 	
 	private void doLeftLeft(BinaryTree a, BinaryTree b)
 	{
 		BinaryTree bRT = b.right;
         if(b.right==null)
{
 		b.right = null;
}
 if(a.left==null)
{
 		a.left = null;
}
 		b.add(a);
 		if(bRT != null)
 		{
 			a.add(bRT);
 		}
 	}
 	
 	private void doLeftRight(BinaryTree a, BinaryTree b, BinaryTree c)
 	{
 		BinaryTree cLT = c.left;
        if(b.right==null){
 		b.right = null;
}
if(a.left==null)
{
 		a.left = null;
 		a.add(c);
 		c.add(b);
}
 		if(cLT != null)
 		{
 			b.add(cLT);
 		}
 		this.doLeftLeft(	a, c);
 	}
 	
 	private void doRightRight(BinaryTree a, BinaryTree b)
 	{
 		BinaryTree bLT = b.left;
if(b.left==null)
{
 		b.left = null;
}
if(a.right==null)
{
 		a.right = null;

}
 		b.add(a);
 		if(bLT != null)
 		{
 			a.add(bLT);
 		}
 	}
 	
 	private void doRightLeft(BinaryTree a, BinaryTree b, BinaryTree c)
 	{
 		BinaryTree cRT = c.right;
if(b.left==null)
{
 		b.left = null;

}if(a.right==null)
{
 		a.right = null;
}
 		a.add(c);
 		c.add(b);
 		if(cRT != null)
 		{
 			b.add(cRT);
 		}
 		this.doRightRight(a, c);
 	}
 	
 	private void applyAlgorithm(String first, String second, BinaryTree a, BinaryTree b, BinaryTree c)
 	{
 		if(first.equals("Left") && second.equals("Left"))
 		{
 			//do left left
 			this.doLeftLeft(a, b);
 		}
 		else if(first.equals("Right") && second.equals("Right"))
 		{
 			//do right right
 			this.doRightRight(a, b);
 		}
 		else if(first.equals("Right") && second.equals("Left"))
 		{
 			//do right left
 			this.doRightLeft(a, b, c);
 		}
 		else if(first.equals("Left") && second.equals("Right"))
 		{
 			//do left right
 			this.doLeftRight(a, b, c);
 		}
 		System.out.println("Did it work? " +  BinaryTree.topRoot.isBalanced());
 	}
 	
 	private void add(BinaryTree theTree)
 	{
 		if(theTree.payload.getAge() <= this.payload.getAge())
 		{
 			//try to add left
 			if(this.left == null)
 			{
 				this.left = theTree;
 			}
 			else
 			{
 				this.left.add(theTree);
 			}
 		}
 		else
 		{
 			//try to add right
 			if(this.right == null)
 			{
 				this.right = theTree;
 			}
 			else
 			{
 				this.right.add(theTree);
 			}
 		}
 	}
 	
 	public void add(PatientRecord thePatient)
 	{
 		if(this.payload == null)
 		{
 			this.payload = thePatient;
 			//done adding
 			if(!BinaryTree.topRoot.isBalanced())
 			{
 				BinaryTree.topRoot.rebalance();
 			}
 			else
 			{
 				System.out.println("Tree is Balanced");
 			}
 		}
 		else
 		{
 			if(thePatient.getAge() <= this.payload.getAge())
 			{
 				//add it to the left
 				if(this.left == null)
 				{
 					this.left = new BinaryTree(true);
 					this.left.add(thePatient);
 				}
 				else
 				{
 					this.left.add(thePatient);
 				}
 			}
 			else
 			{
 				//add it to the right
 				if(this.right == null)
 				{
 					this.right = new BinaryTree(true);
 					this.right.add(thePatient);
 				}
 				else
 				{
 					this.right.add(thePatient);
 				}
 			}
 		}
 	}
 }