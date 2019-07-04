package de.simpleproglang.purefun.types;

public class PureFunTupleType implements PureFunType {

    public static final PureFunTupleType EMPTY_TUPLE = new PureFunTupleType();

    private final PureFunType[] subTypes;

    public PureFunTupleType(PureFunType... types) {
        subTypes = types;
    }

    @Override
    public String getTypeString() {
        StringBuilder res = new StringBuilder();
        String sep = "";

        res.append("(");
        for (int i = 0; i < subTypes.length; i++, sep = ", ") {
            res.append(sep);
            res.append(subTypes[i].getTypeString());
        }
        res.append(")");

        return res.toString();
    }

    public PureFunType[] getSubTypes() {
        PureFunType[] clonedSubTypes = new PureFunType[subTypes.length];
        for (int i = 0; i < subTypes.length; i++) {
            clonedSubTypes[i] = subTypes[i];
        }

        return clonedSubTypes;
    }

    @Override
    public boolean equals(PureFunType type) {
        if (!(type instanceof PureFunTupleType)) {
            return false;
        }

        return PureFunType.super.equals(type) || ((PureFunTupleType) type).getSubTypes().length == 0;
    }
}
