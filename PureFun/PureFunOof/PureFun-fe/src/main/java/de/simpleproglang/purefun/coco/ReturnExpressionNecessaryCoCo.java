package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTBlockElement;
import de.simpleproglang.purefun._ast.ASTFunction;
import de.simpleproglang.purefun._ast.ASTReturnStatement;
import de.simpleproglang.purefun._ast.ASTStatement;
import de.simpleproglang.purefun._cocos.PureFunASTFunctionCoCo;
import de.simpleproglang.purefun.printer.TypesPrinter;

public class ReturnExpressionNecessaryCoCo implements PureFunASTFunctionCoCo {

    @Override
    public void check(ASTFunction node) {
        boolean hasReturnStatement = false;
        String type = TypesPrinter.printType(node.getType());

        for (ASTBlockElement st : node.getBlockStatement().getBlockElementList()) {
            if (st.isPresentStatement() && st.getStatement() instanceof ASTReturnStatement) {
                ASTReturnStatement returnSt = (ASTReturnStatement) st.getStatement();

                hasReturnStatement = true;

                if (!returnSt.isPresentReturnExpression() && !type.equalsIgnoreCase("Void")) {
                    Log.error("Missing expression after return since return type is not void");
                }
            }
        }

        if (!hasReturnStatement && !type.equalsIgnoreCase("Void")) {
            Log.error("Missing return statement in fun " + node.getName());
        }
    }


}
