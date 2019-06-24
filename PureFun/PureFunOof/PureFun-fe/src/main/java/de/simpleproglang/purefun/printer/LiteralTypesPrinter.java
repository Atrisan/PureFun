package de.simpleproglang.purefun.printer;

import de.monticore.literals.literals._ast.*;

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
        return PureFunType.DOUBLE.toString();
    }

    @Override
    protected String doPrintDoubleLiteral(ASTDoubleLiteral literal) {
        return PureFunType.DOUBLE.toString();
    }

    @Override
    protected String doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal) {
        return PureFunType.FLOAT.toString();
    }

    @Override
    protected String doPrintFloatLiteral(ASTFloatLiteral literal) {
        return PureFunType.FLOAT.toString();
    }

    @Override
    protected String doPrintSignedLongliteral(ASTSignedLongLiteral literal) {
        return PureFunType.LONG.toString();
    }

    @Override
    protected String doPrintLongLiteral(ASTLongLiteral literal) {
        return PureFunType.LONG.toString();
    }

    @Override
    protected String doPrintSignedIntLiteral(ASTSignedIntLiteral literal) {
        return PureFunType.INT.toString();
    }

    @Override
    protected String doPrintIntLiteral(ASTIntLiteral literal) {
        return PureFunType.INT.toString();
    }

    @Override
    protected String doPrintStringLiteral(ASTStringLiteral literal) {
        return PureFunType.STRING.toString();
    }

    @Override
    protected String doPrintCharLiteral(ASTCharLiteral literal) {
        return PureFunType.CHAR.toString();
    }

    @Override
    protected String doPrintBooleanLiteral(ASTBooleanLiteral literal) {
        return PureFunType.BOOLEAN.toString();
    }
}
