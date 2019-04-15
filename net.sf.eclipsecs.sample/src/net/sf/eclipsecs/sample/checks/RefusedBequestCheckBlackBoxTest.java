/**
 * File: src/net.sf.eclipsecs.sample.checks/RefusedBequestCheckBlackBoxTest.java
 * -------------------------------------------------------------------------------------------
 * Date			Author          Changes
 * -------------------------------------------------------------------------------------------
 * 04/14/2019	xiaoqin Fu		created; Black box test cases for Refused Bequest Check (Deliverable 2)
*/
package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

class RefusedBequestCheckBlackBoxTest {

	RefusedBequestCheck check = new RefusedBequestCheck();
	  
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
	 
	 // test childMethodNum default value
	 @Test
	 public void testDefaultChildMethodNum(){
		//  childMethodNum default value should be 0
		 assertEquals(0, check.getChildMethodNum());
	 }

	 // test fatherMethodNum default value
	 @Test
	 public void testDefaultFatherMethodNum(){
		//  fatherMethodNum default value should be 0
		 assertEquals(0, check.getFatherMethodNum());
	 }

	 // test fatherClassName default value
	 @Test
	 public void testDefaultFatherClassName(){
		 String fatherClassNameReceived=check.getFatherClassName();
		 // fatherClassName default value should be empty string
		 assertEquals("", fatherClassNameReceived);
	 }
	 
	 // test the function setChildMethodNum (getChildMethodNum())
	 @Test	   
	 public void testSetChildMethodNum(){		 
		 check.setChildMethodNum(Integer.MIN_VALUE);
		 assertEquals(Integer.MIN_VALUE, check.getChildMethodNum());		 
		 check.setChildMethodNum(-20);
		 assertEquals(-20, check.getChildMethodNum());
		 check.setChildMethodNum(-1);
		 assertEquals(-1, check.getChildMethodNum());	
		 check.setChildMethodNum(1);
		 assertEquals(1, check.getChildMethodNum());
		 check.setChildMethodNum(200);
		 assertEquals(200, check.getChildMethodNum());
		 check.setChildMethodNum(Integer.MAX_VALUE);
		 assertEquals(Integer.MAX_VALUE, check.getChildMethodNum());
		 check.setChildMethodNum(0);
		 assertEquals(0, check.getChildMethodNum());	 
	 }	
	 
	 // test the function setFatherMethodNum (getFatherMethodNum())
	 @Test	   
	 public void testSetFatherMethodNum(){
		 check.setFatherMethodNum(Integer.MIN_VALUE);
		 assertEquals(Integer.MIN_VALUE, check.getFatherMethodNum());		 
		 check.setFatherMethodNum(-20);
		 assertEquals(-20, check.getFatherMethodNum());
		 check.setFatherMethodNum(-1);
		 assertEquals(-1, check.getFatherMethodNum());
		 check.setFatherMethodNum(1);
		 assertEquals(1, check.getFatherMethodNum());
		 check.setFatherMethodNum(200);
		 assertEquals(200, check.getFatherMethodNum());
		 check.setFatherMethodNum(Integer.MAX_VALUE);
		 assertEquals(Integer.MAX_VALUE, check.getFatherMethodNum());
		 check.setFatherMethodNum(0);
		 assertEquals(0, check.getFatherMethodNum());
	 }	
	 
	 // test the function setFatherClassName (getFatherClassName())
	 @Test	   
	 public void testSetFatherClassName(){
		 check.setFatherClassName("a");
		 assertEquals("a", check.getFatherClassName());
		 check.setFatherClassName("testclass");
		 assertEquals("testclass", check.getFatherClassName());
		 check.setFatherClassName("class1234567890123456789012345678901234567890");
		 assertEquals("class1234567890123456789012345678901234567890", check.getFatherClassName());
		 check.setFatherClassName("");
		 assertEquals("", check.getFatherClassName());
	 }	
	 
  
}
