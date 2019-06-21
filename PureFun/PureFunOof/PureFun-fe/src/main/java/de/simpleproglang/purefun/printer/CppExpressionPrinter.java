package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun._ast.*;



public class CppExpressionPrinter extends AbstractExpressionPrinter{

    private static CppExpressionPrinter printer;

    AbstractLiteralPrinter LitPrinter = new CppLiteralPrinter();

    private CppExpressionPrinter() { }

    protected static AbstractExpressionPrinter getInstance() {
        if (printer == null) {
            printer = new CppExpressionPrinter();
        }

        return printer;
    }

    public static String printExpression(ASTExpression expression) {
        return CppExpressionPrinter.getInstance().this.doPrintExpression(expression);
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
        String erg = "";
        //TODO
        return erg;
    }

    @Override
    protected String doPrintAssignmentExpression(ASTAssignmentExpression exp) {
        String erg = "";
        erg += exp.getLeft();
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
        String erg = "";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ".size()";
        return erg;
    }

    @Override
    protected String doPrintInExpression(ASTInExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getRight());
        erg += ".existsIn(";
        erg += this.doPrintExpression(exp.getLeft());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintMapValueExpression(ASTMapValueExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ".getValueList()";
        return erg;
    }

    @Override
    protected String doPrintMapKeyExpression(ASTMapKeyExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ".getKeyList()";
        return erg;
    }

    @Override
    protected String doPrintIndexAccessExpression(ASTIndexAccessExpression exp) {
        String erg = "";
        erg += this.doPrintExpression(exp.getExpression());
        erg += ".getAt(";
        erg += this.doPrintExpression(exp.getIndex());
        erg += ")";
        return erg;
    }

    @Override
    protected String doPrintConcatExpression(ASTConcatExpression exp) {
        String erg = "";
        if (exp.isPresentRight()) {
            erg += this.doPrintExpression(exp.getLeft());
            erg += ".getAt(";
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
        return exp.getName();
    }

    @Override
    protected String doPrintPlusExpression(ASTPlusExpression exp) {
        String erg = this.doPrintExpression(exp.getLeft());
        erg += " + ";
        erg = this.doPrintExpression(exp.getRight());
        return erg;
    }

    @Override
    protected String doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp) {
        String erg = doPrintExpression(exp.getExpression());
        erg += ".";
        erg += exp.getName();
        return erg;
    }

    @Override
    protected String doPrintLitExpression(ASTLitExpression exp) {
        return LitPrinter.doPrintLiteral(exp.getLiteral());
    }
}
