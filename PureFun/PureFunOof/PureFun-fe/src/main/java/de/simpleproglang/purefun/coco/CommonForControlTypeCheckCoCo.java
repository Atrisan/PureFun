package de.simpleproglang.purefun.coco;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTCommonForControl;
import de.simpleproglang.purefun._ast.ASTForControl;
import de.simpleproglang.purefun._cocos.PureFunASTCommonForControlCoCo;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunPrimitiveType;
import de.simpleproglang.purefun.types.PureFunType;

import java.util.Optional;

public class CommonForControlTypeCheckCoCo implements PureFunASTCommonForControlCoCo {

    @Override
    public void check(ASTCommonForControl node) {
        Optional<PureFunType> conditionType = ExpressionTypesResolver.resolveType(node.getCondition());

        if (!conditionType.isPresent() || !conditionType.get().equals(PureFunPrimitiveType.BOOLEAN)) {
            Log.error("for condition on line " + node.getCondition().get_SourcePositionStart().getLine() + " has not type Boolean");
        }
    }
}
