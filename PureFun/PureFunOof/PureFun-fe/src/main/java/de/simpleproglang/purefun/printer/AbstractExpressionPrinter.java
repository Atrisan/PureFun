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
            return this.doPrintLitExpression((ASTLitExpression) expression);
        } else if (expression instanceof ASTBooleanNotExpression) {
            return this.doPrintBooleanNotExpression((ASTBooleanNotExpression) expression);
        } else if (expression instanceof ASTLogicalNotExpression) {
            return this.doPrintLogicalNotExpression((ASTLogicalNotExpression) expression);
        } else if (expression instanceof ASTMultExpression) {
            return this.doPrintMultExpression((ASTMultExpression) expression);
        } else if (expression instanceof ASTDivideExpression) {
            return this.doPrintDivideExpression((ASTDivideExpression) expression);
        } else if (expression instanceof ASTModuloExpression) {
            return this.doPrintModuloExpression((ASTModuloExpression) expression);
        } else if (expression instanceof ASTMinusExpression) {
            return this.doPrintMinusExpression((ASTMinusExpression) expression);
        } else if (expression instanceof ASTLessEqualExpression) {
            return this.doPrintLessEqualExpression((ASTLessEqualExpression) expression);
        } else if (expression instanceof ASTGreaterEqualExpression) {
            return this.doPrintGreaterEqualExpression((ASTGreaterEqualExpression) expression);
        } else if (expression instanceof ASTLessThanExpression) {
            return this.doPrintLessThanExpression((ASTLessThanExpression) expression);
        } else if (expression instanceof ASTGreaterThanExpression) {
            return this.doPrintGreaterThanExpression((ASTGreaterThanExpression) expression);
        } else if (expression instanceof ASTEqualsExpression) {
            return this.doPrintEqualsExpression((ASTEqualsExpression) expression);
        } else if (expression instanceof ASTNotEqualsExpression) {
            return this.doPrintNotEqualsExpression((ASTNotEqualsExpression) expression);
        } else if (expression instanceof ASTConditionalExpression) {
            return this.doPrintConditionalExpression((ASTConditionalExpression) expression);
        } else if (expression instanceof ASTSimpleAssignmentExpression) {
            return this.doPrintSimpleAssignmentExpression((ASTSimpleAssignmentExpression) expression);
        } else if (expression instanceof ASTBracketExpression) {
            return this.doPrintBracketExpression((ASTBracketExpression) expression);
        } else if (expression instanceof ASTAssignmentByDecreaseExpression) {
            return this.doPrintAssignmentByDecreaseExpression((ASTAssignmentByDecreaseExpression) expression);
        } else if (expression instanceof ASTAssignmentByMultiplyExpression) {
            return this.doPrintAssignmentByMultiplyExpression((ASTAssignmentByMultiplyExpression) expression);
        } else if (expression instanceof ASTAssignmentByDivisionExpression) {
            return this.doPrintAssignmentByDivisionExpression((ASTAssignmentByDivisionExpression) expression);
        } else if (expression instanceof ASTAssignmentByRemainderExpression) {
            return this.doPrintAssignmentByRemainderExpression((ASTAssignmentByRemainderExpression) expression);
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

    protected abstract String doPrintLogicalNotExpression(ASTLogicalNotExpression exp);

    protected abstract String doPrintBooleanNotExpression(ASTBooleanNotExpression exp);

    protected abstract String doPrintLitExpression(ASTLitExpression exp);

    protected abstract String doPrintAssignmentByRemainderExpression(ASTAssignmentByRemainderExpression exp);

    protected abstract String doPrintAssignmentByDivisionExpression(ASTAssignmentByDivisionExpression exp);

    protected abstract String doPrintAssignmentByMultiplyExpression(ASTAssignmentByMultiplyExpression exp);

    protected abstract String doPrintAssignmentByDecreaseExpression(ASTAssignmentByDecreaseExpression exp);

    protected abstract String doPrintBracketExpression(ASTBracketExpression exp);

    protected abstract String doPrintSimpleAssignmentExpression(ASTSimpleAssignmentExpression exp);

    protected abstract String doPrintConditionalExpression(ASTConditionalExpression exp);

    protected abstract String doPrintNotEqualsExpression(ASTNotEqualsExpression exp);

    protected abstract String doPrintEqualsExpression(ASTEqualsExpression exp);

    protected abstract String doPrintGreaterThanExpression(ASTGreaterThanExpression exp);

    protected abstract String doPrintLessThanExpression(ASTLessThanExpression exp);

    protected abstract String doPrintGreaterEqualExpression(ASTGreaterEqualExpression exp);

    protected abstract String doPrintLessEqualExpression(ASTLessEqualExpression exp);

    protected abstract String doPrintMinusExpression(ASTMinusExpression exp);

    protected abstract String doPrintModuloExpression(ASTModuloExpression exp);

    protected abstract String doPrintDivideExpression(ASTDivideExpression exp);

    protected abstract String doPrintMultExpression(ASTMultExpression exp);
}
