package de.simpleproglang.purefun.printer;

import de.monticore.literals.literals._ast.*;
import de.simpleproglang.purefun.types.PureFunPrimitiveType;

public class LiteralTypesPrinter extends AbstractLiteralPrinter<String> {
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
        return PureFunPrimitiveType.DOUBLE.getTypeString();
    }

    @Override
    protected String doPrintDoubleLiteral(ASTDoubleLiteral literal) {
        return PureFunPrimitiveType.DOUBLE.getTypeString();
    }

    @Override
    protected String doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal) {
        return PureFunPrimitiveType.FLOAT.getTypeString();
    }

    @Override
    protected String doPrintFloatLiteral(ASTFloatLiteral literal) {
        return PureFunPrimitiveType.FLOAT.getTypeString();
    }

    @Override
    protected String doPrintSignedLongliteral(ASTSignedLongLiteral literal) {
        return PureFunPrimitiveType.LONG.getTypeString();
    }

    @Override
    protected String doPrintLongLiteral(ASTLongLiteral literal) {
        return PureFunPrimitiveType.LONG.getTypeString();
    }

    @Override
    protected String doPrintSignedIntLiteral(ASTSignedIntLiteral literal) {
        return PureFunPrimitiveType.INT.getTypeString();
    }

    @Override
    protected String doPrintIntLiteral(ASTIntLiteral literal) {
        return PureFunPrimitiveType.INT.getTypeString();
    }

    @Override
    protected String doPrintStringLiteral(ASTStringLiteral literal) {
        return PureFunPrimitiveType.STRING.getTypeString();
    }

    @Override
    protected String doPrintCharLiteral(ASTCharLiteral literal) {
        return PureFunPrimitiveType.CHAR.getTypeString();
    }

    @Override
    protected String doPrintBooleanLiteral(ASTBooleanLiteral literal) {
        return PureFunPrimitiveType.BOOLEAN.getTypeString();
    }
}
