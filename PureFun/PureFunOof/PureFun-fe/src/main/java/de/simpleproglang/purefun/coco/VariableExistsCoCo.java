package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.monticore.symboltable.Symbol;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun._symboltable.FunctionSymbol;

import java.util.Optional;


public class VariableExistsCoCo implements CommonExpressionsASTNameExpressionCoCo {

    @Override
    public void check(ASTNameExpression node) {
        boolean erg = true;
        if (node.isPresentEnclosingScope()) {
            if (node.getEnclosingScope().resolveMany(node.getName(), VariableSymbol.KIND).size() < 1 && node.getEnclosingScope().resolveMany(node.getName(), FunctionSymbol.KIND).size() < 1) {
                Log.error("Variable/Function not defined " + node.getName());
            }

        }
    }


}
