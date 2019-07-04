package de.simpleproglang.purefun.printer;

import de.monticore.MCBasicLiteralsPrettyPrinter;
import de.monticore.expressions.commonexpressions._ast.*;

import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.ASTLiteral;
import de.monticore.prettyprint.IndentPrinter;
import de.simpleproglang.purefun._ast.*;

public abstract class AbstractExpressionPrinter<T> {

    protected AbstractExpressionPrinter() { }

    protected T doPrintExpression(ASTExpression expression) {
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
        } else if (expression instanceof  ASTPrintExpression) {
            return this.doPrintPrintExpression((ASTPrintExpression) expression);
        } else if (expression instanceof  ASTDecrementExpression) {
            return this.doPrintDecrementExpression((ASTDecrementExpression) expression);
        } else if (expression instanceof  ASTRemainderExpressionDiff) {
            return this.doPrintRemainderExpressionDiff((ASTRemainderExpressionDiff) expression);
        }

        throw new RuntimeException( "Not implemented in PureFunExpressionPrinter: " + expression.toString());
    }

    protected abstract T doPrintRemainderExpressionDiff(ASTRemainderExpressionDiff exp);

    protected abstract T doPrintDecrementExpression(ASTDecrementExpression exp);

    protected abstract T doPrintPrintExpression(ASTPrintExpression exp);

    protected abstract T doPrintListExpression(ASTListExpression exp);

    protected abstract T doPrintMapExpression(ASTMapExpression exp);

    protected abstract T doPrintTupleExpression(ASTTupleExpression exp);

    protected abstract T doPrintAsyncExpression(ASTAsyncExpression exp);

    protected abstract T doPrintAssignmentExpression(ASTAssignmentExpression exp);

    protected abstract T doPrintBooleanAndOpExpression(ASTBooleanAndOpExpression exp);

    protected abstract T doPrintBooleanAndOpExpressionDiff(ASTBooleanAndOpExpressionDiff exp);

    protected abstract T doPrintBooleanOrOpExpression(ASTBooleanOrOpExpression exp);

    protected abstract T doPrintBooleanOrOpExpressionDiff(ASTBooleanOrOpExpressionDiff exp);

    protected abstract T doPrintLengthExpression(ASTLengthExpression exp);

    protected abstract T doPrintInExpression(ASTInExpression exp);

    protected abstract T doPrintMapValueExpression(ASTMapValueExpression exp);

    protected abstract T doPrintMapKeyExpression(ASTMapKeyExpression exp);

    protected abstract T doPrintIndexAccessExpression(ASTIndexAccessExpression exp);

    protected abstract T doPrintConcatExpression(ASTConcatExpression exp);

    protected abstract T doPrintCallExpression(ASTCallExpression exp);

    protected abstract T doPrintNameExpression(ASTNameExpression exp);

    protected abstract T doPrintPlusExpression(ASTPlusExpression exp);

    protected abstract T doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp);

    protected abstract T doPrintLogicalNotExpression(ASTLogicalNotExpression exp);

    protected abstract T doPrintBooleanNotExpression(ASTBooleanNotExpression exp);

    protected abstract T doPrintLitExpression(ASTLitExpression exp);

    protected abstract T doPrintAssignmentByRemainderExpression(ASTAssignmentByRemainderExpression exp);

    protected abstract T doPrintAssignmentByDivisionExpression(ASTAssignmentByDivisionExpression exp);

    protected abstract T doPrintAssignmentByMultiplyExpression(ASTAssignmentByMultiplyExpression exp);

    protected abstract T doPrintAssignmentByDecreaseExpression(ASTAssignmentByDecreaseExpression exp);

    protected abstract T doPrintBracketExpression(ASTBracketExpression exp);

    protected abstract T doPrintSimpleAssignmentExpression(ASTSimpleAssignmentExpression exp);

    protected abstract T doPrintConditionalExpression(ASTConditionalExpression exp);

    protected abstract T doPrintNotEqualsExpression(ASTNotEqualsExpression exp);

    protected abstract T doPrintEqualsExpression(ASTEqualsExpression exp);

    protected abstract T doPrintGreaterThanExpression(ASTGreaterThanExpression exp);

    protected abstract T doPrintLessThanExpression(ASTLessThanExpression exp);

    protected abstract T doPrintGreaterEqualExpression(ASTGreaterEqualExpression exp);

    protected abstract T doPrintLessEqualExpression(ASTLessEqualExpression exp);

    protected abstract T doPrintMinusExpression(ASTMinusExpression exp);

    protected abstract T doPrintModuloExpression(ASTModuloExpression exp);

    protected abstract T doPrintDivideExpression(ASTDivideExpression exp);

    protected abstract T doPrintMultExpression(ASTMultExpression exp);
}
