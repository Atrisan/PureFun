package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTCallExpression;
import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTCallExpressionCoCo;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._symboltable.FunctionSymbol;

// TODO: Arguments for Function Differentiation
public class FunctionDefinedMultipleTimesCoCo implements CommonExpressionsASTCallExpressionCoCo {

    @Override
    public void check(ASTCallExpression node) {
        if (node.getExpression() instanceof ASTNameExpression) {
            if (node.isPresentEnclosingScope()) {
                if (node.getEnclosingScope().resolveMany(((ASTNameExpression) node.getExpression()).getName(), FunctionSymbol.KIND).size() > 1) {
                    Log.error("Function defined Multiple times" + ((ASTNameExpression) node.getExpression()).getName());
                }

            }
        }
    }


}
