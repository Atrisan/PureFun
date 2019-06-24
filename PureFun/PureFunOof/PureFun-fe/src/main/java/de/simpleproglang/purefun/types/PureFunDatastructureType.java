package de.simpleproglang.purefun.types;

public enum PureFunDatastructureType {

    ARBITRARY("Arbitrary"),
    LIST("List"),
    MAP("Map");

    private String typeStr;

    PureFunDatastructureType(String typeStr) {
        this.typeStr = typeStr;
    }

    @Override
    public String toString() {
        return this.typeStr;
    }

    public static boolean contains(String type) {
        for (PureFunDatastructureType pureFunType : values()) {
            if (pureFunType.toString().equalsIgnoreCase(type)) {
                return true;
            }
        }

        return false;
    }
}
