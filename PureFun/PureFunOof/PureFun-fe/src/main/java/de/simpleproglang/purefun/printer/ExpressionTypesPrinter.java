package de.simpleproglang.purefun.printer;

import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun.types.ExpressionTypesResolver;
import de.simpleproglang.purefun.types.PureFunType;

import java.util.Optional;

public class ExpressionTypesPrinter {

    public static final String UNKNOWN_TYPE = "ERROR: UNDEFINED TYPE";
    public static final String TYPE_ERROR = "TYPE ERROR";

    private static ExpressionTypesPrinter instance;

    private ExpressionTypesPrinter() {}

    private static ExpressionTypesPrinter getInstance() {
        if (instance == null) {
            instance = new ExpressionTypesPrinter();
        }
        return instance;
    }

    public String printExpressionType(ASTExpression exp) {
        Optional<PureFunType> type = ExpressionTypesResolver.resolveType(exp);

        if (type.isPresent()) {
            return type.get().getTypeString();
        }

        return TYPE_ERROR;
    }
}
