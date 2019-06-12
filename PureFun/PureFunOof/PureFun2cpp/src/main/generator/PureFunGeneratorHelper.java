

import de.monticore.expressions.prettyprint.MCExpressionsPrettyPrinter;
import de.monticore.mcexpressions._ast.*;
import de.monticore.prettyprint.IndentPrinter;
import de.monticore.symboltable.GlobalScope;
import de.monticore.types.types._ast.*;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun._ast.ASTType;
import de.simpleproglang.purefun.printer.CppTypesPrinter;
import de.simpleproglang.purefun.printer.TypesPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


class PureFunGeneratorHelper{

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

    /**
     * Prints an expression
     *
     * @param ast
     * @return
     */
    public String printExpression(ASTExpression ast) {
        //TODO
        return "";
    }

    public Boolean isDataStruct(ASTDefinition type){
        if (type instanceof ASTDataStructure){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isFunction(ASTDefinition type){
        if (type instanceof ASTFunction){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isGlobalVar(ASTDefinition type){
        if (type instanceof ASTVariable){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isCommonForControl(ASTForControl type){
        if (type instanceof ASTCommonForControl){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isForEachControl(ASTForControl type){
        if (type instanceof ASTCommonForControl){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isAsyncStatement(ASTStatement type){
        if (type instanceof ASTAsyncStatement){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isForStatement(ASTStatement type){
        if (type instanceof ASTForStatement){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isIfStatement(ASTStatement type){
        if (type instanceof ASTIfStatement){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isReturnStatement(ASTStatement type){
        if (type instanceof ASTReturnStatement){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isWHileStatement(ASTStatement type){
        if (type instanceof ASTWhileStatement){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}