package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CheckUtil;


public class TypeChecking extends AbstractCheck {

    //The maximum nesting in the code is only 2 and depth is used to check the depth of the condition
	private int max = 2;
    private int depth;

    public void setMax(int limit) {
        max = limit;
    }

    public int getMax() {
        return max;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public void visitToken(DetailAST detailast) {
        if (detailast.getType() == TokenTypes.LITERAL_SWITCH) {
            DetailAST ast = detailast.findFirstToken(TokenTypes.CASE_GROUP);
            depth = 0;
            while (ast != null) {
                if (depth++ > max) {
                    log(detailast.getLineNo(), "typecheck", max);
                    break;
                }
                ast = ast.getNextSibling();
            }
        } else if (detailast.getType() == TokenTypes.LITERAL_IF) {
            if (CheckUtil.isElseIf(detailast)) {
                if (++depth >= max) {
                    log(detailast.getLineNo(), "typecheck", max);
                }
            }
        }
        else if (detailast.getType() == TokenTypes.LITERAL_SWITCH) {
        	if(detailast.getChildCount(TokenTypes.CASE_GROUP) > 1) {
                log(detailast.getLineNo(), "typecheck", max);
        	}
        }
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] { TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_IF };
    }

    @Override
    public int[] getRequiredTokens() {
        return getAcceptableTokens();
    }

}
