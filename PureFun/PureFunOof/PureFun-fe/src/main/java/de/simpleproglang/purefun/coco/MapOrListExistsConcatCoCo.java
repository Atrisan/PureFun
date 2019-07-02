package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTConcatExpression;
import de.simpleproglang.purefun._ast.ASTLengthExpression;
import de.simpleproglang.purefun._cocos.PureFunASTConcatExpressionCoCo;
import de.simpleproglang.purefun._cocos.PureFunASTLengthExpressionCoCo;
import de.simpleproglang.purefun.types.*;

import java.util.Optional;


public class MapOrListExistsConcatCoCo implements PureFunASTConcatExpressionCoCo {

    @Override
    public void check(ASTConcatExpression node) {

        Optional<PureFunType> typeLeft = ExpressionTypesResolver.resolveType(node.getLeft());
        if (typeLeft.isPresent()) {
            if (!(typeLeft.get() instanceof PureFunListType ||  typeLeft.get() instanceof PureFunMapType)) {
                Log.error("Left expression not of container type Map or List");
            }
        }
        Optional<PureFunType> typeRight = ExpressionTypesResolver.resolveType(node.getLeft());
        if (typeRight.isPresent()) {
            if (!(typeRight.get() instanceof PureFunListType ||  typeRight.get() instanceof PureFunMapType)) {
                Log.error("Left expression not of container type Map or List");
            }
        }
        if (typeRight.isPresent() && typeLeft.isPresent()) {
            if (!typeLeft.get().equals(typeRight.get())){
                Log.error("Left and Right expression not of same type");
            }
        }
    }


}
