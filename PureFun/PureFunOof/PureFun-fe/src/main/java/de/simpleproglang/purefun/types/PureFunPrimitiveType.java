package de.simpleproglang.purefun.types;

public enum PureFunPrimitiveType {
    DOUBLE("Double"),
    FLOAT64("Float64"),
    FLOAT("Float"),
    INT("Int"),
    CHAR("Char"),
    STRING("String"),
    BOOLEAN("Boolean"),
    INT8("Int8"),
    INT16("Int16"),
    INT32("Int32"),
    INT64("Int64"),
    UINT8("UInt8"),
    UINT16("UInt16"),
    UINT32("UInt32"),
    UINT64("UInt64"),
    LONG("Long"),
    VOID("Void");

    private String typeStr;

    PureFunPrimitiveType(String typeStr) {
        this.typeStr = typeStr;
    }

    @Override
    public String toString() {
        return this.typeStr;
    }

    public static boolean contains(String type) {
        for (PureFunPrimitiveType pureFunType : values()) {
            if (pureFunType.toString().equalsIgnoreCase(type)) {
                return true;
            }
        }

        return false;
    }
}
