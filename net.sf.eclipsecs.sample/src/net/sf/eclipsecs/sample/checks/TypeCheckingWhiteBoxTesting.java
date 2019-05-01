package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class TypeCheckingWhiteBoxTesting {

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
	    
	 //Data Flow Analysis - Test 1 Nested If-Else statements with depth 3
	 @Test
	    public void testNestIf() throws Throwable {

	        DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else1 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);
	        DetailAST elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);

	        TypeChecking check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));
	        
	        check.visitToken(if1);
	        check.visitToken(elseIf);

	        else1 = elseIf.findFirstToken(TokenTypes.LITERAL_ELSE);
	        elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);
	        
	        check.visitToken(elseIf);

	        else1 = elseIf.findFirstToken(TokenTypes.LITERAL_ELSE);
	        elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);

	        check.visitToken(else1);
	        assertEquals(3, check.getDepth());
	    }

//	 Data Flow Analysis - Test 2 Switch case with depth 3 
	   @Test
	    public void testSwitchCase() throws Throwable {
	        DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
	        DetailAST switch2 = switch1.getNextSibling();

	         check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));

	        check.visitToken(switch2);
	        assertEquals(3, check.getDepth());
	    }
//		 Data Flow Analysis - Test 3 Switch case and nested if-else with depth 3 
		 @Test
		    public void testNestIfandSwitchCase() throws Throwable {

		        DetailAST detailast = rootAST.getNextSibling();
		        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
		        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
		        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
		        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
		        DetailAST else1 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);
		        DetailAST elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);

		        TypeChecking check = PowerMockito.spy(new TypeChecking());
		        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
		                Mockito.any(Object.class));
		        
		        check.visitToken(if1);
		        check.visitToken(elseIf);

		        else1 = elseIf.findFirstToken(TokenTypes.LITERAL_ELSE);
		        elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);
		        
		        check.visitToken(elseIf);

		        else1 = elseIf.findFirstToken(TokenTypes.LITERAL_ELSE);
		        elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);

		        check.visitToken(else1);
		        assertEquals(3, check.getDepth());
		        
		        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
		        DetailAST case1 = switch1.findFirstToken(TokenTypes.CASE_GROUP);
		        assertEquals(switch1.getType(), TokenTypes.CASE_GROUP);
		        assertEquals(case1.getType(), TokenTypes.CASE_GROUP);

		        DetailAST caseToken = case1.findFirstToken(TokenTypes.LITERAL_CASE);
		        assertEquals(caseToken.getType(), TokenTypes.LITERAL_CASE);
		        
		        
		        check = new TypeChecking();
		        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
		                Mockito.any(Object.class));
		        
		        check.visitToken(switch1);
		        assertEquals(3, check.getDepth());

		    }


}
