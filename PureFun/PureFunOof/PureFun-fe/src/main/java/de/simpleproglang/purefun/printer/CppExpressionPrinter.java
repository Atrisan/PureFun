package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.ASTLiteral;
import de.monticore.literals.literals._ast.ASTStringLiteral;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun._symboltable.VariableSymbol;

import java.util.Optional;


public class CppExpressionPrinter extends AbstractExpressionPrinter<String> {

    private static CppExpressionPrinter printer;

    protected AbstractLiteralPrinter<String> LitPrinter = new CppLiteralPrinter();

    private CppExpressionPrinter() { }

    protected static AbstractExpressionPrinter<String> getInstance() {
        if (printer == null) {
            printer = new CppExpressionPrinter();
        }

        return printer;
    }

    public static String printExpression(ASTExpression expression) {
        return CppExpressionPrinter.getInstance().doPrintExpression(expression);
    }

    @Override
    protected String doPrintRemainderExpressionDiff(ASTRemainderExpressionDiff exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " % ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintConstructorExpression(ASTConstructorExpression exp) {
        String erg = "(";
        for (int i = 0; i < exp.getArguments().sizeExpressions(); i++) {
            erg += this.doPrintExpression(exp.getArguments().getExpression(i));
            if (i < exp.getArguments().sizeExpressions() - 1) {
                erg += ", ";
            }
        }
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintDecrementExpression(ASTDecrementExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += "--";
        return erg;
    }

    @Override
    protected String doPrintPrintExpression(ASTPrintExpression exp) {
        String erg = "print(";
        for (int i = 0; i < exp.sizePrintElements(); i++) {
            ASTPrintElement akt = exp.getPrintElement(i);
            if (akt.isPresentExpression()){
                erg += this.doPrintExpression( akt.getExpression());
            } else if (akt.isPresentStringLiteral()){
                erg += LitPrinter.doPrintLiteral( akt.getStringLiteral());
            }
            if(i < exp.sizePrintElements() - 1) {
                erg += ", ";
            }
        }
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintListExpression(ASTListExpression exp) {
        String erg = "{";
        for (int i = 0; i < exp.sizeExpressions(); i++) {
            erg += this.doPrintExpression(exp.getExpression(i));
            if(i < exp.sizeExpressions() - 1) {
                erg += ", ";
            }
        }
        erg += "}";
        return erg;
    }

    @Override
    protected String doPrintMapExpression(ASTMapExpression exp) {
        String erg = "{";
        for (int i = 0; i < exp.sizeMapKeyValuePairs(); i++) {
            erg += "{";
            erg += this.doPrintExpression(exp.getMapKeyValuePair(i).getKey());
            erg += ", ";
            erg += this.doPrintExpression(exp.getMapKeyValuePair(i).getValue());
            erg += "}";
            if (i < exp.sizeMapKeyValuePairs() - 1) {
                erg += ", ";
            }
        }
        erg += "}";
        return erg;
    }

    @Override
    protected String doPrintTupleExpression(ASTTupleExpression exp) {
        String erg = "{";
        for (int i = 0; i < exp.sizeExpressions(); i++) {
            erg += this.doPrintExpression(exp.getExpression(i));
            if (i < exp.sizeExpressions() - 1) {
                erg += ", ";
            }
        }
        erg += "}";
        return erg;
    }

    @Override
    protected String doPrintAsyncExpression(ASTAsyncExpression exp) {
        String erg = "std::async(std::launch::async, [=]() { return ";
        erg += this.doPrintExpression(exp.getExpression());
        erg += "(";
        for (int i = 0; i < exp.getArguments().sizeExpressions(); i++) {
            erg += this.doPrintExpression(exp.getArguments().getExpression(i));
            if (i < exp.getArguments().sizeExpressions() - 1) {
                erg += ", ";
            }
        }
        erg += ");})";
        return erg;
    }

    @Override
    protected String doPrintAssignmentExpression(ASTAssignmentExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getLeft());
        erg += " = ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintBooleanAndOpExpression(ASTBooleanAndOpExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getLeft());
        erg += " && ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintBooleanAndOpExpressionDiff(ASTBooleanAndOpExpressionDiff exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getLeft());
        erg += " && ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintBooleanOrOpExpression(ASTBooleanOrOpExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getLeft());
        erg += " || ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintBooleanOrOpExpressionDiff(ASTBooleanOrOpExpressionDiff exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getLeft());
        erg += " || ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintLengthExpression(ASTLengthExpression exp) {
        String erg = "size(";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintInExpression(ASTInExpression exp) {
        String erg = "check_in(";
        erg += this.doPrintExpression(exp.getRight());
        erg += ", ";
        erg += this.doPrintExpression(exp.getLeft());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintMapValueExpression(ASTMapValueExpression exp) {
        String erg = "map_values(";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintMapKeyExpression(ASTMapKeyExpression exp) {
        String erg = "map_keys(";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintIndexAccessExpression(ASTIndexAccessExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ".at(";
        erg += this.doPrintExpression(exp.getIndex());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintConcatExpression(ASTConcatExpression exp) {
        String erg = "";
        if (exp.isPresentRight()) {
            erg += "concat(";
            erg += this.doPrintExpression(exp.getLeft());
            erg += ", ";
            erg += this.doPrintExpression(exp.getRight());
            erg += ")";
        } else {
            erg += this.doPrintExpression(exp.getLeft());
            erg += "++";
        }
        return erg;
    }

    @Override
    protected String doPrintCallExpression(ASTCallExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getExpression());
        erg += "(";
        for (int i = 0; i < exp.getArguments().sizeExpressions(); i++) {
            erg += this.doPrintExpression(exp.getArguments().getExpression(i));
            if (i < exp.getArguments().sizeExpressions() - 1) {
                erg += ", ";
            }
        }
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintNameExpression(ASTNameExpression exp) {
        Optional<VariableSymbol> sym = exp.getEnclosingScope().resolve(exp.getName(), VariableSymbol.KIND);
        if(sym.isPresent() && sym.get().getHasAsync()) {
            return exp.getName() + ".get()";
        }

        return exp.getName();
    }

    @Override
    protected String doPrintPlusExpression(ASTPlusExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " + ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp) {
        String erg = this.doPrintExpression(exp.getExpression());
        erg += ".";
        erg += exp.getName();
        return erg;
    }

    @Override
    protected String doPrintLogicalNotExpression(ASTLogicalNotExpression exp) {
        return "!" + this.doPrintExpression(exp.getExpression());
    }

    @Override
    protected String doPrintBooleanNotExpression(ASTBooleanNotExpression exp) {
        return "~" + this.doPrintExpression(exp.getExpression());
    }

    @Override
    protected String doPrintLitExpression(ASTLitExpression exp) {
        return LitPrinter.doPrintLiteral(exp.getLiteral());
    }

    @Override
    protected String doPrintAssignmentByRemainderExpression(ASTAssignmentByRemainderExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " %= ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintAssignmentByDivisionExpression(ASTAssignmentByDivisionExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " /= ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintAssignmentByMultiplyExpression(ASTAssignmentByMultiplyExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " *= ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintAssignmentByDecreaseExpression(ASTAssignmentByDecreaseExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " -= ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintBracketExpression(ASTBracketExpression exp) {
        String erg = "(";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintSimpleAssignmentExpression(ASTSimpleAssignmentExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " += ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintConditionalExpression(ASTConditionalExpression exp) {
        String erg = this.doPrintExpression(exp.getCondition());
        erg += " ? ";
        erg += this.doPrintExpression(exp.getTrueExpression());
        erg += " : ";
        erg += this.doPrintExpression(exp.getFalseExpression());
        return erg;
    }

    @Override
    protected String doPrintNotEqualsExpression(ASTNotEqualsExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " != ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintEqualsExpression(ASTEqualsExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " == ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintGreaterThanExpression(ASTGreaterThanExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " > ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintLessThanExpression(ASTLessThanExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " < ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintGreaterEqualExpression(ASTGreaterEqualExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " >= ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintLessEqualExpression(ASTLessEqualExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " <= ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintMinusExpression(ASTMinusExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " - ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintModuloExpression(ASTModuloExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " % ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintDivideExpression(ASTDivideExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " / ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintMultExpression(ASTMultExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " * ";
        erg += this.doPrintExpression(exp.getRight());
        return erg;
    }
}
