package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import static org.junit.Assert.*;

class BlobCheckTest {

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
	 
	 // test the function setMax (getMax())
	 @Test	   
	 public void testsetMax(){
		 check.setMax(10);
		 assertEquals(10, check.getMax());
	 }	
	 
	 // test the function visitTokenWithoutLog 
	 @Test
	 public void testvisitTokenWithoutLog() throws IOException, CheckstyleException{		 
		FileText text;
		FileContents contents;
		DetailAST rootAST;			 
		text = new FileText(new File("C:/Temp/BlobTestScenario.java"), System.getProperty("file.encoding", "UTF-8"));
		contents = new FileContents(text);
		rootAST = JavaParser.parse(contents);
		check.visitTokenWithoutLog(rootAST);
        // Function visitTokenWithoutLog should let the count value is greater than zero and we use getCount() to get the count value.
        assertTrue(check.getCount()>0);
     // Function visitTokenWithoutLog should let the count value is greater than or equals to max value set.
        assertTrue(check.getCount()>=check.getMax()); 
	 } 
	 
}
