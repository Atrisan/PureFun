package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTCallExpression;
import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._ast.ASTQualifiedNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTQualifiedNameExpressionCoCo;
import de.monticore.symboltable.Scopes;
import de.monticore.symboltable.Symbol;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTDataStructure;
import de.simpleproglang.purefun._ast.ASTVariable;
import de.simpleproglang.purefun._symboltable.DataStructureSymbol;
import de.simpleproglang.purefun._symboltable.FunctionSymbol;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.printer.TypesPrinter;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;

import java.lang.reflect.Type;
import java.util.Optional;


public class DataStructureSubVariableExistsCoCo implements CommonExpressionsASTQualifiedNameExpressionCoCo {

    @Override
    public void check(ASTQualifiedNameExpression node) {
        if (node.isPresentEnclosingScope()) {
            String TypeName = "";
            if (node.getExpression() instanceof ASTCallExpression) {
                if (((ASTCallExpression) node.getExpression()).getExpression() instanceof ASTNameExpression) {
                    String name = ((ASTNameExpression) ((ASTCallExpression) node.getExpression()).getExpression()).getName();
                    if (node.getEnclosingScope().resolveMany(name,FunctionSymbol.KIND).size() == 1) {
                        Symbol sym = node.getEnclosingScope().resolve(name,FunctionSymbol.KIND).get();
                        if (sym instanceof FunctionSymbol) {
                            TypeName = ((FunctionSymbol) sym).getReturnType().getTypeString();
                        }
                    } else {
                        Log.warn("Please rename/create function " + name);
                    }
                }
            } else if (node.getExpression() instanceof ASTNameExpression) {
                String name = ((ASTNameExpression) node.getExpression()).getName();
                if (node.getEnclosingScope().resolveMany(name,VariableSymbol.KIND).size() == 1) {
                    Symbol sym = node.getEnclosingScope().resolve(name,VariableSymbol.KIND).get();
                    if (sym instanceof VariableSymbol) {
                        TypeName = ((VariableSymbol) sym).getType();
                    }
                } else {
                    Log.warn("Please rename/create Variable " + name);
                }
            }
            if (node.getEnclosingScope().resolve(TypeName, DataStructureSymbol.KIND).isPresent()) {
                Optional<DataStructureSymbol> sym = node.getEnclosingScope().resolve(TypeName, DataStructureSymbol.KIND);
                if (sym.isPresent()) {
                    if((sym.get().getDataStructureNode().isPresent())) {
                        ASTDataStructure dat = sym.get().getDataStructureNode().get();
                        boolean erg = true;
                        for (ASTVariable var: dat.getVariableList() )  {
                            if(var.getName().equals(node.getName())) {
                                erg = false;
                            }
                        }
                        if (erg) {
                            Log.error("DataStructure" + dat.getName() + "does not contain Variable:" + ((ASTNameExpression) node.getExpression()).getName());
                        }
                    }
                }
            } else {
                if (node.getExpression() instanceof ASTCallExpression) {
                    if (((ASTCallExpression) node.getExpression()).getExpression() instanceof ASTNameExpression)
                    Log.error("DataStructure not defined for Function:" + ((ASTNameExpression) ((ASTCallExpression) node.getExpression()).getExpression()).getName());
                } else if (node.getExpression() instanceof ASTNameExpression) {
                    Log.error("DataStructure not defined for Variable:" + ((ASTNameExpression) node.getExpression()).getName());
                }

            }

        }
    }


}
