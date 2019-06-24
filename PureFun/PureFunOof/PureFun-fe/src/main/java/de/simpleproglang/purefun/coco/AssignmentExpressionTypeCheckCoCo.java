package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTCallExpression;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.expressions.prettyprint2.AssignmentExpressionsPrettyPrinter;
import de.monticore.prettyprint.IndentPrinter;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTAssignmentExpression;
import de.simpleproglang.purefun._ast.ASTIndexAccessExpression;
import de.simpleproglang.purefun._cocos.PureFunASTAssignmentExpressionCoCo;


public class AssignmentExpressionTypeCheckCoCo implements PureFunASTAssignmentExpressionCoCo {

    @Override
    public void check(ASTAssignmentExpression node) {
        if (node.getRight() instanceof ASTCallExpression) {
            Log.info(((ASTCallExpression) node.getRight()).geteMethodSymbol().getName(), "Call Expression name:");
        }



    }

    public static String getExpressionType(ASTExpression expression) {
        return null;
    }
}
