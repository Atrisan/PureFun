package de.simpleproglang.purefun.printer;

import de.monticore.MCBasicLiteralsPrettyPrinter;
import de.monticore.expressions.commonexpressions._ast.*;

import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.ASTLiteral;
import de.monticore.prettyprint.IndentPrinter;
import de.simpleproglang.purefun._ast.*;

public abstract class AbstractExpressionPrinter {

    protected AbstractExpressionPrinter() { }

    protected String doPrintExpression(ASTExpression expression) {
        if (expression instanceof ASTListExpression) {
            return this.doPrintListExpression((ASTListExpression) expression);
        } else if (expression instanceof ASTMapExpression) {
            return this.doPrintMapExpression((ASTMapExpression) expression);
        } else if (expression instanceof ASTTupleExpression) {
            return this.doPrintTupleExpression((ASTTupleExpression) expression);
        } else if (expression instanceof ASTAsyncExpression) {
            return this.doPrintAsyncExpression((ASTAsyncExpression) expression);
        } else if (expression instanceof ASTAssignmentExpression) {
            return this.doPrintAssignmentExpression((ASTAssignmentExpression) expression);
        } else if (expression instanceof ASTBooleanAndOpExpression) {
            return this.doPrintBooleanAndOpExpression((ASTBooleanAndOpExpression) expression);
        } else if (expression instanceof ASTBooleanAndOpExpressionDiff) {
            return this.doPrintBooleanAndOpExpressionDiff((ASTBooleanAndOpExpressionDiff) expression);
        } else if (expression instanceof ASTBooleanOrOpExpression) {
            return this.doPrintBooleanOrOpExpression((ASTBooleanOrOpExpression) expression);
        } else if (expression instanceof ASTBooleanOrOpExpressionDiff) {
            return this.doPrintBooleanOrOpExpressionDiff((ASTBooleanOrOpExpressionDiff) expression);
        } else if (expression instanceof ASTLengthExpression) {
            return this.doPrintLengthExpression((ASTLengthExpression) expression);
        } else if (expression instanceof ASTInExpression) {
            return this.doPrintInExpression((ASTInExpression) expression);
        } else if (expression instanceof ASTMapValueExpression) {
            return this.doPrintMapValueExpression((ASTMapValueExpression) expression);
        } else if (expression instanceof ASTMapKeyExpression) {
            return this.doPrintMapKeyExpression((ASTMapKeyExpression) expression);
        } else if (expression instanceof ASTIndexAccessExpression) {
            return this.doPrintIndexAccessExpression((ASTIndexAccessExpression) expression);
        } else if (expression instanceof ASTConcatExpression) {
            return this.doPrintConcatExpression((ASTConcatExpression) expression);
        } else if (expression instanceof ASTCallExpression) {
            return this.doPrintCallExpression((ASTCallExpression) expression);
        } else if (expression instanceof ASTNameExpression) {
            return this.doPrintNameExpression((ASTNameExpression) expression);
        } else if (expression instanceof ASTPlusExpression) {
            return this.doPrintPlusExpression((ASTPlusExpression) expression);
        } else if (expression instanceof ASTQualifiedNameExpression) {
            return this.doPrintQualifiedNameExpression((ASTQualifiedNameExpression) expression);
        } else if (expression instanceof ASTLitExpression) {
            // TODO: Use LiteralPrinter
        }

        return "Not implemented in PureFunExpressionPrinter: " + expression.toString();
    }

    protected abstract String doPrintListExpression(ASTListExpression exp);

    protected abstract String doPrintMapExpression(ASTMapExpression exp);

    protected abstract String doPrintTupleExpression(ASTTupleExpression exp);

    protected abstract String doPrintAsyncExpression(ASTAsyncExpression exp);

    protected abstract String doPrintAssignmentExpression(ASTAssignmentExpression exp);

    protected abstract String doPrintBooleanAndOpExpression(ASTBooleanAndOpExpression exp);

    protected abstract String doPrintBooleanAndOpExpressionDiff(ASTBooleanAndOpExpressionDiff exp);

    protected abstract String doPrintBooleanOrOpExpression(ASTBooleanOrOpExpression exp);

    protected abstract String doPrintBooleanOrOpExpressionDiff(ASTBooleanOrOpExpressionDiff exp);

    protected abstract String doPrintLengthExpression(ASTLengthExpression exp);

    protected abstract String doPrintInExpression(ASTInExpression exp);

    protected abstract String doPrintMapValueExpression(ASTMapValueExpression exp);

    protected abstract String doPrintMapKeyExpression(ASTMapKeyExpression exp);

    protected abstract String doPrintIndexAccessExpression(ASTIndexAccessExpression exp);

    protected abstract String doPrintConcatExpression(ASTConcatExpression exp);

    protected abstract String doPrintCallExpression(ASTCallExpression exp);

    protected abstract String doPrintNameExpression(ASTNameExpression exp);

    protected abstract String doPrintPlusExpression(ASTPlusExpression exp);

    protected abstract String doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp);
}
