package de.simpleproglang.purefun._symboltable;


public class VariableSymbol extends VariableSymbolTOP {

    private String type;
    private Boolean async;

    public VariableSymbol(String name) {
        super(name);
    }

    public VariableSymbol(String name, String type) {
        super(name);

        this.type  = type;
    }

    public VariableSymbol(String name, String type, Boolean async) {
        super(name);

        this.type  = type;
        this.async = async;
    }

    public String getType() {
        return type;
    }

    public Boolean getHasAsync() { return this.async; }
}
