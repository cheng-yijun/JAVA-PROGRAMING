//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            PackageManagerTest
// Files:            PackageManagerTest.java
// Semester:         Fall--2019
//
// Author:           Yijun Cheng 
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc 
// Lecturer's Name:  <cs400>
// Lecture number:   001
// program description: This is test class to test all the methods in the
// PackageManager class and test their function to check if the functions are
// as expected. Check the installation order.etc...
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PackageManagerTest class to test the functions in PackageManager class
 * @author yijuncheng
 *
 */
class PackageManagerTest {
	public static final String PACKAGES = "shared_dependencies.json";
	static PackageManager managerObject;
	
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
		managerObject = createInstance();
	}

	/**
	 * This is a tear down method 
	 * @throws Exception this method may throw exceptions
	 */
	@AfterEach
	void tearDown() throws Exception {
		managerObject = null;
	}
	
	/**
	 * createInstance to get the instance of  PackageManager
	 * @return a new PackageManager instance
	 */
	protected PackageManager createInstance() {
        return new PackageManager();
    }
	
	/**
	 * Test if the construct of the Graph is as expected
	 * And if the JASON file is parsed correctly graph is constructed correctly.
	 */
	@Test
	void test00_constructGraph() {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}

	/**
	 * Test the getAllPackages method in PackageManager class.
	 * Test if the number of packages is correct (are all in the graph)
	 */
	@Test
	void test01_getAllPackages() {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
			Set<String> allPkg = managerObject.getAllPackages();
			// check the size of the set
			if (allPkg.size() != 5) {
				fail("Unexpected number of packages");
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}
	
	/**
	 * Test if the installation order for a specific package is 
	 * valid and as expected
	 * @throws CycleException an un-expected cycle occurred.
	 * @throws PackageNotFoundException cannot find package in the graph
	 */
	@Test
	void test02_getInstallationOrder()
			throws CycleException, PackageNotFoundException {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
			// get the list of order need to be installed
			List<String> order = managerObject.getInstallationOrder("D");
			if (!order.get(0).equals("A") || !order.get(1).equals("C") 
				|| !order.get(2).equals("B") || !order.get(3).equals("D")) {
				fail("Unexpected order for installing");
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}
	
	/**
	 * Test if the toInstall methods is correct
	 * given a package installed and a package to be installed, test if 
	 * the method can return a list contains the packages need to be installed
	 * and the order is valid
	 * @throws CycleException an un-expected cycle occurred.
	 * @throws PackageNotFoundException cannot find package in the graph
	 */
	@Test
	void test03_toInstall() throws CycleException, PackageNotFoundException {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
			// get the order of packages to be installed
			List<String> order = managerObject.toInstall("D", "C");
			if (!order.get(0).equals("D") || !order.get(1).equals("B")) {
				fail("Unexpected order for to install");
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}
	
	/**
	 * Test if the getInstallationOrderForAllPackages method is correct
	 * Test if the order in the list is valid to install all of the packages
	 * @throws CycleException an un-expected cycle occurred.
	 * @throws PackageNotFoundException cannot find package in the graph
	 */
	@Test
	void test04_getInstallationOrderForAllPackages() 
			throws CycleException, PackageNotFoundException {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
			// get the order for installing all packages
			List<String> order = 
					managerObject.getInstallationOrderForAllPackages();
			if (order.indexOf("A") != 0) {
				fail("Unexpected order to install");
			}
			if (order.indexOf("C") > order.indexOf("D")) {
				fail("Unexpected order to install");
			}
			if (order.indexOf("C") > order.indexOf("B")) {
				fail("Unexpected order to install");
			}
			if (order.indexOf("B") > order.indexOf("D")) {
				fail("Unexpected order to install");
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}

	/**
	 * Test if the getPackageWithMaxDependencies is correct
	 * Test if the package is as expected that has max dependencies
	 * @throws CycleException an un-expected cycle occurred.
	 * @throws PackageNotFoundException cannot find package in the graph
	 */
	@Test
	void test05_getPackageWithMaxDependencies()
			throws CycleException, PackageNotFoundException {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
			// get the package with max dependency
			String maxPkg = managerObject.getPackageWithMaxDependencies();
			if (!maxPkg.equals("D")) {
				fail("Unexpected Package with max dependencies found");
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}
	
	/**
	 * Further test to test if the install order is correct
	 * For a specific package, test 
	 * @throws CycleException an un-expected cycle occurred.
	 * @throws PackageNotFoundException cannot find package in the graph
	 */
	@Test
	void test06_specific_package_install_order() 
			throws CycleException, PackageNotFoundException {
		try {// construct the graph for packages
			managerObject.constructGraph(PACKAGES);
			// get the list of packages to be installed
			List<String> Pkgs = managerObject.getInstallationOrder("E");
			if (!Pkgs.get(0).equals("A")) {
				fail("Unexpected installed order");
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		} catch (IOException e) {
			fail("Unexpected IOException");
		} catch (ParseException e) {
			fail("Unexpected ParseException");
		}
	}
}
