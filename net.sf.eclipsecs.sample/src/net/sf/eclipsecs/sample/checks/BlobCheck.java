package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class BlobCheck extends AbstractCheck {
	  private int max = 20;  // The number threshold of method_definition and variable_definition
	   
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
	  public void setCount(int count) {
		    this.count = count;
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
	    // add the OBJBLOCK direct children numbers of METHOD_DEF and VARIABLE_DEF
	    count = objBlock.getChildCount(TokenTypes.METHOD_DEF)
		      + objBlock.getChildCount(TokenTypes.VARIABLE_DEF);  
	    // There is Blob (Gob Class) anti-pattern if limit is reached
	    //System.out.println("count ="+count+" max ="+max); 
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
		    // add the OBJBLOCK direct children numbers of METHOD_DEF and VARIABLE_DEF
		    count = objBlock.getChildCount(TokenTypes.METHOD_DEF)
			      + objBlock.getChildCount(TokenTypes.VARIABLE_DEF); 
		    // There is Blob (Gob Class) anti-pattern if limit (20) is reached according to the rule "a number of methods and attributes higher than 20"
		    if (count >= max) {
		    	System.out.println("The source file line "+ast.getLineNo()+ " has a blob (god class) anti-pattern");
		    }
		  }  
}
