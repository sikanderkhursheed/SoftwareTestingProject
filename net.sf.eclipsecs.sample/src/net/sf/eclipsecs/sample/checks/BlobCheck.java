package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class BlobCheck extends AbstractCheck {
	  private int max = 10;  // The maximal number of class_definition, method_definition and variable_definition
	   
	  private int count=0;  //  The number of class_definition, method_definition and variable_definition
	  
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
	    // add the OBJBLOCK direct children numbers of CLASS_DEF, METHOD_DEF and VARIABLE_DEF
	    count = objBlock.getChildCount(TokenTypes.CLASS_DEF) 
	    	  + objBlock.getChildCount(TokenTypes.METHOD_DEF)
		      + objBlock.getChildCount(TokenTypes.VARIABLE_DEF);  
	    // There is Blob (Gob Class) anti-pattern if limit is reached
	    if (count >= max) {
	      log(ast.getLineNo(), "blob", max);
	    }
	  }  

	/**
	 * @param ast
	 * will be used by Junit
	 */
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
		    // add the OBJBLOCK direct children numbers of CLASS_DEF, METHOD_DEF and VARIABLE_DEF
		    count = objBlock.getChildCount(TokenTypes.CLASS_DEF) 
		    	  + objBlock.getChildCount(TokenTypes.METHOD_DEF)
			      + objBlock.getChildCount(TokenTypes.VARIABLE_DEF); 
		    // There is Blob (Gob Class) anti-pattern if limit is reached
		    if (count >= max) {
		    	System.out.println("The source file line "+ast.getLineNo()+ " has a blob (god class) anti-pattern");
		    }
		  }  
}
