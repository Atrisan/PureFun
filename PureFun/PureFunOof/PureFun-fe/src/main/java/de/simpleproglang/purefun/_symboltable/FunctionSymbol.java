package de.simpleproglang.purefun._symboltable;

public class FunctionSymbol extends FunctionSymbolTOP {

    private String returnType;

    public FunctionSymbol(String name) {
        super(name);
    }

    public FunctionSymbol(String name, String returnType) {
        super(name);

        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }
}
