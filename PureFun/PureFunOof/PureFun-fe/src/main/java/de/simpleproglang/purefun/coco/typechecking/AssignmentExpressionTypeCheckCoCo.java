package de.simpleproglang.purefun.coco.typechecking;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTAssignmentExpression;
import de.simpleproglang.purefun._cocos.PureFunASTAssignmentExpressionCoCo;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunType;

import java.util.Optional;


public class AssignmentExpressionTypeCheckCoCo implements PureFunASTAssignmentExpressionCoCo {

    @Override
    public void check(ASTAssignmentExpression node) {
        Optional<PureFunType> type = ExpressionTypesResolver.resolveType(node);

        if (!type.isPresent()) {
            Log.error("type error in assignment on line " + node.get_SourcePositionStart().getLine());
        }
    }
}
