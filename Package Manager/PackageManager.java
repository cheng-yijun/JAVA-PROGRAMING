//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            PackageManager
// Files:            PackageManager.java
// Semester:         Fall--2019
//
// Author:           Yijun Cheng 
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc 
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: This is a package manager program. In this program, 
// users can obtain the order of installation for the packages that they
// want to install. In addition, there are some other methods that can help
// users get the package that need most dependencies to install, packages
// to be installed given installed packages...
// This is a very useful and popular program for users when installing packages.
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
	//2D represented dictionary of dependencies
	private List<ArrayList<String>> dependdict;
    private List<String> jsPkgs;
    private List<Integer> visit;
    private List<String> allVertices;
    private Graph graph;
    
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
		graph = new Graph();
		dependdict = new ArrayList<ArrayList<String>>();
		jsPkgs = new ArrayList<String>();
		visit = new ArrayList<Integer>();
		allVertices = new ArrayList<String>();
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file 
     * 		  with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath)
    		throws FileNotFoundException, IOException, ParseException {
		// create JSON object and JSON array to parse the JSON file
		Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
		JSONObject jo = (JSONObject) obj;// cast object to JSON object
		// parse JSON into a JSON array
		JSONArray packages = (JSONArray) jo.get("packages");
		for (int i = 0; i < packages.size(); i++) {
			// get this package in JSON array
			JSONObject jsonPackage = (JSONObject) packages.get(i);
			// get the name of this package
			String packageName = (String) jsonPackage.get("name");
			// get the dependencies of this package
			JSONArray dependencies = 
					(JSONArray) jsonPackage.get("dependencies");
			// add this package with its corresponding dependencies into list
			dependdict.add(dependencies);
			jsPkgs.add(packageName);
			// add the vertex into the graph structure
			graph.addVertex(packageName);
			// add dependencies in the graph and add necessary edge
			for (int j = 0; j < dependencies.size(); j++) {
				String dependency = (String) dependencies.get(j);
				graph.addEdge(dependency, packageName);
			}
		}
		// use set to store all of the vertices in the graph
		Set<String> allPackages = new TreeSet<String>();
		Set<String> allpkgs = getAllPackages();
		// get all packages into a sorted set
		for (String value : allpkgs) {
			allPackages.add(value);
		}
		// get all sorted packages into an ArrayList
		for (String value : allPackages) {
			allVertices.add(value);
		}
		// initialize all visit to false(0)
		for (int i = 0; i < graph.order(); i++) {
			visit.add(0);// 0 means un-visited
		}
	}

	/**
	 * Helper method to get all packages in the graph.
	 * 
	 * @return Set<String> of all the packages
	 */
	public Set<String> getAllPackages() {
		// create a set to store all the vertices in the graph
		Set<String> allPackages = graph.getAllVertices();
		return allPackages;
    }
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg)
    		throws CycleException, PackageNotFoundException {
		// clear the visit list to 0 (un-visit)
		for (int i = 0; i < visit.size(); i++) {
			visit.set(i, 0);
		}
		// check if the package is null
		if (pkg == null) {
			throw new PackageNotFoundException();
		}
		// create a variable to mark if the package vertex is in the graph
		boolean found = false;
		// initialize the index of the
		int vertexIndex = -1;
		for (int i = 0; i < allVertices.size(); i++) {
			// iterate to find the found and index in the allVertices list
			if (allVertices.get(i).equals(pkg)) {
				found = true;
				vertexIndex = i;
				break;
			}
		}
		// if the package is not found
		if (!found) {
			throw new PackageNotFoundException();
		}
		visit.set(vertexIndex, 1);// mark pkg as visited
		// create a list to store the package order
		List<String> install = new ArrayList<String>();
		int jsIndex = -1;// initialize the jsIndex
		for (int i = 0; i < jsPkgs.size(); i++) {
			if (jsPkgs.get(i).equals(pkg)) {
				// find the corresponding index in the JSON package list
				jsIndex = i;
			}
		}
		// no dependencies to install before this package
		if (jsIndex == -1) {
			install.add(pkg);
			return install;
		}
		// has dependencies
		// create a list to store all the start package(s)
		// start packages means those package without dependency
		List<String> startVertices = new ArrayList<String>();
		// for each dependencies in this package, recursive to check cycle
		for (int i = 0; i < dependdict.get(jsIndex).size(); i++) {
			// get one of the start package every loop
			String startVertex = 
					installOrderHelper(dependdict.get(jsIndex).get(i));
			// once there is a null start package meaning a cycle occur
			if (startVertex == null) {
				throw new CycleException();
			} else {// find a start vertex
					// add this original package to startVertices list
				startVertices.add(startVertex);
				// clear visit list
				for (int x = 0; x < visit.size(); x++) {
					visit.set(x, 0);
				}
				visit.set(vertexIndex, 1);// mark package as visited
			}
		}
		// no cycle generated
		// get the order of installation
		Queue<String> orderQueue = new LinkedList<String>();
		// add this package to install list
		install.add(pkg);
		// add all dependencies of this package into the queue
		for (int i = 0; i < dependdict.get(jsIndex).size(); i++) {
			orderQueue.offer(dependdict.get(jsIndex).get(i));
		}
		// loop to get the valid order
		while (!orderQueue.isEmpty()) {
			// get the first element in the queue
			String node = orderQueue.remove();
			// then add this node into install list
			install.add(node);
			// initialize a index(recording the index in the JSON list) to -1
			int nodeJsIndex = -1;
			// find the corresponding index
			for (int i = 0; i < jsPkgs.size(); i++) {
				if (jsPkgs.get(i).equals(node)) {
					nodeJsIndex = i;
				}
			}
			// cannot find the node in JSON list(no dependencies)
			if (nodeJsIndex != -1) {
				// find all the dependencies of this package
				for (int i = 0; i < dependdict.get(nodeJsIndex).size(); i++) {
					// add current dependency(package) to queue if
					// it is not in the queue
					String lowerNode = dependdict.get(nodeJsIndex).get(i);
					if (!orderQueue.contains(lowerNode)) {
						orderQueue.offer(lowerNode);// add into queue
					}
				}
			}
		}
		// install is a inverse order of real installation order
		// inverse the list to get the real order
		List<String> realOrder = new ArrayList<String>();
		// from last element to first
		for (int i = install.size() - 1; i >= 0; i--) {
			realOrder.add(install.get(i));
		}
		// remove duplicate package in the order list
		for (int i = 0; i < realOrder.size(); i++) {
			for (int j = i + 1; j < realOrder.size(); j++) {
				if (realOrder.get(j).equals(realOrder.get(i))) {
					realOrder.remove(j);
				}
			}
		}
		// clear visit list
		for (int i = 0; i < visit.size(); i++) {
			visit.set(i, 0);
		}
		return realOrder;
    }
    
    /**
     * This is a private helper method to recurse finding cycle exception
     * @param vertex package vertex of the graph
     * @return a String represent a package vertex
     */
    private String installOrderHelper(String vertex){
		// create a start vertex and initialize it to null
		String startVertex = null;
		// check if it is unvisited
		boolean isVisited = ifVisited(vertex);
		if (isVisited) {// this vertex has been visited meaning here is a cycle
			return null;
		}
		// un-visited situation
		int vertexIndex = -1;
		for (int i = 0; i < allVertices.size(); i++) {
			// create a index for iterating and search corresponding index
			// in allVertices list
			if (allVertices.get(i).equals(vertex)) {
				vertexIndex = i;
			}
		}
		if (vertexIndex == -1) {
			// impossible actually since this vertex must be in the graph
			return null;
		}
		visit.set(vertexIndex, 1);// mark pkg as visited
		// find dependencies of this vertex
		int jsIndex = -1;
		for (int i = 0; i < jsPkgs.size(); i++) {
			// find the corresponding index in JSON list
			if (jsPkgs.get(i).equals(vertex)) {
				jsIndex = i;
			}
		}
		if (jsIndex == -1) {// no dependency
			return vertex;
		}
		// has dependencies
		// continue recursing to find cycle
		for (int i = 0; i < dependdict.get(jsIndex).size(); i++) {
			// recurse to lower dependency level of this package
			startVertex = installOrderHelper(dependdict.get(jsIndex).get(i));
			if (startVertex == null) {// cycle occur
				return null;
			} else {
				// find corresponding index in graph allVertices list
				int vertexIndex2 = -1;
				for (int j = 0; j < allVertices.size(); j++) {
					if (allVertices.get(j)
							.equals(dependdict.get(jsIndex).get(i))) {
						vertexIndex2 = j;
					}
				}
				visit.set(vertexIndex2, 0);// mark package as Un-visited
				// clear all dependencies' visit to 0 for current package
				clearVisit(dependdict.get(jsIndex).get(i));
			}
		}
		return startVertex;
    }
    
    /**
     * A private helper method to clear all dependencies vertices' visit to 0
     * @param vertex a vertex represented package
     */
    private void clearVisit(String vertex) {
		// find corresponding index in JASON list
		int jsIndex = -1;
		for (int i = 0; i < jsPkgs.size(); i++) {
			if (jsPkgs.get(i).equals(vertex)) {
				jsIndex = i;
			}
		}
		// no dependencies further
		if (jsIndex == -1) {
			return;
		}
		// recurse to lower dependencies level to clear visit
		for (int i = 0; i < dependdict.get(jsIndex).size(); i++) {
			// find corresponding index in allVertices list
			int vertexIndex = -1;
			for (int j = 0; j < allVertices.size(); j++) {
				if (allVertices.get(j).equals(dependdict.get(jsIndex).get(i))) {
					vertexIndex = j;
				}
			}
			// cleat this vertex's visit to 0
			visit.set(vertexIndex, 0);// mark pkg as un-visited
			// continue recursing
			clearVisit(dependdict.get(jsIndex).get(i));
		}
		return;
    }
    
    /**
     * Check if the vertex is visited
     * @param vertex a vertex represented package
     * @return true if this package is visited, false otherwise
     */
    private boolean ifVisited(String vertex) {
		// find corresponding index in graph allVertices
		int vertexIndex = -1;
		for (int i = 0; i < allVertices.size(); i++) {
			if (allVertices.get(i).equals(vertex)) {
				vertexIndex = i;
			}
		}
		// package does not exist in graph
		if (vertexIndex == -1) {
			return false;
		}
		// not visited
		if (visit.get(vertexIndex) == 0) {
			return false;
		}
		// visited
		if (visit.get(vertexIndex) == 1) {
			return true;
		}
		return false;
    }
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg)
    		throws CycleException, PackageNotFoundException {
		// check if at least one vertices are null
		if (newPkg == null || installedPkg == null) {
			throw new PackageNotFoundException();
		}
		// create variable to record if it can be found in graph
		boolean found1 = false;
		boolean found2 = false;
		for (int i = 0; i < allVertices.size(); i++) {
			if (allVertices.get(i).equals(newPkg)) {
				// find vertex
				found1 = true;
				found2 = true;
				break;
			}
		}
		if (!found1 || !found2) {// at least one vertices are not in the graph
			throw new PackageNotFoundException();
		}
		// two vertices are both in the graph
		// toInstall list: packages to be installed
		List<String> toInstall = new ArrayList<String>();
		// needInstall list: all packages that need to be installed
		List<String> needInstall = getInstallationOrder(newPkg);
		// add this package into toInstall list
		toInstall.add(newPkg);
		// list storing all installed packages for newPkg
		List<String> installed = getInstallationOrder(installedPkg);
		// add packages to be installed into list
		for (int i = 0; i < needInstall.size(); i++) {
			if (!installed.contains(needInstall.get(i)) 
					&& !needInstall.get(i).equals(newPkg)) {
				toInstall.add(needInstall.get(i));
			}
		}
		return toInstall;
    }
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>,order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages()
    		throws CycleException {
		// create a list to store all the order
		List<String> order = new ArrayList<String>();
		// for each package, obtain the installation order of it
		for (int i = 0; i < allVertices.size(); i++) {
			// get this package
			String node = allVertices.get(i);
			try {
				// install order for current node(package)
				List<String> lowerOrder = getInstallationOrder(node);
				for (int j = 0; j < lowerOrder.size(); j++) {
					// avoid duplicate packages in the order
					if (!order.contains(lowerOrder.get(j))) {
						order.add(lowerOrder.get(j));
					}
				}
			} catch (PackageNotFoundException e) {
				System.out.println("impossible here");
			}
		}
		return order;
    }
    
    /**
     * Find and return the name of the package with the maximum number of 
     * dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
		// create a package with max dependencies
		String maxDpdcPkg = allVertices.get(0);
		try {
			// (Assume)record the first package as max
			int max = getInstallationOrder(maxDpdcPkg).size();
			// search and find a package with max dependencies
			// iterate and check all packages with its corresponding dependency
			for (int i = 1; i < allVertices.size(); i++) {
				String node = allVertices.get(i);
				try {
					// get the dependencies of the package
					List<String> lowerOrder = getInstallationOrder(node);
					// compare and obtain the max dependencies package
					if (lowerOrder.size() > max) {
						max = lowerOrder.size();//update the max
						maxDpdcPkg = node;//replace package
					}
				} catch (PackageNotFoundException e) {
					System.out.println("impossible here");
				}
			}
		} catch (PackageNotFoundException e) {
			System.out.println("impossible here");
		}
		return maxDpdcPkg;
    }
    
    /**
     * main function for this PackageManager class
     * @param args user input arguments
     */
    public static void main (String [] args) {
    	System.out.println("PackageManager.main()");
	}
}
