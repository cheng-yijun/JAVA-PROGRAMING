//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Graph
// Files:            Graph.java
// Semester:         Fall--2019
//
// Author:           Yijun Cheng 
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc 
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: This is a Graph data structure. Used to help construct
// a valid graph structure. Vertices can be added into the graph, and edges
// can be connected to construct this graph. Also, vertices and edges can be
// removed from the graph. 
// This graph is matrix based which means edges connection is represented in
// this matrix. 
// This is a very useful and popular program for users when installing packages.
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    Yijun Cheng
 * 
 * Directed and unweighted graph implementation
 * @param <T>
 */

/**
 * This is a Graph class used to implement basic function of a graph.
 * Some functions are needed to construct a graph.
 * This graph is matrix based, which means a matrix represented edge will be 
 * used to construct 
 * @author yijuncheng
 *
 */
public class Graph implements GraphADT {
	//a 2-d ArrayList represented matrix
	private List<ArrayList<Integer>> matrix;
	//an ArrayList of all vertices
	private List<String> vertexList;
	
	/**
	 * Default no-argument constructor
	 */
	public Graph() {
		// initialize the list(to store all vertices)
		vertexList = new ArrayList<>();
		// initialize the matrix and every element to 0
		matrix = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < vertexList.size(); i++) {
			for (int j = 0; j < vertexList.size(); j++) {
				matrix.get(i).add(0);
			}
		}
	}

	/**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void addVertex(String vertex) {
		// check if the vertex is null
		if (vertex == null) {
			return;
		}
		// iterate to check if the vertex is already in the graph
		for (int i = 0; i < vertexList.size(); i++) {
			if (vertexList.get(i).equals(vertex)) {
				return;
			}
		}
		// vertex is not in graph
		// add vertex into vertex list
		vertexList.add(vertex);
		// update matrix
		matrix.add(new ArrayList<Integer>());
		// set corresponding matrix element to represent edge
		for (int i = 0; i < vertexList.size() - 1; i++) {
			matrix.get(vertexList.size() - 1).add(0);
		}
		for (int i = 0; i < vertexList.size(); i++) {
			matrix.get(i).add(0);
		}
	}

	/**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void removeVertex(String vertex) {
		// check if the vertex is null
		if (vertex == null) {
			return;
		}
		// found variable used to check if the vertex is already in the graph
		boolean found = false;// initialize the found to false
		int index = 0;// initialize the index to start(0)
		// iterate to find the vertex existence and index in vertices list
		for (int i = 0; i < vertexList.size(); i++) {
			if (vertexList.get(i).equals(vertex)) {// find the vertex
				// update found and index
				found = true;
				index = i;
				break;
			}
		}
		if (!found) {// vertex is not in the graph
			return;
		}
		// vertex is in the graph
		// remove this vertex from vertices list and matrix
		vertexList.remove(index);
		matrix.remove(index);
		// remove edge in matrix
		for (int i = 0; i < vertexList.size(); i++) {
			matrix.get(i).remove(index);
		}
	}

	/**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * add vertex, and add edge, no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
		// check if there is at least one vertex are null
		if (vertex1 == null || vertex2 == null) {
			return;
		}
		// define found variable and index variable for searching the vertex
		// and initialize these variables
		boolean found1 = false;
		int index1 = 0;
		boolean found2 = false;
		int index2 = 0;
		// iterate to search the vertices(vertex1 and vertex2)
		for (int i = 0; i < vertexList.size(); i++) {
			if (vertexList.get(i).equals(vertex1)) {// find vertex 1
				found1 = true;
				index1 = i;
			}
			if (vertexList.get(i).equals(vertex2)) {// find vertex 2
				found2 = true;
				index2 = i;
			}
		}
		// both vertices are in the graph
		if (found1 && found2) {
			// add edge between vertex1 and vertex2
			matrix.get(index1).set(index2, 1);
		}
		// vertex1 is in the graph but vertex2 is not
		else if (found1 && !found2) {
			// update the index of vertex2
			index2 = vertexList.size();
			// add vertex2 into vertices list
			addVertex(vertex2);
			// add edge between vertex1 and vertex2
			matrix.get(index1).set(index2, 1);
		}
		// vertex2 is in the graph but vertex1 is not
		else if (!found1 && found2) {
			// update the index of vertex1
			index1 = vertexList.size();
			// add vertex1 into vertices list
			addVertex(vertex1);
			// add edge between vertex1 and vertex2
			matrix.get(index1).set(index2, 1);
		} else {// !found1 && !found2
				// update the index of vertex1 and vertex2
			index1 = vertexList.size();
			addVertex(vertex1);
			index2 = vertexList.size();
			addVertex(vertex2);
			matrix.get(index1).set(index2, 1);
		}
	}
	
	/**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
	public void removeEdge(String vertex1, String vertex2) {
		// check if at least one of the vertices are null
		if (vertex1 == null || vertex2 == null) {
			return;
		}
		// create variable for searching corresponding existence index in graph
		boolean found1 = false;
		int index1 = -1;
		boolean found2 = false;
		int index2 = -1;
		// iterate to find the corresponding existence and index
		for (int i = 0; i < vertexList.size(); i++) {
			if (vertexList.get(i).equals(vertex1)) {
				found1 = true;
				index1 = i;
			}
			if (vertexList.get(i).equals(vertex2)) {
				found2 = true;
				index2 = i;
			}
		}
		// any vertex is not found, finish
		if (!found1 || !found2) {
			return;
		}
		// both vertices are found
		if (found1 && found2) {
			// remove the edge
			if (matrix.get(index1).get(index2) == 1) {
				matrix.get(index1).set(index2, 0);
			}
		}
	}

	/**
     * a Set that contains all the vertices
     * @return a Set that contains all the vertices
	 */
	public Set<String> getAllVertices() {
		// create a set and initialize
		Set<String> allVertices = new TreeSet<String>();
		// add vertices into the set
		for (int i = 0; i < vertexList.size(); i++) {
			allVertices.add(vertexList.get(i));
		}
		return allVertices;
	}

	/**
     * Get all the neighbor (adjacent) vertices of a vertex
     * @return List<String> a list storing all the adjacent vertices
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		// create a list to store all the adjacent element
		List<String> adjacent = new ArrayList<String>();
		// check if the vertex is null
		if (vertex == null) {
			return adjacent;
		}
		// find the existence and corresponding index in graph
		boolean found = false;
		int index = 0;
		for (int i = 0; i < vertexList.size(); i++) {
			// find vertex, assign corresponding index
			if (vertexList.get(i).equals(vertex)) {
				found = true;
				index = i;
				break;
			}
		}
		// vertex is not in the graph
		if (!found) {
			return null;
		}
		// add adjacent vertices of current vertex into the list
		for (int i = 0; i < vertexList.size(); i++) {
			// search in matrix to check edge connection
			if (matrix.get(index).get(i) == 1) {
				adjacent.add(vertexList.get(i));
			}
		}
		return adjacent;
	}
	
	/**
     * Returns the number of edges in this graph.
     */
	public int size() {
		// give a integer variable store number of edges
		int edgeNum = 0;
		// iterate the matrix to find the total number of edges.
		for (int i = 0; i < vertexList.size(); i++) {
			for (int j = 0; j < vertexList.size(); j++) {
				// find edge
				if (matrix.get(i).get(j) == 1) {
					edgeNum++;// increase edgeNum
				}
			}
		}
		return edgeNum;
	}

	/**
     * Returns the number of vertices in this graph.
     */
	public int order() {
		// number of vertices is the size of vertexList
		int vertexNum = vertexList.size();
		return vertexNum;
	}
}
