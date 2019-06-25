package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.*;
import de.simpleproglang.purefun._ast.*;

public abstract class AbstractLiteralPrinter {

    protected AbstractLiteralPrinter() { }

    protected String doPrintLiteral(ASTLiteral literal) {
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
        
        return "Not implemented in PureFunLiteralPrinter: " + literal.toString();
    }

    protected abstract String doPrintSignedDoubleLiteral(ASTSignedDoubleLiteral literal);

    protected abstract String doPrintDoubleLiteral(ASTDoubleLiteral literal);

    protected abstract String doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal);

    protected abstract String doPrintFloatLiteral(ASTFloatLiteral literal);

    protected abstract String doPrintSignedLongliteral(ASTSignedLongLiteral literal);

    protected abstract String doPrintLongLiteral(ASTLongLiteral literal);

    protected abstract String doPrintSignedIntLiteral(ASTSignedIntLiteral literal);

    protected abstract String doPrintIntLiteral(ASTIntLiteral literal);

    protected abstract String doPrintStringLiteral(ASTStringLiteral literal);

    protected abstract String doPrintCharLiteral(ASTCharLiteral literal);

    protected abstract String doPrintBooleanLiteral(ASTBooleanLiteral literal);
}
