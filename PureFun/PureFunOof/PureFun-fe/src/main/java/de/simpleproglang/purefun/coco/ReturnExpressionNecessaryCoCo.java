package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTFunction;
import de.simpleproglang.purefun._ast.ASTReturnStatement;
import de.simpleproglang.purefun._cocos.PureFunASTFunctionCoCo;

public class ReturnExpressionNecessaryCoCo implements PureFunASTFunctionCoCo {

    @Override
    public void check(ASTFunction node) {
        node.getBlockStatement().getStatementList().forEach((st) -> {
            if (st instanceof ASTReturnStatement) {
                ASTReturnStatement returnSt = (ASTReturnStatement) st;

                if (!node.getName().equalsIgnoreCase("Void")) {
                    Log.error("Missing return statement in fun " + node.getName());
                }

                if (!returnSt.isPresentReturnExpression() && !node.getName().equalsIgnoreCase("Void")) {
                    Log.error("Missing expression after return since return type is not void");
                }


            }
        });
    }


}
