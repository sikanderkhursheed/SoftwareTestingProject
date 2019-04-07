package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class TypeCheckingTest {

	 TypeChecking check = new TypeChecking();
	 
	 private FileText text;
	 private FileContents contents;
	 private DetailAST rootAST;
	 
	 
	 @Before
	    public void setUp() throws Exception {
	        text = new FileText(new File("src/net/sf/eclipses/sample/tests/TypeCheckTestScenario.java"), System.getProperty("file.encoding", "UTF-8"));
	        contents = new FileContents(text);
	        rootAST = JavaParser.parse(contents);
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

	
	 @Test
	    public void testVisitIfToken() throws Throwable {
	        DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);

	        check.visitToken(if1);
	        assertEquals(0, check.getDepth());
	    }
	 
	  @Test
	    public void testVisitElseIfToken() throws Throwable {
		  	DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else1 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);
	        DetailAST elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);

	        check.visitToken(if1);
	        check.visitToken(else1);
	        check.visitToken(elseIf);
	        assertEquals(1, check.getDepth());
	    }
	 
	 @Test
	 	public void testNestedIFElse() {
		 DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else1 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);
	        DetailAST elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else2 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);

	        check.visitToken(if1);
	        check.visitToken(else1);
	        check.visitToken(elseIf);
	        check.visitToken(else2);
	        assertEquals(2, check.getDepth());
	 }
	  
	 @Test
	    public void testVisitSwitchToken() throws Throwable {
		 DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
	       // DetailAST case1 = switch1.findFirstToken(TokenTypes.CASE_GROUP);
	        assertEquals(switch1.getType(), TokenTypes.CASE_GROUP);
	        //detailast.getNextSibling();
	        check.visitToken(switch1);
	        assertEquals(3, check.getDepth());
	    }
	 

}
