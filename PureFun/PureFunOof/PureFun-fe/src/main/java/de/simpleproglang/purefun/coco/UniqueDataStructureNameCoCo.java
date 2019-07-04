package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTCallExpression;
import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._ast.ASTQualifiedNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTQualifiedNameExpressionCoCo;
import de.monticore.symboltable.Symbol;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTDataStructure;
import de.simpleproglang.purefun._cocos.PureFunASTDataStructureCoCo;
import de.simpleproglang.purefun._symboltable.DataStructureSymbol;
import de.simpleproglang.purefun._symboltable.VariableSymbol;


public class UniqueDataStructureNameCoCo implements PureFunASTDataStructureCoCo {

    @Override
    public void check(ASTDataStructure node) {
        if (node.isPresentEnclosingScope()) {
            if (node.getEnclosingScope().resolveMany(node.getName(), DataStructureSymbol.KIND).size() > 1) {
                Log.error("Data Structure defined multiple times" + node.getName());
            }

        }
    }


}
