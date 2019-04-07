package net.sf.eclipsecs.sample.checks;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class DuplicateCodeCheck extends AbstractCheck{

	@Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.CLASS_DEF};
    }

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}
	
	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

	@Override
	public void visitToken(DetailAST detailast) {
		if(detailast == null)
		{
			return;
		}
		
			ArrayList<DetailAST> list = new ArrayList<DetailAST>();
			DetailAST ast = detailast.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.METHOD_DEF);
			
			while(ast != null) {
				list.add(ast.findFirstToken(TokenTypes.SLIST));
				ast = ast.getNextSibling();
			}
			
			for(int block1 = 0; block1 < list.size(); block1++) {
				for(int block2 = block1+1; block2 < list.size(); block2++) {
					if(list.get(block1).equalsTree((list.get(block2)))) {
						log(ast, "duplicatecode", 0);
					}
				}
			}
		}	
	
	public int detect() {
		System.out.print("Code duplication in the giving program");
		return 1;
	}	
}
