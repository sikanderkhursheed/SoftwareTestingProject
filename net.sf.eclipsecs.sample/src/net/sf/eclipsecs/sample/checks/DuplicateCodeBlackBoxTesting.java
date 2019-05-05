package net.sf.eclipsecs.sample.checks;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(TypeChecking.class)

class DuplicateCodeBlackBoxTesting {

	DuplicateCodeCheck check = new DuplicateCodeCheck();
	
	private FileText text;
    private FileContents contents;
    private DetailAST rootAST;
    
    
    @Test
    public void CauseEffectiveTesting() throws Throwable {
        text = new FileText(new File("src/net/sf/eclipses/sample/tests/DuplicateCodeTestScenario.java"),
                System.getProperty("file.encoding", "UTF-8"));
        contents = new FileContents(text);
        rootAST = JavaParser.parse(contents);
        

        DetailAST classDef = rootAST.getNextSibling();
        assertEquals(classDef.getType(), TokenTypes.CLASS_DEF);

        DetailAST obj = classDef.findFirstToken(TokenTypes.OBJBLOCK);
        assertEquals(obj.getType(), TokenTypes.OBJBLOCK);

        DetailAST methodDef = obj.findFirstToken(TokenTypes.METHOD_DEF);
        assertEquals(methodDef.getType(), TokenTypes.METHOD_DEF);

        DetailAST block1 = methodDef.findFirstToken(TokenTypes.SLIST);
        assertEquals(block1.getType(), TokenTypes.SLIST);
        
        DetailAST methodDef2 = methodDef.getNextSibling();
        assertEquals(methodDef2.getType(), TokenTypes.METHOD_DEF);
        
        DetailAST block2 = methodDef2.findFirstToken(TokenTypes.SLIST);
        assertEquals(block2.getType(), TokenTypes.SLIST);
        
        
        assertTrue(block1.equalsTree(block2));                  
    }


    

}
