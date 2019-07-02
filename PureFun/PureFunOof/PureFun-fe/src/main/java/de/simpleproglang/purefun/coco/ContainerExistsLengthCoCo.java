package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTIndexAccessExpression;
import de.simpleproglang.purefun._ast.ASTLengthExpression;
import de.simpleproglang.purefun._cocos.PureFunASTIndexAccessExpressionCoCo;
import de.simpleproglang.purefun._cocos.PureFunASTLengthExpressionCoCo;
import de.simpleproglang.purefun.types.*;

import java.util.Optional;


public class ContainerExistsLengthCoCo implements PureFunASTLengthExpressionCoCo {

    @Override
    public void check(ASTLengthExpression node) {

        Optional<PureFunType> type = ExpressionTypesResolver.resolveType(node.getExpression());
        if (type.isPresent()) {
            if (!(type.get() instanceof PureFunListType || type.get() instanceof PureFunTupleType || type.get() instanceof PureFunMapType)) {
                Log.error("Expression not of container type");
            }
        }
    }


}
