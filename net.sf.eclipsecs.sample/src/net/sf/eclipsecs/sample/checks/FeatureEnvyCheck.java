package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class FeatureEnvyCheck extends AbstractCheck{

	private int max = 2;
	private int count = 0;
	public void setMax(int limit) {
	    max = limit;
	  }
	
		
	@Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.METHOD_CALL, TokenTypes.VARIABLE_DEF};
	  }

	  
	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.CLASS_DEF};
	  }

	  

	@Override
	public void visitToken(DetailAST ast) {
		if(ast == null) {
			return;
		}
		else {
			
			DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
			DetailAST varBlock = objBlock.findFirstToken(TokenTypes.VARIABLE_DEF);
			
			while(varBlock!=null ) {
				DetailAST varBlock1= varBlock;
				try {
				  if(varBlock1.findFirstToken(TokenTypes.TYPE).getFirstChild().getType()==TokenTypes.IDENT) {
					count+=1; 
					    	
					} 
				}
				 
				catch(Exception e) {
					
					
				}
					
				if(varBlock.getNextSibling() !=null)
				 varBlock = varBlock.getNextSibling();
				else 
				 break;
				
			}
			
			if(count>max)
				log(ast.getLineNo(), "featureenvy", max);
		}
		
	}

	@Override
	public int[] getRequiredTokens() {
	    return new int[0];
	  }
	
	public boolean getCount() {
		if(count>max)
			return true;
		else
			return false;
		
	}

	}
