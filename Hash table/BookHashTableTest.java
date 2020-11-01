//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            BookHashTableTest
// Files:            BookHashTableTest.java
// Semester:         Fall 2019
//
// Author:           Yijun Cheng
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: This is a test file used to test the functions
// of my BookHashTable class. There are totally 18 tests in this file.
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**

* Filename:   TestHashTableDeb.java
 * Project:    p3
 * Authors:    Debra Deppeler (deppeler@cs.wisc.edu)
 *
 * Semester:   Fall 2018
 * Course:     CS400
 *
 * Due Date:   before 10pm on 10/29
 * Version:    1.0
 *
 * Credits:    None so far
 *
 * Bugs:       no bugs
 */

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test HashTable class implementation to ensure that required
 * functionality works for all cases.
 */
public class BookHashTableTest {

    // Default name of books data file
    public static final String BOOKS = "books.csv";

    // Empty hash tables that can be used by tests
    static BookHashTable bookObject;
    static ArrayList<Book> bookTable;

    static final int INIT_CAPACITY = 2;
    static final double LOAD_FACTOR_THRESHOLD = 0.49;

    static Random RNG = new Random(0);
    // seeded to make results repeatable (deterministic)

    /** Create a large array of keys and matching values for use in any test */
    @BeforeAll
    public static void beforeClass() throws Exception{
        bookTable = BookParser.parse(BOOKS);
    }

    /** Initialize empty hash table to be used in each test */
    @BeforeEach
    public void setUp() throws Exception {
        // TODO: change HashTable for final solution
         bookObject = new BookHashTable(INIT_CAPACITY,LOAD_FACTOR_THRESHOLD);
    }

    /** Not much to do, just make sure that variables are reset     */
    @AfterEach
    public void tearDown() throws Exception {
        bookObject = null;
    }

    /**
     * This method is used to insert many book instance into an
     * ArrayList for the future use
     * @param bookTable an ArrayList stored all of the books in csv file
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    private void insertMany(ArrayList<Book> bookTable)
        throws IllegalNullKeyException, DuplicateKeyException {
        for (int i=0; i < bookTable.size(); i++ ) {
            bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
        }
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test000_collision_scheme() {
        if (bookObject == null)
        	fail("Gg");
    	int scheme = bookObject.getCollisionResolutionScheme();
        if (scheme < 1 || scheme > 9)
            fail("collision resolution must be indicated with 1-9");
    }


    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is empty upon initialization
     */
    @Test
    public void test000_IsEmpty() {
        //"size with 0 entries:"
        assertEquals(0, bookObject.numKeys());
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that a HashTable is not empty after adding one (key,book) pair
     * @throws DuplicateKeyException  when inserting a duplicate key
     * @throws IllegalNullKeyException when the key is invalid
     */
    @Test
    public void test001_IsNotEmpty()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
        String expected = ""+1;
        //"size with one entry:"
        assertEquals(expected, ""+bookObject.numKeys());
    }

