package de.simpleproglang.purefun._symboltable;

import de.simpleproglang.purefun.types.PureFunType;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends FunctionSymbolTOP {

    private PureFunType returnType;
    private List<PureFunType> arguments;

    public FunctionSymbol(String name) {
        super(name);
    }

    public FunctionSymbol(String name, PureFunType returnType, List<PureFunType> arguments) {
        super(name);

        this.returnType = returnType;
        this.arguments = new ArrayList<>(arguments);
    }

    public PureFunType getReturnType() {
        return returnType;
    }

    public List<PureFunType> getArgumentTypes() {
        return new ArrayList<>(arguments);
    }
}
