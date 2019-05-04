package net.sf.eclipsecs.sample.checks;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(TypeChecking.class)

public class TypeCheckingBlackBoxTesting {

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

	 
	 // Test 1 Equivalence class testing - Depth of if statement 0
	 @Test
	    public void testIfDepth0() throws Throwable {
	        
	        DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
	        
	        assertEquals(null, if1);

	         check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));
	        assertEquals(0, check.getDepth());
	    }
	 
	 // Test 1 Equivalence class testing - Depth of switch statement 0
	 @Test
	    public void testSwitchDepth0() throws Throwable {

		 	DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
	        
	        assertEquals(null, switch1);
	         check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));
	        assertEquals(0, check.getDepth());
	    }
	 
	 // Test 2 Strong Equivalence class testing - Depth of if-else statement 2

	    @Test
	    public void testIfDepth2() throws Throwable {

		  	DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST if1 = slist.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else1 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);
	        DetailAST elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);
	        DetailAST else2 = if1.findFirstToken(TokenTypes.LITERAL_ELSE);


	        TypeChecking check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));

	        check.visitToken(if1);
	        check.visitToken(else1);
	        check.visitToken(elseIf);
	        check.visitToken(else2);
	        assertEquals(2, check.getDepth());
	    }

		 // Test 2 Strong Equivalence class testing - Depth of switch statement 2
	    
	    @Test
	    public void testSwtichDepth2() throws Throwable {

	        DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
	        DetailAST case1 = switch1.findFirstToken(TokenTypes.CASE_GROUP);
	        assertEquals(switch1.getType(), TokenTypes.CASE_GROUP);
	        assertEquals(case1.getType(), TokenTypes.CASE_GROUP);

	        DetailAST caseToken = case1.findFirstToken(TokenTypes.LITERAL_CASE);
	        assertEquals(caseToken.getType(), TokenTypes.LITERAL_CASE);
	        
	        
	        TypeChecking check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));
	        
	        check.visitToken(switch1);
	        assertEquals(2, check.getDepth());
	    }

		 // Test 3 Value Analysis testing - Depth of if-else statement 4
	    @Test
	    public void testIfDepth4() throws Throwable {

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
	        check.visitToken(elseIf);

	        else1 = elseIf.findFirstToken(TokenTypes.LITERAL_ELSE);
	        elseIf = else1.findFirstToken(TokenTypes.LITERAL_IF);
	        check.visitToken(elseIf);

	        check.visitToken(else1);
	        assertEquals(3, check.getDepth());
	    }
	    
		 // Test 3 Value Analysis testing - Depth of switch statement 4
	    @Test
	    public void testSwtichDepth4() throws Throwable {

	        DetailAST detailast = rootAST.getNextSibling();
	        DetailAST block = detailast.findFirstToken(TokenTypes.OBJBLOCK);
	        DetailAST method = block.findFirstToken(TokenTypes.METHOD_DEF);
	        DetailAST slist = method.findFirstToken(TokenTypes.SLIST);
	        DetailAST switch1 = slist.findFirstToken(TokenTypes.LITERAL_SWITCH);
	        DetailAST case1 = switch1.findFirstToken(TokenTypes.CASE_GROUP);
	        DetailAST caseToken = case1.findFirstToken(TokenTypes.LITERAL_CASE);
	        assertEquals(caseToken.getType(), TokenTypes.LITERAL_CASE);
	        
	        
	        TypeChecking check = PowerMockito.spy(new TypeChecking());
	        PowerMockito.doNothing().when(check, "log", Mockito.any(Integer.class), Mockito.any(String.class),
	                Mockito.any(Object.class));

	        check.visitToken(switch1);
	        assertEquals(4, check.getDepth());
	    }
}
