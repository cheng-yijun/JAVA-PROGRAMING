//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            BookHashTable
// Files:            BookHashTable.java
// Semester:         Fall 2019
//
// Author:           Yijun Cheng 
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc 
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: This is a program that implements the function of
// hash table which includes insert, remove, get, getnumKeys, getCapacity...
// this is an CHAINED BUCKET hash table which implements the array list
// of array list. the average complexity should be O(1)
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//
// explain your hashing algorithm here: I use the CHAINED BUCKET hash 
// table which implements the array list of array list. 
// the average complexity should be O(1). use the computed index to find the
// chain and continue looking up the value in that chain.
//
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object

/** 
 * @author yijuncheng
 * HashTable implementation that uses:
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 */
public class BookHashTable implements HashTableADT<String, Book> {
	
	/**
	 * This is the inner class containing the node of a key, value pair
	 * @author yijuncheng
	 *
	 * @param <String> the String type of key
	 * @param <Book> the Book type of book
	 */
	private class bookNode<String, Book> {
		//inner class fields
		String key;
		Book book;

		/**
		 * a constructor for this inner class
		 * @param key key of a value
		 * @param book value
		 */
		private bookNode(String key, Book book) {
			//initialize the inner class fields
			this.key = key;
			this.book = book;
		}
	}
	
	//the hash structure
	private ArrayList<ArrayList<bookNode<String, Book>>> bookTable;
	private int capacity;//capacity of the hash table, can change
	private double threshold;//threshold of the hash table
	private int numKey;//number of all keys(book objects)
	//allBooks is the ArrayList containing all the book objects that
    //used for the reorganize of a hash table when resize.

	
    /** The initial capacity that is used if none is specified user */
    static final int DEFAULT_CAPACITY = 101;
    
