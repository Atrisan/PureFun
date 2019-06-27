package de.simpleproglang.purefun._symboltable;


public class VariableSymbol extends VariableSymbolTOP {

    private String type;
    private boolean async;
    private boolean global;

    public VariableSymbol(String name) {
        super(name);
    }

    public VariableSymbol(String name, String type) {
        super(name);

        this.type  = type;
    }

    public VariableSymbol(String name, String type, boolean async) {
        super(name);

        this.type  = type;
        this.async = async;
    }

    public VariableSymbol(String name, String type, boolean async, boolean global) {
        super(name);

        this.type  = type;
        this.async = async;
        this.global = global;
    }

    public String getType() {
        return type;
    }

    public boolean getHasAsync() { return this.async; }

    public boolean getIsGlobal() { return this.global; }
}
