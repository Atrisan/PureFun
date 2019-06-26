package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.monticore.literals.literals._ast.*;
import de.simpleproglang.purefun._ast.*;

import java.util.Iterator;

public class CppLiteralPrinter extends AbstractLiteralPrinter<String> {

    private static CppLiteralPrinter printer;

    protected CppLiteralPrinter() { }

    protected static AbstractLiteralPrinter<String> getInstance() {
        if (printer == null) {
            printer = new CppLiteralPrinter();
        }

        return printer;
    }

    public static String printLiteral(ASTLiteral literal) {
        return CppLiteralPrinter.getInstance().doPrintLiteral(literal);
    }


    @Override
    protected String doPrintSignedDoubleLiteral(ASTSignedDoubleLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintDoubleLiteral(ASTDoubleLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintSignedFloatLiteral(ASTSignedFloatLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintFloatLiteral(ASTFloatLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintSignedLongliteral(ASTSignedLongLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintLongLiteral(ASTLongLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintSignedIntLiteral(ASTSignedIntLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintIntLiteral(ASTIntLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }

    @Override
    protected String doPrintStringLiteral(ASTStringLiteral literal) {
        String erg = "\"" + literal.getValue() + "\"";
        return erg;
    }

    @Override
    protected String doPrintCharLiteral(ASTCharLiteral literal) {
        String erg = "\'" + literal.getValue() + "\'";
        return erg;
    }

    @Override
    protected String doPrintBooleanLiteral(ASTBooleanLiteral literal) {
        String erg = "" + literal.getValue();
        return erg;
    }



}
