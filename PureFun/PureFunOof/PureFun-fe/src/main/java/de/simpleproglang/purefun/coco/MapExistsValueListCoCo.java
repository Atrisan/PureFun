package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTIndexAccessExpression;
import de.simpleproglang.purefun._ast.ASTMapValueExpression;
import de.simpleproglang.purefun._cocos.PureFunASTIndexAccessExpressionCoCo;
import de.simpleproglang.purefun._cocos.PureFunASTMapValueExpressionCoCo;
import de.simpleproglang.purefun.types.*;

import java.util.Optional;


public class MapExistsValueListCoCo implements PureFunASTMapValueExpressionCoCo {

    @Override
    public void check(ASTMapValueExpression node) {

        Optional<PureFunType> type = ExpressionTypesResolver.resolveType(node.getExpression());
        if (type.isPresent()) {
            if (!(type.get() instanceof PureFunMapType)) {
                Log.error("Expression not of map type");
            }
        }
    }


}
