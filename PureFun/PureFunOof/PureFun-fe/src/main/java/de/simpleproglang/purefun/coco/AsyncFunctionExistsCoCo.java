package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTCallExpression;
import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTCallExpressionCoCo;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTAsyncExpression;
import de.simpleproglang.purefun._cocos.PureFunASTAsyncExpressionCoCo;
import de.simpleproglang.purefun._symboltable.FunctionSymbol;


public class AsyncFunctionExistsCoCo implements PureFunASTAsyncExpressionCoCo {

    @Override
    public void check(ASTAsyncExpression node) {
        if (node.isPresentEnclosingScope()) {
            if (node.getEnclosingScope().resolveMany( node.getName(), FunctionSymbol.KIND).size() < 1) {
                Log.error("Function not defined " + (node.getName()));
            }
        }

    }


}
