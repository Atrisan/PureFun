package de.simpleproglang.purefun.types;

import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun.printer.AbstractTypesPrinter;

public class PureFunTypeConverter extends AbstractTypesPrinter<PureFunType> {

    private static PureFunTypeConverter instance;

    private PureFunTypeConverter() {}

    protected static PureFunTypeConverter getInstance() {
        if (instance == null) {
            instance = new PureFunTypeConverter();
        }
        return instance;
    }

    public static PureFunType convertFromAST(ASTType type) {
        return getInstance().doPrintType(type);
    }

    @Override
    protected PureFunType doPrintListType(ASTListType type) {
        return new PureFunListType(convertFromAST(type.getType()));
    }

    @Override
    protected PureFunType doPrintMapType(ASTMapType type) {
        return new PureFunMapType(convertFromAST(type.getKeyType()), convertFromAST(type.getValueType()));
    }

    @Override
    protected PureFunType doPrintNamedTupleType(ASTNamedTupleType type) {
        PureFunType[] tupleTypes = type.getNamedTupleList().stream().map((tuple) -> convertFromAST(tuple.getType())).toArray(PureFunType[]::new);

        return new PureFunTupleType(tupleTypes);
    }

    @Override
    protected PureFunType doPrintNameType(ASTTypeName type) {
        return new PureFunCommonType(type.getName());
    }
}
