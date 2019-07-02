package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTCommonExpressionsNodeCoCo;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTIndexAccessExpression;
import de.simpleproglang.purefun._cocos.PureFunASTIndexAccessExpressionCoCo;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.printer.ExpressionTypesPrinter;
import de.simpleproglang.purefun.types.*;

import java.util.Optional;


public class ContainerExistsIndexAccessCoCo implements PureFunASTIndexAccessExpressionCoCo {

    @Override
    public void check(ASTIndexAccessExpression node) {

        Optional<PureFunType> type = ExpressionTypesResolver.resolveType(node.getExpression());
        if (type.isPresent()) {
            if (!(type.get() instanceof PureFunListType || type.get() instanceof PureFunTupleType || type.get() instanceof PureFunMapType)) {
                Log.error("Expression not of container type");
            }
        }
    }


}
