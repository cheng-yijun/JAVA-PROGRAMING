
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            BALST
// Files:            BALST.java
// Semester:         Fall 2019
//
// Author:           Yijun Cheng 
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc 
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: In this program, I am required to create my own data
// structure and have all of the implementation that dataStructureADT has.
// And then write as many tests as I can to test the function of my own
// data structure and use my tests to test the source code from TA to see
// if their code has some problems.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black. Note
 * which tree you implement here and as a comment when you submit.
 *
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

	private BSTNode<K, V> root;// tree root

	private int numKeys;// size of the tree (number of nodes)

	public BALST() {// constructor of BALST
		// initialize the fields
		root = null;
		numKeys = 0;
	}

	/**
	 * get the key of the tree root
	 * 
	 * @return key of the root
	 */
	@Override
	public K getKeyAtRoot() {
		if (root == null) {
			return null;
		}
		return root.key;
	}

	/**
	 * This is a private helper method used to help find the target node in the
	 * tree.
	 * 
	 * @param node to see if this is the target node with the key
	 * @param key  the key we want to find
	 * @return a node the contains the target key
	 * @throws IllegalNullKeyException key is null, invalid
	 * @throws KeyNotFoundException    cannot find this key in the tree
	 */
	private BSTNode<K, V> lookup(BSTNode<K, V> node, K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {// illegal argument
			throw new IllegalNullKeyException();
		}
		if (node == null) {// no such key in the tree
			throw new KeyNotFoundException();
		}
		if (key.compareTo(node.key) == 0) {// find the key
			return node;
		} else if (key.compareTo(node.key) < 0) {// recurse in the left subtree
			return lookup(node.left, key);
		} else {// recurse in the right subtree
			return lookup(node.right, key);
		}
	}

	/**
	 * get the key of the left child of the target key use lookup method to help
	 * find the target key
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		BSTNode<K, V> node = lookup(root, key);
		// if node not found, throw KeyNotFoundException
		if (node.left == null) {
			return null;
		}
		return node.left.key;
	}

	/**
	 * get the key of the right child of the target key use lookup method to help
	 * find the target key
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		BSTNode<K, V> node = lookup(root, key);
		// if node not found, throw KeyNotFoundException
		if (node.right == null) {
			return null;
		}
		return node.right.key;
	}

	/**
	 * get the height of the tree
	 * 
	 * @return the height of root
	 */
	@Override
	public int getHeight() {
		return root.height;
	}

	/**
	 * This is a private helper method used to help find the height of each node
	 * 
	 * @param node the height of this node is expected
	 * @return a integer of the height of the node
	 */
	private int NodeHeight(BSTNode<K, V> node) {
		if (node == null)// empty tree
			return 0;
		return node.height;
	}

	/**
	 * store the nodes into a list with the in-order
	 * 
	 * @return the height of root
	 */
	@Override
	public List<K> getInOrderTraversal() {
		List<K> nodes = new ArrayList<K>();// create a new list
		nodes = getInOrderTraversal(root);// recurse to store
		return nodes;
	}

	/**
	 * the private helper method used to help recurse to store nodes into a list
	 * with the in-order recursive
	 * 
	 * @param node the node to be stored
	 * @return a list contains all of the nodes in the tree
	 */
	private List<K> getInOrderTraversal(BSTNode<K, V> node) {
		List<K> nodes = new ArrayList<K>();// create a new list
		if (node == null) {// base case
			return nodes;
		}
		// recurse to store
		nodes.addAll((Collection<? extends K>) getInOrderTraversal(node.left));
		nodes.add(node.key);
		nodes.addAll((Collection<? extends K>) getInOrderTraversal(node.right));
		return nodes;
	}

	/**
	 * store the nodes into a list with the pre-order
	 * 
	 * @return the height of root
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		List<K> nodes = new ArrayList<K>();// create a new list
		nodes = getPreOrderTraversal(root);// recurse to store
		return nodes;
	}

	/**
	 * the private helper method used to help recurse to store nodes into a list
	 * with the pre-order recursive
	 * 
	 * @param node the node to be stored
	 * @return a list contains all of the nodes in the tree
	 */
	public List<K> getPreOrderTraversal(BSTNode<K, V> node) {
		List<K> nodes = new ArrayList<K>();// create a new list
		if (node == null) {// base case
			return nodes;
		}
		// recurse to store
		nodes.add(node.key);
		nodes.addAll((Collection< K>) getPreOrderTraversal(node.left));
		nodes.addAll((Collection<? extends K>) getPreOrderTraversal(node.right));
		return nodes;
	}

	/**
	 * store the nodes into a list with the post-order
	 * 
	 * @return the height of root
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		List<K> nodes = new ArrayList<K>();// create a new list
		nodes = getPostOrderTraversal(root);// recurse to store
		return nodes;
	}

	/**
	 * the private helper method used to help recurse to store nodes into a list
	 * with the pre-order recursive
	 * 
	 * @param node the node to be stored
	 * @return a list contains all of the nodes in the tree
	 */
	@SuppressWarnings("unchecked")
	public List<K> getPostOrderTraversal(BSTNode<K, V> node) {
		List<K> nodes = new ArrayList<K>();// create a new list
		if (node == null) {// base case
			return nodes;
		}
		// recurse to store
		nodes.addAll((Collection<? extends K>) getPostOrderTraversal(node.left));
		nodes.addAll((Collection<? extends K>) getPostOrderTraversal(node.right));
		nodes.add(node.key);
		return nodes;
	}

	/**
	 * the private helper method used to help recurse to store nodes into a list
	 * with the level-order recursive
	 * 
	 * @param node the node to be stored
	 * @return a list contains all of the nodes in the tree
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		// create a list o store the nodes in level order
		List<K> keys = new ArrayList<K>();
		if (root == null) {// empty tree
			return keys;
		}
		// create two list. one is used to store the current nodes in level
		// another is used to store the temporary nodes in the next level
		List<BSTNode<K, V>> current = new LinkedList<BSTNode<K, V>>();
		List<BSTNode<K, V>> children = new LinkedList<BSTNode<K, V>>();
		current.add(root);// add the first node into the list
		// all nodes in this level is stored, terminate loop
		while (!current.isEmpty()) {
			BSTNode<K, V> node = current.remove(0);
			// remove this node an recurse its children
			if (node.left != null) {
				children.add(node.left);
			}
			if (node.right != null) {
				children.add(node.right);
			}
			keys.add(node.key);// add the key into the list
			if (current.isEmpty()) {// move to next level
				current = children;
				children = new LinkedList<BSTNode<K, V>>();
			}
		}
		return keys;
	}

	/**
	 * This is a private helper method to calculate the balance factor for the use
	 * of insertion and removing methods
	 * 
	 * @param node the balance factor of this node to be count
	 * @return a integer variable represent the balance factor
	 */
	private int getBalanceFactor(BSTNode<K, V> node) {
		if (node == null)
			return 0;
		return NodeHeight(node.left) - NodeHeight(node.right);
	}

	/**
	 * This is a private method used to process the left rotate of node
	 * 
	 * @param node to be rotated left
	 * @return the node has been rotated left
	 */
	private BSTNode<K, V> rotateLeft(BSTNode<K, V> node) {
		BSTNode<K, V> tmp = node.right;// store the node's right child
		// store the node's right child's left child
		BSTNode<K, V> tmp2 = tmp.left;
		tmp.left = node;// replace tmp with the node
		node.right = tmp2;// link tmp2 to node's tight
		// re-calculate the height
		node.height = Math.max(NodeHeight(node.left), NodeHeight(node.right)) + 1;
		tmp.height = Math.max(NodeHeight(tmp.left), NodeHeight(tmp.right)) + 1;
		return tmp;
	}

	/**
	 * This is a private method used to process the right rotate of node
	 * 
	 * @param node to be rotated right
	 * @return the node has been rotated right
	 */
	private BSTNode<K, V> rotateRight(BSTNode<K, V> node) {
		BSTNode<K, V> tmpNode = node.left.right;// store the temporary node
		// re-link those nodes
		node.left.right = node;
		node = node.left;
		node.right.left = tmpNode;
		// re-calculate the height
		node.right.height = Math.max(NodeHeight(node.right.left), NodeHeight(node.right.right)) + 1;
		node.height = Math.max(NodeHeight(node.left), NodeHeight(node.right)) + 1;
		return node;
	}

	/**
	 * the insert method. insert a nodes containing a key into the tree
	 * 
	 * @throws IllegalNullKeyException key is null, invalid
	 * @throws DuplicateKeyException   if the key is already existed
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
//		class DS_My_Node<k extends Comparable<k>,v>{
//			//fields of the inner class
//			private k key;
//			private v value;
//			private DS_My_Node<k, v> next;//next node of current node
//			private DS_My_Node<k, v> previous;//previous node of current node
//			
//			/**
//			 * constructor of the inner class
//			 * @param key the key of node
//			 * @param value of the key node
//			 */
//			private DS_My_Node(k key, v value) {
//				this.key = key;
//				this.value = value;
//				next = null;
//				previous = null;
//			}
//		}
		if (key == null) {// invalid since the null key
			throw new IllegalNullKeyException();
		}
		if (contains(key)) {// check if the key is existed
			throw new DuplicateKeyException();
		}
		root = insertHelper(root, key, value);
	}

	/**
	 * private helper method to recurse to add new node into the tree and keep
	 * balance of the tree
	 * 
	 * @param node  node to be check
	 * @param key   key of the new node
	 * @param value value of the new node
	 * @return a node has been inserted
	 */
	private BSTNode<K, V> insertHelper(BSTNode<K, V> node, K key, V value) {
		if (node == null) {// base case, then add the new node
			numKeys++;
			BSTNode<K, V> newNode = new BSTNode<K, V>(key, value);
			return newNode;
		}
		if (key.compareTo(node.key) < 0) {// recurse in the left tree
			node.left = insertHelper(node.left, key, value);
		} else {// recurse in the right tree
			node.right = insertHelper(node.right, key, value);
		}
		// there will not be the node with the same key(checked)
		// update the height of the current node
		node.height = 1 + Math.max(NodeHeight(node.left), NodeHeight(node.right));
		// update the balance factor of current node
		int balanceFactor = getBalanceFactor(node);
		// rotate to balance the tree
		// right rotate
		if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
			return rotateRight(node);
		}
		// left rotate
		if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
			return rotateLeft(node);
		}
		// left rotate node's left child first, then rotate right node
		if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		// left rotate node's right child first, then rotate left node
		if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		return node;
	}

	/**
	 * this is a remove method to remove a node with the target key in the tree
	 * 
	 * @return true if the node is moved
	 * @throws IllegalNullKeyException key is null, invalid
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {// invalid argument
			throw new IllegalNullKeyException();
		}
		// loof up to find the key
		BSTNode<K, V> node = lookup(root, key);
		BSTNode<K, V> parentNode = root;// point to the tree root forst
		// find the parent node of the target node
		if (root.key == key && numKeys == 1) {
			root = null;// only one node in the tree
		} else {
			boolean foundParent = true;// true to continue loop
			// this loop is to find the parent node of the mode to be removed
			while (foundParent) {
				if (parentNode.key.compareTo(key) > 0) {// serch in the left-sub
					if (parentNode.left.key.compareTo(key) == 0) {
						foundParent = false;
					} else {
						parentNode = parentNode.left;
					} // in the right-sub
				} else if (parentNode.key.compareTo(key) < 0) {
					if (parentNode.right.key.compareTo(key) == 0) {
						foundParent = false;
					} else {
						parentNode = parentNode.right;
					}
				} else {// the node to be removed is root node
					parentNode = null;
					foundParent = false;
				}
			}
		}
		if (parentNode != null) {// the node is not the root node
			if (node.left == null && node.right == null) {// no child case
				if (parentNode.left == node) {
					parentNode.left = null;
				} else {
					parentNode.right = null;
				}
			} else if (node.left == null && node.right != null) {
				// only one child case(right child)
				if (parentNode.left == node) {
					parentNode.left = node.right;
				} else {
					parentNode.right = node.right;
				}
			} else if (node.left != null && node.right == null) {
				// only one child case, only left child
				if (parentNode.left == node) {
					parentNode.left = node.left;
				} else {
					parentNode.right = node.left;
				}
			}
			// delete with inorder successor
			else {// node has both left and right children
				BSTNode<K, V> child = node.right;
				while (child.left != null) {
					child = child.left;
				} // find the in-order success
					// record the key
				K newKey = child.key;
				V newValue = child.value;
				remove(child.key);// remove this replaced key-node
				node.key = newKey;// replace key and value
				node.value = newValue;
				return true;
			}
		} else {// root node to be removed
			if (root.right != null) {// root has right sub tree
				BSTNode<K, V> child = node.right;
				while (child.left != null) {// find the in-order success
					child = child.left;
				}
				// replace
				K newKey = child.key;
				V newValue = child.value;
				remove(child.key);
				root.key = newKey;
				root.value = newValue;
				return true;
			} else {// root has left sub tree
				BSTNode<K, V> child = node.left;
				while (child.right != null) {// find the in-order predecessor
					child = child.right;
				}
				// replace
				K newKey = child.key;
				V newValue = child.value;
				remove(child.key);
				root.key = newKey;
				root.value = newValue;
				return true;
			}
		}
		numKeys--;
		return true;
	}

	/**
	 * this get method is used to get the key that we want if it exist
	 * 
	 * @throws IllegalNullKeyException key is null, invalid
	 * @throws KeyNotFoundException    if the key is not found
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (contains(key))// check if the key is exist first
			return lookup(root, key).value;// recurse to find the key
		else// cannot find the key
			throw new KeyNotFoundException();
	}

	/**
	 * this method is used to check if the key is in the tree
	 * 
	 * @throws IllegalNullKeyException key is null, invalid
	 * @return true if find the key
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		if (key == null) {// invalid
			throw new IllegalNullKeyException();
		}
		try {// find the key
			lookup(root, key);
		} catch (KeyNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * a integer of the number of the keys in the tree
	 * 
	 * @return a integer of the number of the keys in the tree
	 */
	@Override
	public int numKeys() {
		return numKeys;
	}

	/**
	 * print the tree in the shape of a tree
	 */
	@Override
	public void print() {
		int space = 0;
		// call the recursive function
		printHelper(root, space);
	}

	/**
	 * private helper method is used to print the tree by recursive way
	 * 
	 * @param node  current node to be print
	 * @param space the space between nodes
	 */
	private void printHelper(BSTNode<K, V> node, int space) {
		// Base case
		if (node == null)
			return;
		// Increase distance between levels
		space = space + 7;
		// recurse right child first
		printHelper(node.right, space);

		// Print current node after space
		// count

		for (int i = 7; i < space; i++) {
			if (i % 7 == 0) {
				System.out.print("|");
			} else if (space - i < 7) {
				System.out.print("-");
			} else {
				System.out.print(" ");
			}
		}
		System.out.print(node.key + "\n");
		// recurse left child
		printHelper(node.left, space);
	}
}
