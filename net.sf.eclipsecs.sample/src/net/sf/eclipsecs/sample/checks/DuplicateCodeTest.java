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

public class DuplicateCodeTest {

	DuplicateCodeCheck check = new DuplicateCodeCheck();

	 private FileText text;
	 private FileContents contents;
	 private DetailAST rootAST;
	 
	 @Before
	    public void setUp() throws Exception {
	        text = new FileText(new File("src/net/sf/eclipses/sample/tests/DuplicateCodeTestScenario.java"), System.getProperty("file.encoding", "UTF-8"));
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
	 public void testDuplicateCode(){
       DetailAST detailast = rootAST.getNextSibling();
       DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
       DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
       DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
       DetailAST block2 = slist.getNextSibling();

       check.visitToken(block2);
       assertEquals(0, check.detect());

       
	 } 
}
