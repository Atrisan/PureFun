package de.simpleproglang.purefun.coco.typechecking;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTIfStatement;
import de.simpleproglang.purefun._cocos.PureFunASTIfStatementCoCo;
import de.simpleproglang.purefun._symboltable.DataStructureSymbol;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunCommonType;
import de.simpleproglang.purefun.types.PureFunPrimitiveType;
import de.simpleproglang.purefun.types.PureFunType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class IfTypeCheckCoCo implements PureFunASTIfStatementCoCo {

    @Override
    public void check(ASTIfStatement node) {
        Optional<PureFunType> conditionType = ExpressionTypesResolver.resolveType(node.getCondition());

        if (!conditionType.isPresent() || !conditionType.get().equals(PureFunPrimitiveType.BOOLEAN)) {
            Log.error("if condition on line " + node.getCondition().get_SourcePositionStart().getLine() + " has not type Boolean");
        }
    }
}
