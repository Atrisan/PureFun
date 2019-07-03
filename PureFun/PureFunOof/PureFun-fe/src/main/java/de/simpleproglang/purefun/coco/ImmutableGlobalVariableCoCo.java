package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._ast.ASTQualifiedNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.Symbol;
import de.monticore.symboltable.SymbolNameAndKindPredicate;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun._cocos.PureFunASTAssignmentExpressionCoCo;
import de.simpleproglang.purefun._cocos.PureFunASTFunctionCoCo;
import de.simpleproglang.purefun._symboltable.PureFunScope;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.printer.PureFunExpressionPrinter;
import de.simpleproglang.purefun.printer.TypesPrinter;

import java.util.Collection;
import java.util.Optional;

public class ImmutableGlobalVariableCoCo implements PureFunASTAssignmentExpressionCoCo {

    @Override
    public void check(ASTAssignmentExpression node) {
        VariableSymbol sym = new VariableSymbol("name");
        boolean erg = true;

        if( node.getLeft() instanceof ASTNameExpression) {
            ASTNameExpression exp = (ASTNameExpression) node.getLeft();
            if (node.isPresentEnclosingScope()) {
                for (Symbol element : node.getEnclosingScope().resolveMany(exp.getName(), VariableSymbol.KIND)) {
                    if ( element instanceof  VariableSymbol) {
                        sym = ((VariableSymbol) element);
                        if (!((VariableSymbol) element).getIsGlobal()) {
                            erg = false;
                        }
                    }
                }
            }
        } else if( node.getLeft() instanceof ASTQualifiedNameExpression) {
            ASTQualifiedNameExpression exp = (ASTQualifiedNameExpression) node.getLeft();
            if (node.isPresentEnclosingScope()) {
                for (Symbol element : node.getEnclosingScope().resolveMany(PureFunExpressionPrinter.printExpression(exp.getExpression()), VariableSymbol.KIND)) {
                    if ( element instanceof  VariableSymbol) {
                        sym = ((VariableSymbol) element);
                        if (!((VariableSymbol) element).getIsGlobal()) {
                            erg = false;
                        }
                    }
                }
            }
        }else if( node.getLeft() instanceof ASTIndexAccessExpression) {
            ASTIndexAccessExpression exp = (ASTIndexAccessExpression) node.getLeft();
            if (node.isPresentEnclosingScope()) {
                for (Symbol element : node.getEnclosingScope().resolveMany(PureFunExpressionPrinter.printExpression(exp.getExpression()), VariableSymbol.KIND)) {
                    if ( element instanceof  VariableSymbol) {
                        sym = ((VariableSymbol) element);
                        if (!((VariableSymbol) element).getIsGlobal()) {
                            erg = false;
                        }
                    }
                }
            }
        }
        if (erg){
            Log.error("Reassignment of global variable " + sym.getName());
        }

    }


}
