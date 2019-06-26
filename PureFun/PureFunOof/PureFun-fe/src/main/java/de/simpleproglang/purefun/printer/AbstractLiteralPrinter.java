package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.*;
import de.simpleproglang.purefun._ast.*;

public abstract class AbstractLiteralPrinter<T> {

    protected AbstractLiteralPrinter() { }

    protected T doPrintLiteral(ASTLiteral literal) {
        if (literal instanceof ASTBooleanLiteral) {
            return this.doPrintBooleanLiteral((ASTBooleanLiteral) literal);
        } else  if (literal instanceof ASTCharLiteral) {
            return this.doPrintCharLiteral((ASTCharLiteral) literal);
        } else  if (literal instanceof ASTStringLiteral) {
            return this.doPrintStringLiteral((ASTStringLiteral) literal);
        } else  if (literal instanceof ASTIntLiteral) {
            return this.doPrintIntLiteral((ASTIntLiteral) literal);
        } else  if (literal instanceof ASTSignedIntLiteral) {
            return this.doPrintSignedIntLiteral((ASTSignedIntLiteral) literal);
        } else  if (literal instanceof ASTLongLiteral) {
            return this.doPrintLongLiteral((ASTLongLiteral) literal);
        } else  if (literal instanceof ASTSignedLongLiteral) {
            return this.doPrintSignedLongliteral((ASTSignedLongLiteral) literal);
        } else  if (literal instanceof ASTFloatLiteral) {
            return this.doPrintFloatLiteral((ASTFloatLiteral) literal);
        } else  if (literal instanceof ASTSignedFloatLiteral) {
            return this.doPrintSignedFloatLiteral((ASTSignedFloatLiteral) literal);
        } else  if (literal instanceof ASTDoubleLiteral) {
            return this.doPrintDoubleLiteral((ASTDoubleLiteral) literal);
        } else  if (literal instanceof ASTSignedDoubleLiteral) {
            return this.doPrintSignedDoubleLiteral((ASTSignedDoubleLiteral) literal);
        }
        
        throw new RuntimeException("Not implemented in PureFunLiteralPrinter: " + literal.toString());
    }

    protected abstract T doPrintSignedDoubleLiteral(ASTSignedDoubleLiteral literal);

    protected abstract T doPrintDoubleLiteral(ASTDoubleLiteral literal);

    protected abstract T doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal);

    protected abstract T doPrintFloatLiteral(ASTFloatLiteral literal);

    protected abstract T doPrintSignedLongliteral(ASTSignedLongLiteral literal);

    protected abstract T doPrintLongLiteral(ASTLongLiteral literal);

    protected abstract T doPrintSignedIntLiteral(ASTSignedIntLiteral literal);

    protected abstract T doPrintIntLiteral(ASTIntLiteral literal);

    protected abstract T doPrintStringLiteral(ASTStringLiteral literal);

    protected abstract T doPrintCharLiteral(ASTCharLiteral literal);

    protected abstract T doPrintBooleanLiteral(ASTBooleanLiteral literal);
}
