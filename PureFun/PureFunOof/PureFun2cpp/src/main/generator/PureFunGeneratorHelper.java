

import de.monticore.expressions.prettyprint.MCExpressionsPrettyPrinter;
import de.monticore.mcexpressions._ast.*;
import de.monticore.prettyprint.IndentPrinter;
import de.monticore.symboltable.GlobalScope;
import de.monticore.types.types._ast.*;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._ast.ASTType;
import de.simpleproglang.purefun.printer.TypesPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


class PureFunGeneratorHelper{

    protected ASTModule ast;

    protected GlobalScope symbolTable;

    public static final String CPP_EXTENSION = ".cpp";


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
        return TypesPrinter.printType(type);
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


}