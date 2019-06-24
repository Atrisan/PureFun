package de.simpleproglang.purefun.printer;

import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.*;
import de.simpleproglang.purefun.types.PureFunPrimitiveType;

public class LiteralTypesPrinter extends AbstractLiteralPrinter {
    private static LiteralTypesPrinter instance;

    private LiteralTypesPrinter() {}

    private static LiteralTypesPrinter getInstance() {
        if (instance == null) {
            instance = new LiteralTypesPrinter();
        }

        return instance;
    }

    public static String printLiteralType(ASTLiteral literal) {
        return getInstance().doPrintLiteral(literal);
    }

    @Override
    protected String doPrintSignedDoubleLiteral(ASTSignedDoubleLiteral literal) {
        return PureFunPrimitiveType.DOUBLE.toString();
    }

    @Override
    protected String doPrintDoubleLiteral(ASTDoubleLiteral literal) {
        return PureFunPrimitiveType.DOUBLE.toString();
    }

    @Override
    protected String doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal) {
        return PureFunPrimitiveType.FLOAT.toString();
    }

    @Override
    protected String doPrintFloatLiteral(ASTFloatLiteral literal) {
        return PureFunPrimitiveType.FLOAT.toString();
    }

    @Override
    protected String doPrintSignedLongliteral(ASTSignedLongLiteral literal) {
        return PureFunPrimitiveType.LONG.toString();
    }

    @Override
    protected String doPrintLongLiteral(ASTLongLiteral literal) {
        return PureFunPrimitiveType.LONG.toString();
    }

    @Override
    protected String doPrintSignedIntLiteral(ASTSignedIntLiteral literal) {
        return PureFunPrimitiveType.INT.toString();
    }

    @Override
    protected String doPrintIntLiteral(ASTIntLiteral literal) {
        return PureFunPrimitiveType.INT.toString();
    }

    @Override
    protected String doPrintStringLiteral(ASTStringLiteral literal) {
        return PureFunPrimitiveType.STRING.toString();
    }

    @Override
    protected String doPrintCharLiteral(ASTCharLiteral literal) {
        return PureFunPrimitiveType.CHAR.toString();
    }

    @Override
    protected String doPrintBooleanLiteral(ASTBooleanLiteral literal) {
        return PureFunPrimitiveType.BOOLEAN.toString();
    }
}
