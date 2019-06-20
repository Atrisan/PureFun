package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun._ast.*;

import java.util.Iterator;

public class PureFunExpressionPrinter extends AbstractExpressionPrinter {

    private static PureFunExpressionPrinter printer;

    private PureFunExpressionPrinter() { }

    protected static AbstractExpressionPrinter getInstance() {
        if (printer == null) {
            printer = new PureFunExpressionPrinter();
        }

        return printer;
    }

    public static String printExpression(ASTExpression expression) {
        return PureFunExpressionPrinter.getInstance().doPrintExpression(expression);
    }

    @Override
    protected String doPrintListExpression(ASTListExpression exp) {
        StringBuilder res = new StringBuilder();
        res.append("[");
        String sep = "";
        for (Iterator it = exp.iteratorExpressions(); it.hasNext(); sep = ", ") {
            res.append(sep + this.doPrintExpression((ASTExpression) it.next()));
        }
        res.append("]");

        return res.toString();
    }

    @Override
    protected String doPrintMapExpression(ASTMapExpression exp) {
        StringBuilder res = new StringBuilder();
        res.append("{");
        String sep = "";

        for (Iterator it = exp.iteratorMapKeyValuePairs(); it.hasNext(); sep = ", ") {
            ASTMapKeyValuePair pair = (ASTMapKeyValuePair) it.next();
            res.append(sep + this.doPrintExpression(pair.getKey()));
            res.append(": ");
            res.append(this.doPrintExpression(pair.getValue()));
        }

        res.append("}");
        return res.toString();
    }

    @Override
    protected String doPrintTupleExpression(ASTTupleExpression exp) {
        StringBuilder res = new StringBuilder();
        res.append("(");
        String sep = "";

        for (Iterator it = exp.iteratorExpressions(); it.hasNext(); sep = ", ") {
            res.append(sep + this.doPrintExpression((ASTExpression) it.next()));
        }

        res.append(")");
        return res.toString();
    }

    @Override
    protected String doPrintAsyncExpression(ASTAsyncExpression exp) {
        StringBuilder res = new StringBuilder();
        res.append("async ");
        res.append(this.doPrintExpression(exp.getExpression()));
        String sep = "";
        res.append("(");

        for (Iterator it = exp.getArguments().iteratorExpressions(); it.hasNext(); sep=", ") {
            res.append(sep + this.doPrintExpression((ASTExpression) it.next()));
        }

        res.append(")");
        return res.toString();
    }

    @Override
    protected String doPrintAssignmentExpression(ASTAssignmentExpression exp) {
        StringBuilder res = new StringBuilder();

        res.append(exp.getLeft());
        res.append(" = ");
        res.append(this.doPrintExpression(exp.getRight()));

        return res.toString();
    }

    @Override
    protected String doPrintBooleanAndOpExpression(ASTBooleanAndOpExpression exp) {
        StringBuilder res = new StringBuilder();

        res.append(this.doPrintExpression(exp.getLeft()));
        res.append(" && ");
        res.append(this.doPrintExpression(exp.getRight()));

        return res.toString();
    }

    @Override
    protected String doPrintBooleanAndOpExpressionDiff(ASTBooleanAndOpExpressionDiff exp) {
        StringBuilder res = new StringBuilder();

        res.append(this.doPrintExpression(exp.getLeft()));
        res.append(" and ");
        res.append(this.doPrintExpression(exp.getRight()));

        return res.toString();
    }

    @Override
    protected String doPrintBooleanOrOpExpression(ASTBooleanOrOpExpression exp) {
        return this.doPrintExpression(exp.getLeft()) + " || " + this.doPrintExpression(exp.getRight());
    }

    @Override
    protected String doPrintBooleanOrOpExpressionDiff(ASTBooleanOrOpExpressionDiff exp) {
        return this.doPrintExpression(exp.getLeft()) + " or " + this.doPrintExpression(exp.getRight());
    }

    @Override
    protected String doPrintLengthExpression(ASTLengthExpression exp) {
        return "#" + this.doPrintExpression(exp.getExpression());
    }

    @Override
    protected String doPrintInExpression(ASTInExpression exp) {
        return this.doPrintExpression(exp.getLeft()) + " in " + this.doPrintExpression(exp.getRight());
    }

    @Override
    protected String doPrintMapValueExpression(ASTMapValueExpression exp) {
        return "$" + this.doPrintExpression(exp.getExpression());
    }

    @Override
    protected String doPrintMapKeyExpression(ASTMapKeyExpression exp) {
        return "!" + this.doPrintExpression(exp.getExpression());
    }

    @Override
    protected String doPrintIndexAccessExpression(ASTIndexAccessExpression exp) {
        return this.doPrintExpression(exp.getExpression()) + "[" + this.doPrintExpression(exp.getIndex()) + "]";
    }

    @Override
    protected String doPrintConcatExpression(ASTConcatExpression exp) {
        return this.doPrintExpression(exp.getLeft()) + " ++ " + this.doPrintExpression(exp.getRight());
    }

    @Override
    protected String doPrintCallExpression(ASTCallExpression exp) {
        StringBuilder res = new StringBuilder();
        String sep = "";
        res.append(this.doPrintExpression(exp.getExpression()));
        res.append("(");

        for (Iterator it = exp.getArguments().iteratorExpressions(); it.hasNext(); sep = ", ") {
            res.append(sep + this.doPrintExpression((ASTExpression) it.next()));
        }

        res.append(")");

        return res.toString();
    }

    @Override
    protected String doPrintNameExpression(ASTNameExpression exp) {
        return exp.getName();
    }

    @Override
    protected String doPrintPlusExpression(ASTPlusExpression exp) {
        return this.doPrintExpression(exp.getLeft()) + " " + exp.getOperator()  + " " + this.doPrintExpression(exp.getRight());
    }

    @Override
    protected String doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp) {
        return this.doPrintExpression(exp.getExpression()) + "." + exp.getName();
    }
}
