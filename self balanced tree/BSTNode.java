//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            BALSTNode
// Files:            BALSTNode.java
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
// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// and make this an inner class in your tree implementation.
//
// package level access means that all classes in the package can access directly.
// and accessing the node fields from classes other than your balanced search 
// is bad design as it creates many more chances for bugs to be introduced and not
// caught.
//
// Classes that use this type:  <TODO, list which if any classes use this type>
/**
 * this is a node class
 * @author yijuncheng
 *
 * @param <K> type of key
 * @param <V> type of value
 */
class BSTNode<K,V> {
    
    K key;//key of the node
    V value;//value of the node
    BSTNode<K,V> left;//left child of the node
    BSTNode<K,V> right;//right child of the node
    int balanceFactor;//balance factor of the node
    int height;//height of then ode
    

    /**
     * @param key key of the node
     * @param value value of the node
     * @param leftChild left child of the node
     * @param rightChild right child of the node
     */
    BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
    	this.key = key;
        this.value = value;
        this.left = leftChild;
        this.right = rightChild;
        this.height = 1;
        this.balanceFactor = 0;
    }
    
    BSTNode(K key, V value) { this(key,value,null,null); }
    
}
