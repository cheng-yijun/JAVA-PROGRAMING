//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            GraphTest
// Files:            GraphTest.java
// Semester:         Fall--2019
//
// Author:           Yijun Cheng 
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc 
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: This is test class to test all the methods in the
// Graph class and test their function to check if the functions are
// as expected. Check the add vertex and edges, and remove...
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class to test the function of the graph struct
 * @author yijuncheng
 *
 */
class GraphTest {
	
	static Graph graph;
	
	/**
	 * This is a set up method before class
	 * @throws Exception this method may throw exceptions
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * This is a tear down method before class
	 * @throws Exception this method may throw exceptions
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * This is a set up method 
	 * @throws Exception this method may throw exceptions
	 */
	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
	}

	/**
	 * This is a tear down method 
	 * @throws Exception this method may throw exceptions
	 */
	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}
	
	/**
	 * Test the addVertex method in Graph
	 */
	@Test
	void test00_addVertex() {
		// add vertices
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v1");
		// get the number of vertices and edges
		int order = graph.order();
		int degree = graph.size();
		if (order != 2) {
			fail("Unexpected number of vertices");
		}
		if (degree != 0) {
			fail("Unexpected number of edge");
		}
	}

	/**
	 * Test the removeVertex method in Graph
	 */
	@Test
	void test01_removeVertex() {
		// add vertices
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");
		// remove some vertices
		graph.removeVertex("v1");
		graph.removeVertex("v2");
		graph.removeVertex("v1");
		graph.removeVertex("v5");
		// check if the order is correct
		if (graph.order() != 2) {
			fail("Unexpected number of vertices after remove");
		}
	}
	
	/**
	 * Test the addEdge method in Graph
	 */
	@Test
	void test02_addEdge() {
		// add vertices
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");
		// add edge between 2 vertices
		graph.addEdge("v1", "v2");
		graph.addEdge("v1", "v3");
		graph.addEdge("v1", "v5");
		List<String> adj = graph.getAdjacentVerticesOf("v1");
		// check if the adjacent list is correct
		if (adj.get(0) != "v2" || adj.get(1) != "v3" || adj.get(2) != "v5") {
			fail("Unexpected adjacent vertices of v1");
		}
		if (adj.size() != 3) {
			fail("Unexpected adjacent size");
		}
	}
	
	/**
	 * Test the removeEdge method in Graph
	 */
	@Test
	void test03_removeEdge() {
		// add vertices
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");
		// add edge between 2 vertices
		graph.addEdge("v1", "v2");
		graph.addEdge("v1", "v3");
		graph.addEdge("v1", "v5");
		// check the size of the graph before the remove
		if (graph.size() != 3) {
			fail("Unexpected number of edges before removing edges");
		}
		graph.removeEdge("v1", "v2");
		graph.removeEdge("v1", "v3");
		graph.removeEdge("v1", "v5");
		// check the size of the graph after the remove
		if (graph.size() != 0) {
			fail("Unexpected number of edges after removing edges");
		}
	}

	/**
	 * Test the getAllVertices method in Graph
	 */
	@Test
	void test04_getAllVertices() {
		// add vertices
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addVertex("v5");
		// add edge
		graph.addEdge("v1", "v6");
		// remove the edge
		graph.removeVertex("v1");
		// check the vertices in the graph
		if (graph.getAllVertices().size() != 5) {
			fail("Unexpected number of vertices of this graph");
		}
	}
	
	/**
	 * Construct a complex graph and test its fuction
	 */
	@Test
	void test05_graph_net_work() {
		// add vertices
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addVertex("v5");
		// add edge
		graph.addEdge("v1", "v6");
		graph.addEdge("v2", "v7");
		graph.addEdge("v9", "v10");
		graph.addEdge("v9", "v1");
		graph.addEdge("v7", "v9");
		// check the size of this graph
		if (graph.size() != 5) {
			fail("Unexpected number of edges of this graph");
		}
	}
	
	/**
	 * Add some null vertex in the graph and test if the graph is as expected
	 */
	@Test
	void test06_add_null() {
		// add vertices
		graph.addVertex(null);
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addVertex(null);
		// check the order of graph
		if (graph.order() != 3) {
			fail("Unexpected number of edges of this graph");
		}
	}
	
}
