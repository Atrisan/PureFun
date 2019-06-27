package de.simpleproglang.purefun.types;

public class PureFunMapType implements PureFunType {

    public static final PureFunMapType EMPTY_MAP = new PureFunMapType(PureFunCommonType.ARBITRARY, PureFunCommonType.ARBITRARY);

    private PureFunType keyType;
    private PureFunType valueType;

    public PureFunMapType(PureFunType keyType, PureFunType valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public String getTypeString() {
        return "[" + keyType.getTypeString() + ", " + valueType.getTypeString() + "]";
    }

    public PureFunType getKeyType() {
        return keyType;
    }

    public PureFunType getValueType() {
        return valueType;
    }

    @Override
    public boolean equals(PureFunType type) {
        if (!(type instanceof PureFunMapType)) {
            return false;
        }

        return PureFunType.super.equals(type) || (((PureFunMapType) type).getKeyType().equals(PureFunCommonType.ARBITRARY) && ((PureFunMapType) type).getValueType().equals(PureFunCommonType.ARBITRARY));
    }
}
