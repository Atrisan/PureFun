package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTMapKeyExpression;
import de.simpleproglang.purefun._ast.ASTMapValueExpression;
import de.simpleproglang.purefun._cocos.PureFunASTMapKeyExpressionCoCo;
import de.simpleproglang.purefun._cocos.PureFunASTMapValueExpressionCoCo;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunMapType;
import de.simpleproglang.purefun.types.PureFunType;

import java.util.Optional;


public class MapExistsKeyListCoCo implements PureFunASTMapKeyExpressionCoCo {

    @Override
    public void check(ASTMapKeyExpression node) {

        Optional<PureFunType> type = ExpressionTypesResolver.resolveType(node.getExpression());
        if (type.isPresent()) {
            if (!(type.get() instanceof PureFunMapType)) {
                Log.error("Expression not of map type");
            }
        }
    }


}
