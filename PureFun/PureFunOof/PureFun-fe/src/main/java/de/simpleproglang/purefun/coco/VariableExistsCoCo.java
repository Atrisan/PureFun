package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._symboltable.VariableSymbol;

import java.util.Optional;


public class VariableExistsCoCo implements CommonExpressionsASTNameExpressionCoCo {

    @Override
    public void check(ASTNameExpression node) {
        if (node.isPresentEnclosingScope()) {
            if (node.getEnclosingScope().resolve(node.getName(),VariableSymbol.KIND).isPresent()) {
                Log.error("Variable not defined " + node.getName());
            }
        }
    }


}
