package de.simpleproglang.purefun.generator;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.prettyprint.MCExpressionsPrettyPrinter;
import de.monticore.mcexpressions._ast.*;
import de.monticore.prettyprint.IndentPrinter;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.Scopes;
import de.monticore.types.types._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun._ast.ASTType;
import de.simpleproglang.purefun._symboltable.DataStructureSymbol;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.printer.CppExpressionPrinter;
import de.simpleproglang.purefun.printer.CppTypesPrinter;
import de.simpleproglang.purefun.printer.TypesPrinter;

import java.util.*;
import java.util.stream.Collectors;


public class PureFunGeneratorHelper{

    protected ASTModule ast;

    protected GlobalScope symbolTable;

    private int num = 0;
    public static final String CPP_EXTENSION = ".cxx";

    public PureFunGeneratorHelper(ASTModule ast, GlobalScope symbolTable) {
        this.ast = ast;
        this.symbolTable = symbolTable;
    }

    /**
     * Prints a type
     *
     * @param type
     * @return
     */

    public String printType(ASTType type) {
        return CppTypesPrinter.cppTypePrinter(type);
    }

    public String printExpression(ASTExpression expression) { return CppExpressionPrinter.printExpression(expression); }

    public List<ASTFunction> getFunctionList(ASTModule module) {
        List<ASTFunction> res = new ArrayList<>();
        for (ASTDefinition def: module.getDefinitionList()) {
            if (def instanceof ASTFunction) {
                res.add((ASTFunction) def);
            }
        }
        return res;
    }

    public String toCAPS(String in){
        return in.toUpperCase();
    }

    public List<ASTDefinition> getPrevDefs(ASTDataStructure def, ASTModule parent) {
        List<ASTDefinition> erg = new ArrayList<ASTDefinition>();
        for (ASTDefinition element : parent.getDefinitionList()) {
            if (element instanceof ASTDataStructure){
                if (isSameStruct(element, def)) {
                    break;
                } else {
                    erg.add(element);
                }
            }
        }
        return erg;
    }

    public boolean isSameStruct(ASTDefinition left, ASTDefinition right) {
        return ((ASTDataStructure)left).getName().equals(((ASTDataStructure)right).getName());
    }

    public boolean isNotClassType (ASTVariable var) {
        ASTType type = var.getType();
        boolean erg = true;
        String[] Types = new String[17];
        Types[0] = ("Double");
        Types[1] = ("Float64");
        Types[2] = ("Float");
        Types[3] = ("Int");
        Types[4] = ("Char");
        Types[5] = ("String");
        Types[6] = ("Boolean");
        Types[7] = ("Int8");
        Types[8] = ("Int16");
        Types[9] = ("Int32");
        Types[10] = ("Int64");
        Types[11] = ("UInt8");
        Types[12] = ("UInt16");
        Types[13] = ("UInt32");
        Types[14] = ("UInt64");
        Types[15] = ("Long");
        Types[16] = ("Void");
        if (type instanceof ASTTypeName) {
            for (int i = 0; i < Types.length; i++) {
                if (((ASTTypeName) type).getName().equals(Types[i])) {
                    return true;
                }
            }
            erg = false;
        }
        return erg;
    }

    public String printAsync(ASTExpression expression, Boolean assign) {
        String res = "";
        if(!assign) {
            res += "std::shared_future async" + this.num;
            num++;
        }

        return res += this.printExpression(expression);
    }

    public boolean isAsyncExpression(ASTExpression expression) {
        return (expression instanceof ASTAsyncExpression);
    }

    public boolean isDataStruct(ASTDefinition type){
        if (type instanceof ASTDataStructure){
            return true;
        }
        return false;
    }

    public boolean isFunction(ASTDefinition type){
        if (type instanceof ASTFunction){
            return true;
        }
        return false;
    }

    public boolean isGlobalVar(ASTDefinition type){
        if (type instanceof ASTVariable){
            return true;
        }
        return false;
    }

    public boolean isListType(ASTType type) {
        if (type instanceof ASTListType) {
            return true;
        }
        return false;
    }

    public boolean isMapType(ASTType type) {
        if (type instanceof ASTMapType) {
            return true;
        }
        return false;
    }

    public boolean isTupleType(ASTType type) {
        if (type instanceof ASTNamedTupleType) {
            return true;
        }
        return false;
    }

    public boolean isNotContainerType(ASTVariable var) {
        ASTType type = var.getType();

        if (var.isPresentExpression()) {
            ASTExpression exp = var.getExpression();
            if (exp instanceof ASTListExpression
                    || exp instanceof ASTMapExpression
                    || exp instanceof ASTTupleExpression) {
                return false;
            }
        }

        return true;
    }

    public boolean isCommonForControl(ASTForControl type){
        if (type instanceof ASTCommonForControl){
            return true;
        }
        return false;
    }

    public Boolean isForEachControl(ASTForControl type){
        if (type instanceof ASTForEachControl){
            return true;
        }
        return false;
    }

    public boolean isAsyncStatement(ASTStatement type){
        if (type instanceof ASTAsyncStatement){
            return true;
        }
        return false;
    }

    public boolean isForStatement(ASTStatement type){
        if (type instanceof ASTForStatement){
            return true;
        }
        return false;
    }

    public boolean isIfStatement(ASTStatement type){
        if (type instanceof ASTIfStatement){
            return true;
        }
        return false;
    }

    public boolean isReturnStatement(ASTStatement type){
        if (type instanceof ASTReturnStatement){
            return true;
        }
        return false;
    }

    public boolean isWhileStatement(ASTStatement type){
        if (type instanceof ASTWhileStatement){
            return true;
        }
        return false;
    }

}