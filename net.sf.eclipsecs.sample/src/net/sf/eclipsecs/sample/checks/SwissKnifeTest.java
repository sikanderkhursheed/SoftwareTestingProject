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
	 
	 
	 @Before
	    public void setUp() throws Exception {
	        text = new FileText(new File("src/net/sf/eclipses/sample/tests/SwissKnifeScenario.java"), System.getProperty("file.encoding", "UTF-8"));
	        contents = new FileContents(text);
	        rootAST = JavaParser.parse(contents);
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

	
	 @Test (expected = NullPointerException.class)
	    public void testVisitToken() throws Throwable {
	        DetailAST detailast = rootAST.getNextSibling();
	        	        
	        s.visitToken(detailast);
	        assertTrue(s.getCount());
	    }
	  
	  

}