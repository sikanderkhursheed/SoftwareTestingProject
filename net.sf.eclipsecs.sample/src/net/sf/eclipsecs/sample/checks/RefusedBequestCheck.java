package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class RefusedBequestCheck extends AbstractCheck {
	 int childMethodNum=0;                 // Method number in child class
	 int fatherMethodNum=0;	               // Method number in father class				
	 String fatherClassName="";            // Father class name
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
	  
	  public void setFatherClassName(String fatherClassName) {
		    this.fatherClassName = fatherClassName;
	  }
	  
	  public String getFatherClassName() {
		    return fatherClassName;
	  }

		/**
		 * @param ast
		 * 
		 */
	  @Override
	  public void visitToken(DetailAST ast) {
		    // don't handle null ast
	        if ( ast == null) {
	            return;
	        }
	        // find the first checked class (AST)
	        DetailAST checkClassAST = ast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.CLASS_DEF);
	        // reverse and check all classes 
			while(checkClassAST != null && checkClassAST.toString().startsWith("CLASS_DEF[") ) {
				// find father class name
				// checked class (checkClassAST) extends from father class
				if (checkClassAST.getChildCount(TokenTypes.EXTENDS_CLAUSE)>0)
		        {
		        	fatherClassName=checkClassAST.findFirstToken(TokenTypes.EXTENDS_CLAUSE).findFirstToken(TokenTypes.IDENT).getText();
		        }    
		        else if (checkClassAST.getChildCount(TokenTypes.IMPLEMENTS_CLAUSE)>0)       // checked class (checkClassAST) implements from father interface
		        {
		        	fatherClassName=checkClassAST.findFirstToken(TokenTypes.IMPLEMENTS_CLAUSE).findFirstToken(TokenTypes.IDENT).getText();
		        }	
				// skip null and empty father class
				if (fatherClassName!=null && fatherClassName.length()>0 )
				{   // get method number of child class (checked class (checkClassAST))
					childMethodNum=checkClassAST.findFirstToken(TokenTypes.OBJBLOCK).getChildCount(TokenTypes.METHOD_DEF);
					// get method number of father class whose name is $fatherClassName
					fatherMethodNum=findMethodNumberByClassName(ast, fatherClassName);
					// If child class has less methods than father class, there is Refused Bequest anti-pattern
					if (childMethodNum<fatherMethodNum)
					{
						log(checkClassAST, "Refused Bequest", childMethodNum);
					}
				}					
				// find the next checked class from the sibling of current checked class 
				checkClassAST = checkClassAST.getNextSibling();
			}
	  }

   
	/**
	 * @param aAST, className 
	 * @return The number of methods in the class whose name is $className
	 */
	private int findMethodNumberByClassName(DetailAST theAST, String className) {
		if ( theAST == null) {
            return 0;
        }
        int methodNumber=0;    
       // find the first class (AST)
        DetailAST theClassAST = theAST.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.CLASS_DEF);
        // reverse all classes (ASTs)
		while(theClassAST != null && theClassAST.toString().startsWith("CLASS_DEF[") ) {
			// find the class whose name is $className
			if (className.equals(theClassAST.findFirstToken(TokenTypes.IDENT).getText()))
			{
				// get and return the number of methods in the class whose name is $className
				methodNumber=theClassAST.findFirstToken(TokenTypes.OBJBLOCK).getChildCount(TokenTypes.METHOD_DEF);
				return methodNumber;
			}
			// find the next class from the sibling of current class 
			theClassAST = theClassAST.getNextSibling();
		}
		return methodNumber;
	}
	  
	}
