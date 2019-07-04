package de.simpleproglang.purefun._symboltable;


import de.simpleproglang.purefun.types.PureFunType;

public class VariableSymbol extends VariableSymbolTOP {

    private PureFunType type;
    private boolean async;
    private boolean global;
    private boolean data;

    public VariableSymbol(String name) {
        super(name);
    }

    public VariableSymbol(String name, PureFunType type) {
        super(name);

        this.type  = type;
    }

    public VariableSymbol(String name, PureFunType type, boolean async) {
        super(name);

        this.type  = type;
        this.async = async;
    }

    public VariableSymbol(String name, PureFunType type, boolean async, boolean global) {
        super(name);

        this.type  = type;
        this.async = async;
        this.global = global;
    }

    public VariableSymbol(String name, PureFunType type, boolean async, boolean global, boolean data) {
        super(name);

        this.type  = type;
        this.async = async;
        this.global = global;
        this.data = data;
    }

    public PureFunType getType() {
        return type;
    }

    public boolean getHasAsync() { return this.async; }

    public boolean getIsGlobal() { return this.global; }

    public boolean isData() { return this.data; }
}
