package de.simpleproglang.purefun._symboltable;


public class VariableSymbol extends VariableSymbolTOP {

    private String type;

    public VariableSymbol(String name) {
        super(name);
    }

    public VariableSymbol(String name, String type) {
        super(name);

        this.type  = type;
    }

    public String getType() {
        return type;
    }
}
