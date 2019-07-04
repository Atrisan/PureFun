package de.simpleproglang.purefun.coco.typechecking;

import de.monticore.symboltable.GlobalScope;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTVariable;
import de.simpleproglang.purefun._cocos.PureFunASTVariableCoCo;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunType;

import java.util.Optional;

public class VariableAssignmentTypeCheckCoCo implements PureFunASTVariableCoCo {

    private ModuleSymbol symbolTable;

    public VariableAssignmentTypeCheckCoCo(ModuleSymbol symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public void check(ASTVariable node) {
        Optional<VariableSymbol> symbol = symbolTable.getSpannedScope().resolve(node.getName(), VariableSymbol.KIND);

        if (!symbol.isPresent()) {
            Log.error("VariableSymbol not present (line " + node.get_SourcePositionStart().getLine() + ")");
        }

        if (node.isPresentExpression()) {
            Optional<PureFunType> type = ExpressionTypesResolver.resolveType(node.getExpression());

            if (!type.isPresent() || !type.get().equals(symbol.get().getType())) {
                Log.error("Type error: expression not of type " + symbol.get().getType().getTypeString() + " on line " + node.getExpression().get_SourcePositionStart().getLine());
            } else {
                Log.info("Recognized type: " + type.get().getTypeString(), "VariableAssignmentTypeCheckCoCo");
            }
        }
    }
}
