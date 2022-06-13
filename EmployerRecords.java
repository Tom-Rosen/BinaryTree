///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Tom Rosen
// Campus ID: 908 190 4204
// WiscEmail: trrosen@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// Complete the missing (blank) sections of code in this EmployerRecords class using a binary search
// tree to store their contact information (Customer objects).
// Note that the comparison of the Customer objects is based on the lexicographic ordering of
// the names (case sensitive comparison of the names).
// This means that this employer records BST is ordered lexicographically with respect to the names
// of its entries. The toString.compareTo() compares strings in that lexicographic order.
// Any exceptions thrown do not need messages.
// You are NOT allowed to add any instance field not already defined in the starting code of the
// PhoneBook class.
// You are NOT allowed to make any change to the signatures of the provided methods.

/**
 * This class represents a BST of Customer objects. The search key in this BST
 * is the name of the entries.
 */
public class EmployerRecords {
	private BSTNode<Employer> root; // see exam reference section listing for BSTNode<T>

	/**
	 * Checks whether this BST is empty
	 * 
	 * @return true if this BST is empty and false otherwise
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false; // TODO CHANGE THIS: included to avoid compiler errors
	}

	// MAKE SURE TO SAVE your source file before uploading it to gradescope.
	/**
	 * Recursive helper method to insert a new entry into this EmployerRecords
	 * 
	 * @param employer the employer being inserted
	 * @param current  root of the subtree that employer is being inserted into
	 * @return the new root of this subtree after employer is inserted
	 * @throws IllegalStateException when this CustomerRecords contains a duplicate
	 *                               entry
	 */
	protected static BSTNode<Employer> insertHelper(Employer employer, BSTNode<Employer> current) {
		// DO NOT CHECK whether employer is null or not. The method will automatically
		// throw
		// a NullPointerException if this case occurs.

		// TODO complete the implementation of this method

		// base case1: the current subtree is empty (current is null) --> add employer,
		// and
		// return the new root (current) of this subtree
		
		BSTNode<Employer> node = new BSTNode<Employer>(employer);
		if (current == null) {
			current = node;
			return current;
		}
		

		// base case2: subtree contains a match with employer
		if (current.getData().compareTo(employer) == 0) {
			throw new IllegalStateException("Adding match to BST");
		}
		

			// recursive case: recurse left
			// try to insert employer to the left subtree
			// Hint: node.setLeft(/* recursive call */);
			if (employer.compareTo(current.getData()) < 0) {
				if (current.getLeft() == null) {
					current.setLeft(new BSTNode<Employer>(employer));
					return current;
				} else {
					insertHelper(employer, current.getLeft());
				}
			} else {
				if (current.getRight() == null) {
					current.setRight(new BSTNode<Employer>(employer));
					return current;
				} else {
					insertHelper(employer, current.getRight());
				}
			}
		
		// else recursive case: recurse right
		// try to insert employer to the right subtree
		// Hint: node.setRight(/* recursive call */);

		 return current; // return the root of this subtree
	}

	/**
	 * Inserts a new employer entry into this EmployerRecords.
	 * 
	 * @param employer is the data being inserted into this employer records
	 * @throws IllegalStateException when this employer records already contains a
	 *                               duplicate entry (a name matching entry).
	 */
	public void insert(Employer employer) {
		root = insertHelper(employer, root);	
	}

	/**
	 * Returns the number of employer objects stored in this employer records
	 * 
	 * @return the size of this BST
	 */
	public int size() {
		return sizeHelper(root);
	}

	/**
	 * Recursively computes and returns the size of the BST rooted at current
	 * 
	 * @param current root of the subtree whose size is being computed
	 * @return the size of the subtree rooted at current
	 */
	protected static int sizeHelper(BSTNode<Employer> current) {
		// TODO Complete this method
		int leftSize = 0;
		int rightSize = 0;
		if(current == null)
		{
			return 0;
		} 
		else
		{
			if(current.getLeft() != null)
			{
				leftSize = sizeHelper(current.getLeft());
			}
			if(current.getRight() != null)
			{
				rightSize = sizeHelper(current.getRight());
			}
			return (1 + rightSize + leftSize);
		}
	}

	// MAKE SURE TO SAVE your source file before uploading it to gradescope.

	/**
	 * Searches for the phone number associated with a particular name in this
	 * employer records.
	 * 
	 * @param name of the employer to search for their phone number
	 * @return The contact information "(phoneNumber)address" found associated with
	 *         that name
	 * @throws IllegalArgumentException when no entry in this employer records has
	 *                                  that name
	 */
	public String search(String name) {
		return searchHelper(name, root);
	}

	/**
	 * Recursively searches for the contact information associated with an employer
	 * name the BST rooted at current
	 * 
	 * @param name    of the employer to search for the phone number of
	 * @param current root of the subtree that is being searched
	 * @return contact information in the format "(phoneNumber)address" of an
	 *         employer given their name
	 * @throws IllegalArgumentException when the specified name is NOT found in the
	 *                                  BST rooted at current
	 */
	protected static String searchHelper(String name, BSTNode<Employer> current) {
		// TODO Complete this method
		if(current == null)
		{
			throw new IllegalArgumentException("Not in tree");
		}
		if(current.getData().getName().compareTo(name) == 0)
		{
			return "(" + current.getData().getPhoneNumber() + ")" + current.getData().getAddress();
		}
		if(name.compareTo(current.getData().getName()) < 0)
		{
			return searchHelper(name, current.getLeft());
		}
		else
		{
			return searchHelper(name, current.getRight());
		}
	}

	// MAKE SURE TO SAVE your source file before uploading it to gradescope.

	/**
	 * Checks the correctness of insert(), search(), and size() methods
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testInsertSearchSize() {
		Employer e1 = new Employer("Mouna", "123", "Middleton");
		Employer e2 = new Employer("Hobbes", "456", "Madison");
		Employer e3 = new Employer("Sulong", "789", "Sun Prairie");
		Employer e4 = new Employer("Michelle", "102", "Madison");
		Employer e5 = new Employer("Aisha", "963", "Fitchburg");
		EmployerRecords employers = new EmployerRecords();
		try {
			employers.insert(e1);
			if (employers.size() != 1 || !employers.search("Mouna").equals("(123)Middleton")) {
				System.out.println("e1");
				return false;
			}

			employers.insert(e2);
			if (employers.size() != 2 || !employers.search("Hobbes").equals("(456)Madison")) {
				System.out.println(employers.size());
				System.out.println("e2");
				return false;
			}

			employers.insert(e3);
			if (employers.size() != 3 || !employers.search("Sulong").equals("(789)Sun Prairie")) {
				System.out.println("e3");
				return false;
			}
			
			employers.insert(e4);
			if (employers.size() != 4) {
				System.out.println("e4");
				System.out.println(employers.size());
				return false;
			}
			

			employers.insert(e5);
			if (employers.size() != 5 || !employers.search("Michelle").equals("(102)Madison")
					|| !employers.search("Aisha").equals("(963)Fitchburg")) {
				System.out.println("e5");
				return false;
			}
			
			try {
				employers.search("Juan");
				return false;
			} catch (IllegalArgumentException e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Main method
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println("testInsertSearchSize(): " + testInsertSearchSize());
	}
}
