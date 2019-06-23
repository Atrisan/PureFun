

import de.monticore.expressions.prettyprint.MCExpressionsPrettyPrinter;
import de.monticore.mcexpressions._ast.*;
import de.monticore.prettyprint.IndentPrinter;
import de.monticore.symboltable.GlobalScope;
import de.monticore.types.types._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun._ast.ASTType;
import de.simpleproglang.purefun.printer.CppExpressionPrinter;
import de.simpleproglang.purefun.printer.CppTypesPrinter;
import de.simpleproglang.purefun.printer.TypesPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PureFunGeneratorHelper{

    protected ASTModule ast;

    protected GlobalScope symbolTable;

    public static final String CPP_EXTENSION = ".cxx";
    protected  CppTypesPrinter cppPrint = new CppTypesPrinter();

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
        return cppPrint.cppTypePrinter(type);
    }

    public String printExpression(ASTExpression expression) { return CppExpressionPrinter.printExpression(expression); }

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

    public boolean isNotContainerType(ASTType type) {
        return !isTupleType(type) && !isMapType(type) && !isListType(type);
    }

    public boolean isCommonForControl(ASTForControl type){
        if (type instanceof ASTCommonForControl){
            return true;
        }
        return false;
    }

    public Boolean isForEachControl(ASTForControl type){
        if (type instanceof ASTForEachControl){
            return false;
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