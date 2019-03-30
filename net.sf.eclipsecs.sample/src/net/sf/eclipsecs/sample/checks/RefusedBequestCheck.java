package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class RefusedBequestCheck extends AbstractCheck {

	  private int max = 2;
		 int childMethodNum=0;
		 int fatherMethodNum=0;
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

	  public void setchildMethodNum(int childMethodNum) {
		    this.childMethodNum = childMethodNum;
	  }
	  
	  public int getchildMethodNum() {
		    return childMethodNum;
	  }

	  public void setFatherMethodNum(int fatherMethodNum) {
		    this.fatherMethodNum = fatherMethodNum;
	  }
	  
	  public int getFatherMethodNum() {
		    return fatherMethodNum;
	  }
	  
	  @Override
	  public void visitToken(DetailAST ast) {
		  System.out.println("ast = "+ast);
	        if ( ast == null) {
	            return;
	        }
	        // The first class is the parent
	        DetailAST firstClass = ast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.CLASS_DEF);
	        if (firstClass  == null) {
	            return;
	        }
	        fatherMethodNum = firstClass.findFirstToken(TokenTypes.OBJBLOCK).getChildCount(TokenTypes.METHOD_DEF);
	        System.out.println("fatherMethodNum = "+fatherMethodNum);	        

	        // The second class is the child
	        DetailAST childClass = firstClass.getNextSibling();
	        if (childClass  == null) {
	            return;
	        }
	        childMethodNum= childClass.findFirstToken(TokenTypes.OBJBLOCK).getChildCount(TokenTypes.METHOD_DEF);	        
	        System.out.println("childMethodNum = "+childMethodNum);
	        // If child class has less methods than father class, there is RefusedBequest
	        if (childMethodNum<fatherMethodNum)  {
	        	log(ast, "Refused Bequest", childMethodNum);
	        }
	        


	    
	  }

   
	/**
	 * @param aAST 
	 * @return ancestor CLASS_DEF
	 */
	private DetailAST findParentClassDefBy(DetailAST aAST) {
	    if (null == aAST || aAST.getType() == TokenTypes.CLASS_DEF) {
	        return aAST;
	    } else {
	        return findParentClassDefBy(aAST.getParent());
	    }
	}
	  
	}
