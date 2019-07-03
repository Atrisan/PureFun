package de.simpleproglang.purefun.printer;

import de.simpleproglang.purefun._ast.*;

import java.util.Iterator;

public class TypesPrinter extends AbstractTypesPrinter<String> {
    private static TypesPrinter instance;

    private TypesPrinter() {}

    private static TypesPrinter getInstance() {
        if (instance == null) {
            instance = new TypesPrinter();
        }
        return instance;
    }

    public static String printType(ASTType type) {
        return getInstance().doPrintType(type);
    }

    protected String doPrintType(ASTType type) {
        if (type instanceof ASTListType) {
            return this.doPrintListType((ASTListType)type);
        } else if (type instanceof ASTMapType) {
            return this.doPrintMapType((ASTMapType)type);
        } else if (type instanceof ASTNamedTupleType) {
            return this.doPrintNamedTupleType((ASTNamedTupleType)type);
        } else {
            return (type instanceof ASTTypeName) ? ((ASTTypeName) type).getName() : "";
        }
    }

    protected String doPrintListType(ASTListType type) {
        return "[" + this.doPrintType(type.getType()) + "]";
    }

    protected String doPrintMapType(ASTMapType type) {
        return "[" + this.doPrintType(type.getKeyType()) + ", " + this.doPrintType(type.getValueType()) + "]";
    }

    protected String doPrintNamedTupleType(ASTNamedTupleType type) {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("(");

        String sep = "";
        for (Iterator it = type.getNamedTupleList().iterator(); it.hasNext(); sep = ", ") {
            ASTNamedTuple curTuple = (ASTNamedTuple) it.next();
            strBuilder.append(sep);

            strBuilder.append(this.doPrintType(curTuple.getType()));
        }

        strBuilder.append(")");

        return strBuilder.toString();
    }

    @Override
    protected String doPrintNameType(ASTTypeName type) {
        return type.getName();
    }
}
