package de.simpleproglang.purefun.types;

import de.monticore.literals.literals._ast.*;
import de.simpleproglang.purefun.printer.AbstractLiteralPrinter;

/**
 * Resolves the types of literals. All literals which represent numbers will return type INT.
 *
 * @author Pascal Siewert
 */
public class LiteralTypesResolver extends AbstractLiteralPrinter<PureFunType> {

    private static LiteralTypesResolver instance;

    private LiteralTypesResolver() {}

    private static LiteralTypesResolver getInstance() {
        if (instance == null) {
            instance = new LiteralTypesResolver();
        }

        return instance;
    }

    public static PureFunType resolveType(ASTLiteral literal) {
        return getInstance().doPrintLiteral(literal);
    }

    @Override
    protected PureFunType doPrintSignedDoubleLiteral(ASTSignedDoubleLiteral literal) {
        return PureFunPrimitiveType.INT;
    }

    @Override
    protected PureFunType doPrintDoubleLiteral(ASTDoubleLiteral literal) {
        return PureFunPrimitiveType.DOUBLE;
    }

    @Override
    protected PureFunType doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal) {
        return PureFunPrimitiveType.FLOAT;
    }

    @Override
    protected PureFunType doPrintFloatLiteral(ASTFloatLiteral literal) {
        return PureFunPrimitiveType.FLOAT;
    }

    @Override
    protected PureFunType doPrintSignedLongliteral(ASTSignedLongLiteral literal) {
        return PureFunPrimitiveType.LONG;
    }

    @Override
    protected PureFunType doPrintLongLiteral(ASTLongLiteral literal) {
        return PureFunPrimitiveType.LONG;
    }

    @Override
    protected PureFunType doPrintSignedIntLiteral(ASTSignedIntLiteral literal) {
        return PureFunPrimitiveType.INT;
    }

    @Override
    protected PureFunType doPrintIntLiteral(ASTIntLiteral literal) {
        return PureFunPrimitiveType.INT;
    }

    @Override
    protected PureFunType doPrintStringLiteral(ASTStringLiteral literal) {
        return PureFunPrimitiveType.STRING;
    }

    @Override
    protected PureFunType doPrintCharLiteral(ASTCharLiteral literal) {
        return PureFunPrimitiveType.CHAR;
    }

    @Override
    protected PureFunType doPrintBooleanLiteral(ASTBooleanLiteral literal) {
        return PureFunPrimitiveType.BOOLEAN;
    }
}
