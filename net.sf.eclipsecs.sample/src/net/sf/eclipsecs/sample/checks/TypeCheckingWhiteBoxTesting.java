package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(TypeChecking.class)

class TypeCheckingWhiteBoxTesting {
	
	 TypeChecking check = new TypeChecking();
	 
	 private FileText text;
	 private FileContents contents;
	 private DetailAST rootAST;
	   
	 //Data Flow Analysis - Test 1 Nested If-Else statements with depth 3	 
	 @Test
	 	public void testNestedIFElse() throws Throwable {
		 text = new FileText(new File("src/net/sf/eclipses/sample/tests/TypeCheckTestScenario.java"),
	                System.getProperty("file.encoding", "UTF-8"));
	        contents = new FileContents(text);
	        rootAST = JavaParser.parse(contents);
		 DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else1 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);
	        DetailAST elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else2 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);

//	        TypeChecking check = PowerMockito.spy(new TypeChecking());
//	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
//	                Mockito.any(Object.class));

	        check.visitToken(if1);
	        check.visitToken(else1);
	        check.visitToken(elseIf);
	        check.visitToken(else2);
	        assertEquals(3, check.getDepth());
	 }
		 
//		 Data Flow Analysis - Test 2 Switch case with depth 3 
		   @Test
		    public void testSwitchCase() throws Throwable {
				 text = new FileText(new File("src/net/sf/eclipses/sample/tests/TypeCheckTestScenario.java"),
			                System.getProperty("file.encoding", "UTF-8"));
			        contents = new FileContents(text);
			        rootAST = JavaParser.parse(contents);
		        DetailAST detailast = rootAST.getNextSibling();
		        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
		        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
		        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
		        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
		        DetailAST case1 = switch1.findFirstToken(TokenTypes.CASE_GROUP);
		        assertEquals(switch1.getType(), TokenTypes.CASE_GROUP);
		        assertEquals(case1.getType(), TokenTypes.CASE_GROUP);
		        
		       // DetailAST switch2 = switch1.getNextSibling();

		         check = PowerMockito.spy(new TypeChecking());
		        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
		                Mockito.any(Object.class));

		        check.visitToken(switch1);
		        assertEquals(3, check.getDepth());
		    }
		   
		   @Test
		    public void testVisitElseIfToken() throws Throwable {
				 text = new FileText(new File("src/net/sf/eclipses/sample/tests/TypeCheckTestScenario.java"),
			                System.getProperty("file.encoding", "UTF-8"));
			        contents = new FileContents(text);
			        rootAST = JavaParser.parse(contents);
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

		        assertEquals(1, check.getDepth());
		    }
		   
		   
//			 Data Flow Analysis - Test 3 Switch case and nested if-else with depth 3 
			 @Test
			    public void testNestIfandSwitchCase() throws Throwable {
				 text = new FileText(new File("src/net/sf/eclipses/sample/tests/TypeCheckTestScenario.java"),
			                System.getProperty("file.encoding", "UTF-8"));
			        contents = new FileContents(text);
			        rootAST = JavaParser.parse(contents);
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
