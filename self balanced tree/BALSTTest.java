//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            BALSTTest
// Files:            BALSTTest.java
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
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;


//@SuppressWarnings("rawtypes")
/**
 * This is the Test class used to test the functions of my BALST class
 * @author yijuncheng
 * @param <K> type of key
 * @param <V> type of value
 */
public class BALSTTest {
	//two fields variable of BALST
    BALST<String,String> balst1;	
    BALST<Integer,String> balst2;

    /**
     * setup method
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        balst1 = createInstance();
        balst2 = createInstance2();
    }

    /**
     * teardown method
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
        balst1 = null;
        balst2 = null;
    }
    
    /**
     * create a instance of BALST
     * @return A instance of BALST
     */
    protected BALST<String, String> createInstance() {
        return new BALST<String,String>();
    }

    /**
     * create a instance of BALST
     * @return A instance of BALST
     */
    protected BALST<Integer,String> createInstance2() {
        return new BALST<Integer,String>();
    }

    /** 
     * Insert three values in sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred.
     */
    @Test
    void testBALST_001_insert_sorted_order_simple() {
        try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("avl insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfRightChildOf(10).equals(20)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(30, "30");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
            
            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }

    /** 
     * Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_002_insert_reversed_sorted_order_simple() {
    	try {
            balst2.insert(30, "30");
            if (!balst2.getKeyAtRoot().equals(30)) 
                fail("avl insert at root does not work");
            
            balst2.insert(20, "20");
            if (!balst2.getKeyOfLeftChildOf(30).equals(20)) 
                fail("avl insert to left child of root does not work");
            
            balst2.insert(10, "10");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
            
            System.out.println();
            System.out.println("test002");
            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+ e.getMessage() );
        }
        
    }

    /** 
     * Insert three values so that a right-left rotation is
     * needed to fix the balance.
     * 
     * Example: 10-30-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_003_insert_smallest_largest_middle_order_simple() {
    	try {
            balst2.insert(10, "10");
            if (!balst2.getKeyAtRoot().equals(10)) 
                fail("avl insert at root does not work");
            
            balst2.insert(30, "30");
            if (!balst2.getKeyOfRightChildOf(10).equals(30)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(20, "20");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate (right-left) does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
            
            System.out.println();
            System.out.println("test003");
            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+ e.getMessage() );
        }
    }

    /** 
     * Insert three values so that a left-right rotation is
     * needed to fix the balance.
     * 
     * Example: 30-10-20
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testBALST_004_insert_largest_smallest_middle_order_simple() {
    	try {
            balst2.insert(30, "30");
            if (!balst2.getKeyAtRoot().equals(30)) 
                fail("avl insert at root does not work");
            
            balst2.insert(10, "10");
            if (!balst2.getKeyOfLeftChildOf(30).equals(10)) 
                fail("avl insert to right child of root does not work");
            
            balst2.insert(20, "20");
            Integer k = balst2.getKeyAtRoot();
            if (!k.equals(20)) 
                fail("avl rotate (left-right) does not work");
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
            
            System.out.println();
            System.out.println("test004");
            balst2.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+ e.getMessage() );
        }
    }
    
    /**
     * This test insert more nodes, (5 nodes) and test if every nodes are
     * on the appropriate location.
     */
    @Test
    void testBALST_005_insert_make_larger_tree_if_balance_01() {
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.insert(30, "40");
            balst2.insert(40, "40");
            balst2.insert(35, "40");
            balst2.insert(50, "40");
            //after insertion, root should be 35, left child of root 
            //should be 20, then left child of 20 should be 10, right 
            //child of 20 should be 30, right child of root is 40, right
            //child of 40 is 50. then check all of these below
            Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(35));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(35),new Integer(20));
            Assert.assertEquals(balst2.getKeyOfLeftChildOf(20),new Integer(10));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(20),new Integer(30));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(35),new Integer(40));
            Assert.assertEquals(balst2.getKeyOfRightChildOf(40),new Integer(50));
            System.out.println();
            System.out.println("test005");
            balst2.print();
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * this test method insert 5 nodes first and remove two of them.
     * there should not be any exceptions thrown
     * test if the number of keys are correct
     */
    @Test
    void testBALST_006_insert_then_remove_check_numKeys_and_height() {
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.insert(30, "40");
            balst2.insert(40, "40");
            balst2.insert(35, "40");
            balst2.insert(50, "40");
            //record the number of keys and tree height right after insertion
            int sizeBeforRemove = balst2.numKeys();
            int heightBeforeRemove = balst2.getHeight();
            //remove two of the nodes
            balst2.remove(20);
            balst2.remove(35);
            //record the number of keys and height now
            int heightAfterRemove = balst2.getHeight();
            int sizeAfterRemove = balst2.numKeys();
            //check the number of keys and height 
            if(sizeBeforRemove != 6 || sizeAfterRemove != 4 || 
            		heightBeforeRemove != 3 || heightAfterRemove != 3) {
            	fail("incorrect number of keys for the tree");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * insert a duplicate key and test if there is a duplicate
     * key exception thrown
     */
    @Test
    void testBALST_007_insert_dupicate_key_check_exception() {
    	try {//insertion
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.insert(10, "30");
            fail("should throw dupicate key exception, but no");
        }catch(DuplicateKeyException e) {
        	//should catch duplicateKeyException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * insert a null key and test if there is a duplicate
     * key exception thrown
     */
    @Test
    void testBALST_008_insert_null_key_check_exception() {
    	try {//insert
            balst2.insert(null, "30");
            balst2.insert(20, "20");
            balst2.insert(10, "30");
            fail("should throw null key exception, but no");
        }catch(IllegalNullKeyException e) {
        	//should catch IllegalNullKeyException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * insert some nodes first and remove a null key test 
     * if there is a null key exception thrown
     */
    @Test
    void testBALST_009_remove_null_key_check_exception() {
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.remove(null);
            fail("should throw null key exception, but no");
        }catch(IllegalNullKeyException e) {
        	//should catch IllegalNullKeyException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * insert some nodes first and remove a key that does not exist and test 
     * if there is a key not found exception thrown
     */
    @Test
    void testBALST_0010_remove_unexisted_key_check_exception() {
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.remove(30);
            fail("should throw key not found exception, but no");
        }catch(KeyNotFoundException e) {
        	//should catch KeyNotFoundException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * insert some nodes first and get a null key test 
     * if there is a null key exception thrown
     */
    @Test
    void testBALST_0011_get_null_key_check_exception() {
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.get(null);
            fail("should throw null key exception, but no");
        }catch(IllegalNullKeyException e) {
        	//should catch IllegalNullKeyException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * get a key that is not exist and test if there is a key not found
     * exception thrown
     */
    @Test
    void testBALST_0012_get_unexisted_key_check_exception() {
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.get(30);
            fail("should throw key not found exception, but no");
        }catch(KeyNotFoundException e) {
        	//should catch KeyNotFoundException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * test a contains method test if when we call a contains method with
     * a null key it will throw a null key exception
     */
    @Test
    void testBALST_0013_contains_null_key_check_exception() {
    	try {//inseet
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.contains(null);
            fail("should throw null key exception, but no");
        }catch(IllegalNullKeyException e) {
        	//should catch IllegalNullKeyException
        }catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    }
    
    /**
     * insert many nodes (as large as 50 nodes)
     * and test the root to see if the root is expected after balanced
     */
    @Test
    void testBALST_0014_insert_50_nodes() {
    	try {//insert 50 nodes
    		for(int i = 1; i < 51; i++) {
    			balst2.insert(i, "value");
    		}
    	}catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    	if(balst2.getKeyAtRoot() != 32) {
    		fail("incorrect balancing of the tree");
    	}
    	if(balst2.getHeight() != 6) {
    		fail("incorrect height of the tree");
    	}
    }
    
    /**
     * test the in-order traversal method, check every node in the list
     */
    @Test
    void testBALST_0015_inorder_traversal() {
    	ArrayList<Integer> nodes = new ArrayList<Integer>();
    	try {//insert
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.insert(30, "40");
            balst2.insert(40, "40");
            balst2.insert(35, "40");
            balst2.insert(50, "40");
            //record the nodes into a list
            nodes = (ArrayList<Integer>) balst2.getInOrderTraversal();
            //check if each element in the list is correct
            if(nodes.get(0) != 10 || nodes.get(1) != 20 
            		|| nodes.get(2) != 30 || nodes.get(3) != 35 
            		|| nodes.get(4) != 40 || nodes.get(5) != 50) {
            	fail("incorrect inorder traversal");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+e.getMessage() );
        }
    	//balst2.print();
    }
    
    /**
     * test the pre-order traversal method, check every node in the list
     */
    @Test
    void testBALST_0016_preorder_traversal() {
    	ArrayList<Integer> nodes = new ArrayList<Integer>();
    	try {
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.insert(30, "40");
            balst2.insert(40, "40");
            balst2.insert(35, "40");
            balst2.insert(50, "40");
            //store the nodes into a list
            nodes = (ArrayList<Integer>) balst2.getPreOrderTraversal();
            //check if each element in the list is correct
            if(nodes.get(0) != 35 || nodes.get(1) != 20 
            		|| nodes.get(2) != 10 || nodes.get(3) != 30
            		|| nodes.get(4) != 40 || nodes.get(5) != 50) {
            	fail("incorrect inorder traversal");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+ e.getMessage() );
        }
    }
    
    /**
     * test the post-order traversal method, check every node in the list
     */
    @Test
    void testBALST_0017_postorder_traversal() {
    	ArrayList<Integer> nodes = new ArrayList<Integer>();
    	try {
            balst2.insert(10, "30");
            balst2.insert(20, "20");
            balst2.insert(30, "40");
            balst2.insert(40, "40");
            balst2.insert(35, "40");
            balst2.insert(50, "40");
            nodes = (ArrayList<Integer>) balst2.getPostOrderTraversal();
            if(nodes.get(0) != 10 || nodes.get(1) != 30 
            		|| nodes.get(2) != 20 || nodes.get(3) != 50
            		|| nodes.get(4) != 40 || nodes.get(5) != 35) {
            	fail("incorrect inorder traversal");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception AVL 000: "+ e.getMessage() );
        }
    }
    
    /**
     * insert 50 nodes first and remove all of them. test if the number of 
     * keys is correct
     */
	@Test
	void testBALST_0018_insert50_then_remove() {
		try {
			for (int i = 1; i < 51; i++) {//insert 50 nodes
				balst2.insert(i, "value");
			}
			if (balst2.numKeys() != 50) {//check the numKeys
				fail("incorrect number of keys");
			}
			for (int j = 1; j < 51; j++) {//remove all of the 50 nodes
				balst2.remove(j);
			}
			if (balst2.numKeys() != 0) {//check the numKeys
				fail("incorrect number of keys after remove");
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception AVL 000: " + e.getMessage());
		}
	}
    
    // Add tests to make sure that rebalancing occurs even if the 
    // tree is larger.   Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work?  Does the tree maintain balance when a key is deleted?

}
