package de.simpleproglang.purefun.printer;

import de.simpleproglang.purefun._ast.*;

public abstract class AbstractTypesPrinter<T> {

    protected AbstractTypesPrinter() {}

    protected T doPrintType(ASTType type) {
        if (type instanceof ASTListType) {
            return this.doPrintListType((ASTListType)type);
        } else if (type instanceof ASTMapType) {
            return this.doPrintMapType((ASTMapType)type);
        } else if (type instanceof ASTNamedTupleType) {
            return this.doPrintNamedTupleType((ASTNamedTupleType)type);
        } else if(type instanceof ASTTypeName) {
            return doPrintNameType((ASTTypeName) type);
        }

        throw new RuntimeException("Sub-class of ASTType not implemented");
    }

    protected abstract T doPrintListType(ASTListType type);

    protected abstract T doPrintMapType(ASTMapType type);

    protected abstract T doPrintNamedTupleType(ASTNamedTupleType type);

    protected abstract T doPrintNameType(ASTTypeName type);
}