    /** The load factor that is used if none is specified by user */
    static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * REQUIRED default no-arg constructor
     * Uses default capacity and sets load factor threshold 
     * for the newly created hash table.
     */
    public BookHashTable() {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    
    /**
     * Creates an empty hash table with the specified capacity 
     * and load factor.
     * @param initialCapacity number of elements table should hold at start.
     * @param loadFactorThreshold the ratio of items/capacity that causes
     *  table to resize and rehash
     */
	public BookHashTable(int initialCapacity, double loadFactorThreshold) {
		// initialize fields
		this.capacity = initialCapacity;
		this.threshold = loadFactorThreshold;
		this.bookTable = 
				new ArrayList<ArrayList<bookNode<String, Book>>>(capacity);
		for (int i = 0; i < capacity; i++) {
			bookTable.add(null);
		}
		this.numKey = 0;
		// and load factor threshold and initializes all fields
	}

    /**
     * transfer the key into a more appropriate index integer
     * @param key the key to be operate in hash function
     * @return a index in hash table represent the key
     */
	private int hashFunction(String key) {
		int hashCode = key.hashCode();// call hashCode first
		int hashIndex = Math.abs(hashCode % capacity);// get a positive index
		return hashIndex;
	}
    
    /**
     * This is a private method used to reorganize the hash table(Rehashing)
     * And make the capacity larger
     */
	private void reHash()  {
		//need to rehash this hash table
		int oldCapacity = capacity;//record the capacity before increased
		capacity = 2 * capacity + 1;//make the capacity larger
		//create a temporary hash table to store all values from old hash table
		ArrayList<ArrayList<bookNode<String, Book>>> newTable = 
				new ArrayList<ArrayList<bookNode<String, Book>>>(capacity);
		for (int i = 0; i < capacity; i++) {
			newTable.add(null);//initialize all element to null
		}
		for(int i = 0; i < oldCapacity; i ++) {
			if (bookTable.get(i) != null) {// has chain buckets
				//iterate all values in the chain bucket
				for (int j = 0; j < bookTable.get(i).size(); j++) {
					int hashIndex = hashFunction(bookTable.get(i).get(j).key);
					//create temporary node 
					bookNode<String, Book> node = new bookNode<String, Book>(
							bookTable.get(i).get(j).key,
							bookTable.get(i).get(j).book);
					if (newTable.get(hashIndex) == null) {// empty chain bucket
						ArrayList<bookNode<String, Book>> bucket = 
								new ArrayList<bookNode<String, Book>>();
						bucket.add(node);
						newTable.set(hashIndex, bucket);
					}else {
						newTable.get(hashIndex).add(node);
					}
				}
			}
		}
		bookTable = newTable;
	}
	
	/**
	 * Add the key,value pair to the data structure and increase the number of 
	 * keys.
	 * @param key the key of the value to be removed
	 * @param value the book value
	 * @throws IllegalNullKeyException If key is null;
	 * @throws DuplicateKeyException   If key is already in data structure.
	 */
	@Override
	public void insert(String key, Book value) 
			throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) { // key should not be null
			throw new IllegalNullKeyException();
		}
		double loadFactor = (double) numKey / capacity;
		if (loadFactor >= getLoadFactorThreshold()) {
			//need to rehash this hash table
			reHash();
		}
		//create a new node to be added
		bookNode<String, Book> node = new bookNode<String, Book>(key, value);
		int hashIndex = hashFunction(key);//compute hash index
		if (bookTable.get(hashIndex) == null) {//empty chain bucket
			//create a chain bucket 
			ArrayList<bookNode<String, Book>> bucket = 
					new ArrayList<bookNode<String, Book>>();
			bucket.add(node);//add node into the temporary chain bucket
			bookTable.set(hashIndex, bucket);//add chain bucket to table
			numKey++;//increase number of keys
		}else {//existed chain bucket
			for (int i = 0; i < bookTable.get(hashIndex).size(); i++) {
				if (bookTable.get(hashIndex).get(i).key.equals(key)) {
					//same key found
					throw new DuplicateKeyException();
				}
			}
			bookTable.get(hashIndex).add(node);//add node into chain bucket
			numKey++;//increase number of keys
		}
	}

	/**
	 * If key is found, 
     * remove the key,value pair from the data structure
     * decrease number of keys.
     * @param key the key of the value to be removed
     * @throws IllegalNullKeyException If key is null
     * @return If key is not found, return false, otherwise return true
	 */
	@Override
	public boolean remove(String key) throws IllegalNullKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		int hashIndex = hashFunction(key);//compute hash index
		if (bookTable.get(hashIndex) == null) {//empty chain bucket
			return false;
		}else {//iterate the chain bucket to search the key
			for (int i = 0; i < bookTable.get(hashIndex).size(); i++) {
				if (bookTable.get(hashIndex).get(i).key.equals(key)) {
					//find the key
					bookTable.get(hashIndex).remove(i);//remove the value
					if(bookTable.get(hashIndex).size() == 0) {
						bookTable.set(hashIndex,null);
					}
					numKey--;//decrease number of keys
					return true;
				}
			}
		}
		return false;
	}

	/**
     * Does not remove key or decrease number of keys
     * This method can get the value associated with the specified key.
     * compare the actual key of the value to look up this value in hash table
     * @param key the key of the value to be removed
     * @throws IllegalNullKeyException If key is null.
     * @throws KeyNotFoundException If key is not found.
     * @return the value associated with the specified key
	 */
	@Override
	public Book get(String key) 
			throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {// key is null, throw Exception
			throw new IllegalNullKeyException();
		}
		int hashIndex = hashFunction(key);//compute hash index
		if (bookTable.get(hashIndex) == null) {//empty chain bucket
			throw new KeyNotFoundException();
		}else {//existed chain bucket
			for(int i = 0; i < bookTable.get(hashIndex).size(); i++) {
				//iterate the chain to search
				if (bookTable.get(hashIndex).get(i).key.equals(key)) {
					//find the key
					return bookTable.get(hashIndex).get(i).book;
				}
			}
		}
		//cannot find the key after iteration
		throw new KeyNotFoundException();
	}



	/**
	 * The method can return the number of key,value pairs in this hash table
	 * @return the number of key,value pairs in the data structure
	 */
	@Override
	public int numKeys() {
		return numKey;//just return the numKey field
	}

	/**
	 * getter of the threshold of the load factor
	 * @return a double denoting the threshold of the load factor
	 */
	@Override
	public double getLoadFactorThreshold() {
		return threshold;
	}

	/**
	 * Capacity is the size of the hash table array
     * This method returns the current capacity.
     * The initial capacity must be a positive integer, 1 or greater
     * and is specified in the constructor.
     * REQUIRED: When the load factor is reached, 
     * the capacity must increase to: 2 * capacity + 1
     * Once increased, the capacity never decreases
     *
     * @return a integer that denote the capacity of the hash table
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Implement this ADT with one of the following collision resolution 
	 * strategies and implement this method to return an integer to 
	 * indicate which strategy. In this program, we choose option 4.
     * 1 OPEN ADDRESSING: linear probe
     * 2 OPEN ADDRESSING: quadratic probe
     * 3 OPEN ADDRESSING: double hashing
     * 4 CHAINED BUCKET: array list of array lists
     * 5 CHAINED BUCKET: array list of linked lists
     * 6 CHAINED BUCKET: array list of binary search trees
     * 7 CHAINED BUCKET: linked list of array lists
     * 8 CHAINED BUCKET: linked list of linked lists
     * 9 CHAINED BUCKET: linked list of of binary search trees
	 * @return the collision resolution scheme 
	 *     used for this hash table which is 4
	 */
	@Override
	public int getCollisionResolutionScheme() {
		return 4;// resolution scheme number
	}
}