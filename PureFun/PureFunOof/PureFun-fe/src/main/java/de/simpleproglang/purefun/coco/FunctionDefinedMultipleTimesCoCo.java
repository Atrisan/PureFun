package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTCallExpression;
import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTCallExpressionCoCo;
import de.monticore.symboltable.Symbol;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTFunction;
import de.simpleproglang.purefun._ast.ASTType;
import de.simpleproglang.purefun._symboltable.FunctionSymbol;
import de.simpleproglang.purefun.types.*;

public class FunctionDefinedMultipleTimesCoCo implements CommonExpressionsASTCallExpressionCoCo {

    @Override
    public void check(ASTCallExpression node) {
        if (node.getExpression() instanceof ASTNameExpression) {
            if (node.isPresentEnclosingScope()) {
                if (node.getEnclosingScope().resolveMany(((ASTNameExpression) node.getExpression()).getName(), FunctionSymbol.KIND).size() > 1) {
                    boolean alreadyDefined = false;
                    for (Symbol sym: node.getEnclosingScope().resolveMany(((ASTNameExpression) node.getExpression()).getName(), FunctionSymbol.KIND)) {
                        if (sym instanceof FunctionSymbol) {
                            if (((FunctionSymbol) sym).getArgumentTypes().size() == node.getArguments().getExpressionList().size() ) {
                                boolean erg = true;
                                for (int i = 0 ; i < node.getArguments().getExpressionList().size(); i++) {
                                    PureFunType symtype = ((FunctionSymbol) sym).getArgumentTypes().get(i);
                                    PureFunType nodetype;
                                    if (ExpressionTypesResolver.resolveType(node.getArguments().getExpressionList().get(i)).isPresent()) {

                                        nodetype = ExpressionTypesResolver.resolveType(node.getArguments().getExpressionList().get(i)).get();
                                        if (!symtype.equals(nodetype)) {
                                            erg = false;
                                        }
                                    }

                                }
                                if (erg) {
                                    if (!alreadyDefined) {
                                        alreadyDefined = true;
                                    } else {
                                        Log.error("Function defined Multiple times" + ((ASTNameExpression) node.getExpression()).getName());
                                    }

                                }

                            }
                        }
                    }

                }

            }
        }
    }


}
