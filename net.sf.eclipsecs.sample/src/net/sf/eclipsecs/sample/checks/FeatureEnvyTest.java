package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import org.junit.Test;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;


//import static org.evosuite.runtime.EvoAssertions.*;


public class FeatureEnvyTest {

	 FeatureEnvyCheck f = new FeatureEnvyCheck();
	 @Test	   
	 public void testSetMax(){
		 f.setMax(Integer.MIN_VALUE);	 
		 f.setMax(-20);
		 f.setMax(-1);
		 f.setMax(2);
		 f.setMax(Integer.MAX_VALUE);
		 f.setMax(0);
	 }	 
	 private FileText text;
	 private FileContents contents;
	 private DetailAST rootAST;
	 
	 @Test
	    public void testGetDefaultTokensNotNull() {
	        assertNotNull("Default tokens should not be null", f.getDefaultTokens());
	    }

	   @Test
	    public void testGetAcceptableTokensNotNull() {
	        assertNotNull("Acceptable tokens should not be null", f.getAcceptableTokens());
	    }

	    @Test
	    public void testGetRequiredTokensNotNull() {
	        assertNotNull("Required tokens should not be null", f.getRequiredTokens());
	    }

	    @Test
	    public void testGetDefaultTokens()  throws Throwable  {      
	        int[] intArray0 = f.getDefaultTokens();
	        assertArrayEquals(new int[] {14}, intArray0);
	        assertFalse(f.getCount());
	    }

	    @Test
	    public void testGetAcceptableTokens()  throws Throwable  {      
	        int[] intArray0 = f.getAcceptableTokens();
	        assertFalse(f.getCount());
	        assertArrayEquals(new int[] {27, 10}, intArray0);
	    }

	    @Test
	    public void testGetRequiredTokens()  throws Throwable  {      
	        int[] intArray0 = f.getRequiredTokens();
	        assertFalse(f.getCount());
	        assertEquals(0, intArray0.length);
	    }
	
	 @Test (expected = NullPointerException.class)
	    public void testVisitToken_1() throws Throwable {
	        DetailAST detailast = rootAST.getNextSibling();
	        	        
	        f.visitToken(detailast);
	        assertTrue(f.getCount());
	    }
	  
	  @Test
	  public void testVisitToken_2()  throws Throwable  {
	      DetailAST detailAST0 = new DetailAST();
	      // Undeclared exception!
	      try { 
	        f.visitToken(detailAST0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	    	  //e.printStackTrace();
	    	  //verifyException("FeatureEnvyTest", e);
	      }
	  }
	  
	 @Test	   
	 public void testGetCount(){
		 boolean isGetCount=f.getCount();;
		 assertFalse(isGetCount);
		 f.setMax(Integer.MIN_VALUE);	
		 isGetCount=f.getCount();
		 assertTrue(isGetCount);
		 //System.out.println("isGetCount with Integer.MIN_VALUE max: "+isGetCount);
		 f.setMax(Integer.MAX_VALUE);	
		 isGetCount=f.getCount();
		 assertFalse(isGetCount);
		 //System.out.println("isGetCount with Integer.MAX_VALUE max: "+isGetCount);
	 }		  

}