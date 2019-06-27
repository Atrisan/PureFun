package de.simpleproglang.purefun.types;

public interface PureFunType {

    public String getTypeString();

    public default boolean equals(PureFunType type) {
        return this.getTypeString().equals(type.getTypeString());
    }
}