    /** IMPLEMENTED AS EXAMPLE FOR YOU
    * Test if the hash table  will be resized after adding two (key,book) pairs
    * given the load factor is 0.49 and initial capacity to be 2.
    */
    @Test
    public void test002_Resize()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	int cap1 = bookObject.getCapacity();
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	int cap2 = bookObject.getCapacity();
        //"size with one entry:"
        assertTrue(cap2 > cap1 & cap1 ==2);
    }

    /**
     * This test tests that insert a key,book pairs into the hash table
     * and remove it, the number of keys should be 1 after insertion and
     * 0 after removing
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    @Test
    public void test003_insert_Remove_one()
    		throws IllegalNullKeyException, DuplicateKeyException{
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	//record the number of keys right after insertion
    	int numKey1 = bookObject.numKeys();
    	bookObject.remove(bookTable.get(0).getKey());
    	//record the number of keys right after insertion
    	int numKey2 = bookObject.numKeys();
    	//compare the expected value to the numKeys
    	assertEquals(1, numKey1);
    	assertEquals(0, numKey2);
    }

    /**
     * This test tests that insert two identical key,book pairs into
     * the hash table if there will be a duplication exception thrown
     * if no, then the test fails
     */
    @Test
    public void test004_insert_duplicate() {
    	try {
    		bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    		bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    		//if no exception thrown, the test fails
    		fail("should throw DuplicateKeyException, but do not");
    	}catch(DuplicateKeyException e) {
    		//expected exception found
    	}catch(Exception e) {//unexpected exception thrown
    		fail("unexpected exception");
    	}
    }

    /**
     * This test tests that remove a key,book pair that its key is not
     * in the hash table, check if remove method returns false
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    @Test
    public void test005_remove_unexisted()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	try{
    		//remove bookTable.get(1) since it is not in our hash table
    		boolean ifRemove = bookObject.remove(bookTable.get(1).getKey());
    		if(ifRemove) {//if the remove method returns true, test fails
    			fail("remove method should return false, but return true");
    		}
    	}catch(Exception e) {
    		fail("unexpected exception");
    	}
    }

    /**
     * This test tests that get an existed key,book pair correctly
     * and compare the gotten book with the original book to check
     * if get method works correctly
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    @Test
    public void test006_get_existed_book()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	//insert many key, book pairs
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	bookObject.insert(bookTable.get(1).getKey(),bookTable.get(1));
    	bookObject.insert(bookTable.get(5).getKey(),bookTable.get(5));
    	try{//get book and compare their keys
    		Book book1 = bookObject.get(bookTable.get(0).getKey());
    		Book book2 = bookObject.get(bookTable.get(1).getKey());
    		Book book3 = bookObject.get(bookTable.get(5).getKey());
    		assertEquals(book1.getKey(), bookTable.get(0).getKey());
    		assertEquals(book2.getKey(), bookTable.get(1).getKey());
    		assertEquals(book3.getKey(), bookTable.get(5).getKey());
    	}catch(Exception e) {
    		fail("unexpected exception");
    	}
    }

    /**
     * this test tests insertion of null key if there will be a
     * IllegalNullKeyException thrown
     */
    @Test
    public void test007_insert_null_key() {
    	try {
    		bookObject.insert(bookTable.get(55).getKey(),bookTable.get(55));
        	bookObject.insert(null,bookTable.get(1));
    	}catch(IllegalNullKeyException e) {
    		//expected exception
    	}catch(Exception e) {
    		fail("unexpected exception");
    	}
    }

    /**
     * this test tests when get a null key, check whether there will be a
     * IllegalNullKeyException thrown
     */
    @Test
    public void test008_get_null_key() {
    	try {
    		bookObject.insert(bookTable.get(55).getKey(),bookTable.get(55));
        	bookObject.get(null);
    	}catch(IllegalNullKeyException e) {
    		//expected exception
    	}catch(Exception e) {
    		fail("unexpected exception");
    	}
    }

    /**
     * this test tests that remove a null key, then check if there will be a
     * IllegalNullKeyException thrown
     */
    @Test
    public void test009_remove_null_key() {
    	try {
    		bookObject.insert(bookTable.get(55).getKey(),bookTable.get(55));
        	bookObject.remove(null);
    	}catch(IllegalNullKeyException e) {
    		//expected exception
    	}catch(Exception e) {
    		fail("unexpected exception");
    	}
    }

    /**
     * insert a large amount of key,book pairs into the hash table(500 pairs)
     * use random generator to generator random numbers for insertion.
     * the 500 keys are distinct, so after insertion there should be
     * 500 number of keys
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    @Test
    public void test010_insert_large()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	Random r = new Random();
    	int number = 0;//store random numbers
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        //get 500 distinct random numbers
        while(randoms.size() != 500) {
        	number = r.nextInt(8000);
        	if(!randoms.contains(number)) {//random numbers should be distinct
        		randoms.add(number);
        	}
        }
        for(int i = 0; i < 500; i++) {//insert 500 into hash table
        	bookObject.insert(bookTable.get(randoms.get(i)).getKey(),
        			bookTable.get(randoms.get(i)));
        }
        int numKey = bookObject.numKeys();//check number of keys
        assertEquals(numKey, 500);
    }

    /**
     * insert a large amount of key,book pairs into the hash table(500 pairs)
     * use random generator to generator random numbers for insertion.
     * the 500 keys are distinct, so after insertion there should be
     * 500 number of keys. Then loop to remove all of the keys. finally,
     * there will be empty hash table
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    @Test
    public void test011_insert_large_then_remove()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	Random r = new Random();
    	int number = 0;
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        //get 500 distinct random numbers
        while(randoms.size() != 500) {//store random numbers
        	number = r.nextInt(8000);
        	if(!randoms.contains(number)) {//random numbers should be distinct
        		randoms.add(number);
        	}
        }
        for(int i = 0; i < 500; i++) {//insert 500 into hash table
        	bookObject.insert(bookTable.get(randoms.get(i)).getKey(),
        			bookTable.get(randoms.get(i)));
        }
        int numKey = bookObject.numKeys();
        assertEquals(numKey, 500);//check number of keys
        for(int i = 0; i < 500; i++) {//remove 500
        	bookObject.remove(bookTable.get(randoms.get(i)).getKey());
        }
        numKey = bookObject.numKeys();//check if it is empty hash table
        assertEquals(numKey, 0);
    }

    /**
     * this test tests that after insertion 500 distinct keys into hash table,
     * then every keys can be gotten by get method
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     * @throws KeyNotFoundException when key is not found in hash table
     */
    @Test
    public void test012_insert_large_get() throws IllegalNullKeyException,
    		DuplicateKeyException, KeyNotFoundException {
    	Random r = new Random();
    	int number = 0;
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        //get 500 distinct random numbers
        while(randoms.size() != 500) {//store random numbers
        	number = r.nextInt(8000);
        	if(!randoms.contains(number)) {//random numbers should be distinct
        		randoms.add(number);
        	}
        }
        for(int i = 0; i < 500; i++) {//insert 500 into hash table
        	bookObject.insert(bookTable.get(randoms.get(i)).getKey(),
        			bookTable.get(randoms.get(i)));
        }
        int numKey = bookObject.numKeys();
        assertEquals(numKey, 500);//check number of keys
        ArrayList<String> keys = new ArrayList<String>();//store keys in hash
        for(int i = 0; i < 500; i++) {//get 500 keys from the hash table
        	//then store the 500 keys into the ArrayList keys
        	keys.add(bookObject.get
        			(bookTable.get(randoms.get(i)).getKey()).getKey());
        }
    }

    /**
     * insert two keys sharing one value of book. Then test if the newKey has
     * the same book with old book
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     * @throws KeyNotFoundException when key is not found in hash table
     */
    @Test
    public void test013_shared_value() throws IllegalNullKeyException,
    		DuplicateKeyException, KeyNotFoundException {
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	//create a new key randomly
    	String newKey = "123456654345.0";
    	//insert the same book with the new key
    	bookObject.insert(newKey, bookTable.get(0));
    	//compare the key
    	assertEquals(bookObject.get(newKey).getKey(), bookTable.get(0).getKey());
    }

    /**
     * insert two keys sharing one value of book. Then remove the book with the
     * old key,(new key and the book value left in the hash table). Then
     * use the new key to look up the book value and check if it has the
     * expected key.
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     * @throws KeyNotFoundException when key is not found in hash table
     */
    @Test
    public void test014_shared_value_remove() throws IllegalNullKeyException,
    		DuplicateKeyException, KeyNotFoundException {
    	//insert multiple keys firstly
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	bookObject.insert(bookTable.get(11).getKey(),bookTable.get(11));
    	bookObject.insert(bookTable.get(161).getKey(),bookTable.get(161));
    	bookObject.insert(bookTable.get(400).getKey(),bookTable.get(400));
    	//create a new key
    	String newKey = "123456654345.0";
    	//insert the value existed in hash with the new key
    	bookObject.insert(newKey, bookTable.get(161));
    	String key = bookTable.get(161).getKey();
    	bookObject.remove(key);//remove the book value with old key
    	//use new key to compare the key of the value
    	//since the new key is still in the hash
    	assertEquals(bookObject.get(newKey).getKey(), key);
    }

    /**
     * insert two keys sharing one value of book. Then remove the book with the
     * old key,(new key and the book value left in the hash table). Then, insert
     * that book value with its original key again. check the number of keys
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     * @throws KeyNotFoundException when key is not found in hash table
     */
    @Test
    public void test015_shared_value_remove_reinsert()
    		throws IllegalNullKeyException,
    		DuplicateKeyException, KeyNotFoundException {
    	//insert multiple keys firstly
    	bookObject.insert(bookTable.get(0).getKey(),bookTable.get(0));
    	bookObject.insert(bookTable.get(11).getKey(),bookTable.get(11));
    	bookObject.insert(bookTable.get(161).getKey(),bookTable.get(161));
    	bookObject.insert(bookTable.get(400).getKey(),bookTable.get(400));
    	//create a new key
    	String newKey = "123456654345.0";
    	//insert the value existed in hash with the new key
    	bookObject.insert(newKey, bookTable.get(161));
    	String key = bookTable.get(161).getKey();
    	bookObject.remove(key);//remove the book value with old key
    	//check if the number of key decrease by 1, which is 4
    	assertEquals(bookObject.numKeys(), 4);
    	try {//re-insert the value with its original key
    		bookObject.insert(key, bookTable.get(161));
    	}catch(Exception e) {
    		fail("unexpecte exception thrown as re-insert the key,value pair");
    	}
    	//then number of keys should increase to 5
    	assertEquals(bookObject.numKeys(), 5);
    }

    /**
     * this test call the insert many method and check it the hash table can
     * work well for the while books. The key of books in the csv file
     * are distinct, so there will not be any exception thrown
     * @throws IllegalNullKeyException when the key is invalid
     * @throws DuplicateKeyException when inserting a duplicate key
     */
    @Test
    public void test016_insert_many()
    		throws IllegalNullKeyException, DuplicateKeyException {
    	insertMany(bookTable);//call the insertMany method to insert keys
    	assertEquals(bookObject.numKeys(), bookTable.size());//check numKeys
    }

}
