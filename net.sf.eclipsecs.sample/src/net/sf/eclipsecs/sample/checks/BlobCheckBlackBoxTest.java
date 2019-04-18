/**
 * File: src/net.sf.eclipsecs.sample.checks/BlobCheckBlackBoxTest.java
 * -------------------------------------------------------------------------------------------
 * Date			Author          Changes
 * -------------------------------------------------------------------------------------------
 * 04/14/2019	xiaoqin Fu		created; Black box test cases for Blob Check (Deliverable 2)
*/
package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class BlobCheckBlackBoxTest {
	BlobCheck check = new BlobCheck();
	 
	  @Before
	  public void setUp() throws Exception {
	  }
	  
	 @Test
	 public void testGetDefaultTokensNotNull() {
	    assertNotNull("Default tokens should not be null", check.getDefaultTokens());
	 }

	 @Test
	 public void testGetAcceptableTokensNotNull() {
	    assertNotNull("Acceptable tokens should not be null", check.getAcceptableTokens());
	 }

	 @Test
	 public void testGetRequiredTokensNotNull() {
	    assertNotNull("Required tokens should not be null", check.getRequiredTokens());
	 }
	 
	 // test max default value
	 @Test
	 public void testDefaultMax(){
		 //  max default value should be 20
		 assertEquals(20, check.getMax());
	 }
	 
	 // test the function setMax (getMax())
	 @Test	   
	 public void testSetMax(){
		 check.setMax(Integer.MIN_VALUE);
		 assertEquals(Integer.MIN_VALUE, check.getMax());		 
		 check.setMax(-20);
		 assertEquals(-20, check.getMax());
		 check.setMax(-1);
		 assertEquals(-1, check.getMax());
		 check.setMax(1);
		 assertEquals(1, check.getMax());
		 check.setMax(10);
		 assertEquals(10, check.getMax());
		 check.setMax(Integer.MAX_VALUE);
		 assertEquals(Integer.MAX_VALUE, check.getMax());	
		 check.setMax(0);
		 assertEquals(0, check.getMax());
	 }	

	 // test the function setCount (getCount())
	 @Test	   
	 public void testSetCount(){
		 check.setCount(Integer.MIN_VALUE);
		 assertEquals(Integer.MIN_VALUE, check.getCount());		 
		 check.setCount(-20);
		 assertEquals(-20, check.getCount());
		 check.setCount(-1);
		 assertEquals(-1, check.getCount());
		 check.setCount(1);
		 assertEquals(1, check.getCount());
		 check.setCount(10);
		 assertEquals(10, check.getCount());		 
		 check.setCount(Integer.MAX_VALUE);
		 assertEquals(Integer.MAX_VALUE, check.getCount());	
		 check.setCount(0);
		 assertEquals(0, check.getCount());
	 }	
}
