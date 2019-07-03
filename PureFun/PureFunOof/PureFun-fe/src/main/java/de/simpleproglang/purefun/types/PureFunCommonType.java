package de.simpleproglang.purefun.types;

import java.util.*;

public class PureFunCommonType implements PureFunType {
    public static final PureFunCommonType VOID = new PureFunCommonType("Void");
    public static final PureFunCommonType CUSTOM = new PureFunCommonType("CUSTOM TYPE");
    public static final PureFunCommonType ARBITRARY = new PureFunCommonType("ARBITRARY TYPE");

    private static final Map<PureFunPrimitiveType, PureFunType> NUMBER_TYPES;

    static {
        Map<PureFunPrimitiveType, PureFunType> numberTypes = new HashMap<>();
        numberTypes.put(PureFunPrimitiveType.DOUBLE, new PureFunCommonType(PureFunPrimitiveType.DOUBLE));
        numberTypes.put(PureFunPrimitiveType.FLOAT64, new PureFunCommonType(PureFunPrimitiveType.FLOAT64));
        numberTypes.put(PureFunPrimitiveType.FLOAT, new PureFunCommonType(PureFunPrimitiveType.FLOAT));
        numberTypes.put(PureFunPrimitiveType.INT, new PureFunCommonType(PureFunPrimitiveType.INT));
        numberTypes.put(PureFunPrimitiveType.INT8, new PureFunCommonType(PureFunPrimitiveType.INT8));
        numberTypes.put(PureFunPrimitiveType.INT16, new PureFunCommonType(PureFunPrimitiveType.INT16));
        numberTypes.put(PureFunPrimitiveType.INT32, new PureFunCommonType(PureFunPrimitiveType.INT32));
        numberTypes.put(PureFunPrimitiveType.INT64, new PureFunCommonType(PureFunPrimitiveType.INT64));
        numberTypes.put(PureFunPrimitiveType.UINT8, new PureFunCommonType(PureFunPrimitiveType.UINT8));
        numberTypes.put(PureFunPrimitiveType.UINT16, new PureFunCommonType(PureFunPrimitiveType.UINT16));
        numberTypes.put(PureFunPrimitiveType.UINT32, new PureFunCommonType(PureFunPrimitiveType.UINT32));
        numberTypes.put(PureFunPrimitiveType.UINT64, new PureFunCommonType(PureFunPrimitiveType.UINT64));
        numberTypes.put(PureFunPrimitiveType.LONG, new PureFunCommonType(PureFunPrimitiveType.LONG));

        NUMBER_TYPES = Collections.unmodifiableMap(numberTypes);
    }

    private String typeStr;

    public PureFunCommonType(String typeStr) {
        this.typeStr = typeStr;
    }

    public PureFunCommonType(PureFunPrimitiveType type) {
        this(type.getTypeString());
    }

    public static boolean isPrimitive(PureFunType type) {
        return Arrays.asList(PureFunPrimitiveType.values()).stream().map((el) -> el.getTypeString()).anyMatch((el) -> el.equals(type.getTypeString()));
    }

    public static boolean isNumber(PureFunType type) {
        return NUMBER_TYPES.values().stream().anyMatch((el) -> el.equals(type));
    }

    public static boolean isBoolean(PureFunType type) {
        return type.equals(NUMBER_TYPES.get(PureFunPrimitiveType.BOOLEAN));
    }

    public static boolean isFloatingPointType(PureFunType type) {
        return type.equals(PureFunPrimitiveType.DOUBLE) || type.equals(PureFunPrimitiveType.FLOAT64) || type.equals(PureFunPrimitiveType.FLOAT);
    }

    @Override
    public String getTypeString() {
        return this.typeStr;
    }
}
