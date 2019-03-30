package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SwissKnifeCheck extends AbstractCheck {

	private int max = 2;

	public void setMax(int limit) {
	    max = limit;
	  }
	
	@Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.CLASS_DEF};
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }
	  
	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.CLASS_DEF};
	  }

	  

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.IMPLEMENTS_CLAUSE);
		

		int interfaceCounter = objBlock.getChildCount(TokenTypes.IDENT);
		System.out.println("number of interfaces: " + interfaceCounter + "\n");
		
		if (interfaceCounter > max) {
			 log(ast.getLineNo(), "swissknife", max);
		
    	}

	}

}