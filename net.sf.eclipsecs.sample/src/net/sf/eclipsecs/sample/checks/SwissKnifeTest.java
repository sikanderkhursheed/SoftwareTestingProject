package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.File;

import org.junit.Before;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SwissKnifeTest {


	 SwissKnifeCheck s = new SwissKnifeCheck();
	 
	 private FileText text;
	 private FileContents contents;
	 private DetailAST rootAST;	 

	 @Test	   
	 public void testSetMax(){
		 s.setMax(Integer.MIN_VALUE);	 
		 s.setMax(-20);
		 s.setMax(-1);
		 s.setMax(2);
		 s.setMax(Integer.MAX_VALUE);
		 s.setMax(0);
	 }	
	 
	 @Test
	    public void testGetDefaultTokensNotNull() {
	        assertNotNull("Default tokens should not be null", s.getDefaultTokens());
	    }

	   @Test
	    public void testGetAcceptableTokensNotNull() {
	        assertNotNull("Acceptable tokens should not be null", s.getAcceptableTokens());
	    }

	    @Test
	    public void testGetRequiredTokensNotNull() {
	        assertNotNull("Required tokens should not be null", s.getRequiredTokens());
	    }

	    @Test
	    public void testGetDefaultTokens()  throws Throwable  {      
	        int[] intArray0 = s.getDefaultTokens();
	        assertArrayEquals(new int[] {14}, intArray0);
	        assertFalse(s.getCount());
	    }

	    @Test
	    public void testGetAcceptableTokens()  throws Throwable  {      
	        int[] intArray0 = s.getAcceptableTokens();
	        assertFalse(s.getCount());
	        assertArrayEquals(new int[] {14}, intArray0);
	    }

	    @Test
	    public void testGetRequiredTokens()  throws Throwable  {      
	        int[] intArray0 = s.getRequiredTokens();
	        assertFalse(s.getCount());
	        assertEquals(0, intArray0.length);
	    }
	    
	 @Test (expected = NullPointerException.class)
	    public void testVisitToken_1() throws Throwable {
	        DetailAST detailast = rootAST.getNextSibling();
	        	        
	        s.visitToken(detailast);
	        assertTrue(s.getCount());
	    }
	 
	 @Test 
	    public void testVisitToken_2() throws Throwable {
	      DetailAST detailAST0 = new DetailAST();
	      // Undeclared exception!
	      try { 
	        s.visitToken(detailAST0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	      }
	    }
	 		 
	 @Test	   
	 public void testGetCount(){
		 boolean isGetCount=s.getCount();
		 assertFalse(isGetCount);
		 s.setMax(Integer.MIN_VALUE);	
		 isGetCount=s.getCount();
		 assertTrue(isGetCount);
		 //System.out.println("isGetCount with Integer.MIN_VALUE max: "+isGetCount);
		 s.setMax(Integer.MAX_VALUE);	
		 isGetCount=s.getCount();
		 assertFalse(isGetCount);
		 //System.out.println("isGetCount with Integer.MAX_VALUE max: "+isGetCount);
	 }	
}