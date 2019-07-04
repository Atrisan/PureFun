package de.simpleproglang.purefun.types;

public class PureFunListType implements PureFunType {

    public static final PureFunListType EMPTY_LIST = new PureFunListType(PureFunCommonType.ARBITRARY);

    private PureFunType subType;

    public PureFunListType(PureFunType subType) {
        this.subType = subType;
    }

    @Override
    public String getTypeString() {
        return "[" + subType.getTypeString() + "]";
    }

    public PureFunType getSubType() {
        return subType;
    }

    @Override
    public boolean equals(PureFunType type) {
        if (!(type instanceof PureFunListType)) {
            return false;
        }

        PureFunListType listType = (PureFunListType) type;


        return PureFunType.super.equals(type) || listType.getSubType().equals(PureFunCommonType.ARBITRARY);
    }
}
