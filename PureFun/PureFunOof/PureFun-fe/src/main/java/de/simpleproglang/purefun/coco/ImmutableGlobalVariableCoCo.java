package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._ast.ASTQualifiedNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.monticore.symboltable.GlobalScope;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun._cocos.PureFunASTAssignmentExpressionCoCo;
import de.simpleproglang.purefun._cocos.PureFunASTFunctionCoCo;
import de.simpleproglang.purefun._symboltable.PureFunScope;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.printer.PureFunExpressionPrinter;
import de.simpleproglang.purefun.printer.TypesPrinter;

import java.util.Optional;

public class ImmutableGlobalVariableCoCo implements PureFunASTAssignmentExpressionCoCo {

    @Override
    public void check(ASTAssignmentExpression node) {
        Optional<VariableSymbol> sym = Optional.empty();

        if( node.getLeft() instanceof ASTNameExpression) {
            ASTNameExpression exp = (ASTNameExpression) node.getLeft();

            sym = node.getEnclosingScope().resolve(exp.getName(), VariableSymbol.KIND);
        } else if( node.getLeft() instanceof ASTQualifiedNameExpression) {
            ASTQualifiedNameExpression exp = (ASTQualifiedNameExpression) node.getLeft();

            sym = node.getEnclosingScope().resolve(PureFunExpressionPrinter.printExpression(exp.getExpression()), VariableSymbol.KIND);
        }else if( node.getLeft() instanceof ASTIndexAccessExpression) {
            ASTIndexAccessExpression exp = (ASTIndexAccessExpression) node.getLeft();

            sym = node.getEnclosingScope().resolve(PureFunExpressionPrinter.printExpression(exp.getExpression()), VariableSymbol.KIND);
        }
        if (sym.isPresent()){
            if (sym.get().getIsGlobal()) {
                Log.error("Reassignement of global variable " + sym.get().getName());
            }
        }

    }


}
