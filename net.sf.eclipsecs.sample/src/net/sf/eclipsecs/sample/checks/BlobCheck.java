package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class BlobCheck extends AbstractCheck {
	  private int max = 10;  // The maximal number of method_definition, loop, variable_definition and method_call
	   
	  private int count=0;  //  The number of method_definition, loop, variable_definition and method_call
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	  }

	  public void setMax(int limit) {
	    this.max = limit;
	  }
	  public int getMax() {
		    return max;
	  }
	  
	  public int getCount() {
		    return count;
	  }


	  @Override
	  public void visitToken(DetailAST ast) {
		// don't handle null ast
		if ( ast == null) {
		    return;
		}
	    // find the OBJBLOCK node below the ast
	    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
	    // don't handle null objBlock
        if ( objBlock == null) {
            return;
        }
	    // count the number of direct children of the OBJBLOCK having METHOD_DEFS, 
	    //LITERAL_FOR, LITERAL_WHILE, DO_WHILE, VARIABLE_DEF and METHOD_CALL
	    count = objBlock.getChildCount(TokenTypes.METHOD_DEF)    + objBlock.getChildCount(TokenTypes.LITERAL_FOR)
		      + objBlock.getChildCount(TokenTypes.LITERAL_WHILE) + objBlock.getChildCount(TokenTypes.DO_WHILE)
		      + objBlock.getChildCount(TokenTypes.VARIABLE_DEF)  + objBlock.getChildCount(TokenTypes.METHOD_CALL);
	 // There is Blob (Gob Class) anti-pattern if limit is reached
	    if (count >= max) {
	      log(ast.getLineNo(), "blob", max);
	    }
	  }  

      //  public void visitTokenWithLog(DetailAST ast) will be tested using Junit
	  public void visitTokenWithoutLog(DetailAST ast) {
			// don't handle null ast
			if ( ast == null) {
			    return;
			}
		    // find the OBJBLOCK node below the ast
		    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
		    // don't handle null objBlock
	        if ( objBlock == null) {
	            return;
	        }
		    // count the number of direct children of the OBJBLOCK having METHOD_DEFS, 
		    //LITERAL_FOR, LITERAL_WHILE, DO_WHILE, VARIABLE_DEF and METHOD_CALL
		    count = objBlock.getChildCount(TokenTypes.METHOD_DEF)	 + objBlock.getChildCount(TokenTypes.LITERAL_FOR)
			      + objBlock.getChildCount(TokenTypes.LITERAL_WHILE) + objBlock.getChildCount(TokenTypes.DO_WHILE)
			      + objBlock.getChildCount(TokenTypes.VARIABLE_DEF)	 + objBlock.getChildCount(TokenTypes.METHOD_CALL);
		    // There is Blob (Gob Class) anti-pattern if limit is reached
		    if (count >= max) {
		    	System.out.println(ast.getText()+ "has a blob (god class) anti-pattern");
		    }
		  }  
}
