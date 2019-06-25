package de.simpleproglang.purefun.printer;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun.types.PureFunDatastructureType;
import de.simpleproglang.purefun.types.PureFunPrimitiveType;

import javax.lang.model.type.PrimitiveType;
import java.util.Iterator;

public class ExpressionTypesPrinter extends AbstractExpressionPrinter {

    public static final String UNKNOWN_TYPE = "ERROR: UNDEFINED TYPE";
    public static final String TYPE_ERROR = "TYPE ERROR";

    private static ExpressionTypesPrinter instance;

    private ExpressionTypesPrinter() {}

    @Override
    protected String doPrintDecrementExpression(ASTDecrementExpression exp) {
        return null;
    }

    private static ExpressionTypesPrinter getInstance() {
        if (instance == null) {
            instance = new ExpressionTypesPrinter();
        }
        return instance;
    }

    private static boolean isPrimitive(String type) {
        return PureFunPrimitiveType.contains(type);
    }

    public static String printExpressionType(ASTExpression exp) {
        return getInstance().doPrintExpression(exp);
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
    protected String doPrintPrintExpression(ASTPrintExpression exp) {

        boolean wellTyped = true;
        String type = UNKNOWN_TYPE;

        for(ASTPrintElement el : exp.getPrintElementList()) {
            if (el.getExpressionOpt().isPresent()) {
                if (!PureFunPrimitiveType.contains(printExpressionType(el.getExpression()))) {
                    wellTyped = false;
                    break;
                }
            }
        }

        if (wellTyped) {
            type = PureFunPrimitiveType.VOID.toString();
        }

        return type;
    }

    @Override
    protected String doPrintListExpression(ASTListExpression exp) {
        String listType = PureFunDatastructureType.ARBITRARY.toString();

        if (exp.getExpressionList().size() > 0) {
            // first element defines list type
            listType = printExpressionType(exp.getExpression(0));

            for (int i = 1; i < exp.getExpressionList().size(); i++) {
                if (!printExpressionType(exp.getExpression(i)).equals(listType)) {
                    return TYPE_ERROR;
                }
            }
        }



        return "[" + listType + "]";
    }

    @Override
    protected String doPrintMapExpression(ASTMapExpression exp) {
        String keyType = PureFunDatastructureType.ARBITRARY.toString();
        String valueType = PureFunDatastructureType.ARBITRARY.toString();

        if (exp.getMapKeyValuePairList().size() > 0) {
            // first element defines list type
            keyType = printExpressionType(exp.getMapKeyValuePair(0).getKey());
            valueType = printExpressionType(exp.getMapKeyValuePair(0).getValue());

            for (int i = 1; i < exp.getMapKeyValuePairList().size(); i++) {
                ASTMapKeyValuePair cur = exp.getMapKeyValuePair(i);

                if (!(printExpressionType(cur.getKey()).equals(keyType) && printExpressionType(cur.getValue()).equals(valueType))) {
                    return TYPE_ERROR;
                }
            }
        }

        return "[" + keyType + ", " + valueType + "]";
    }

    @Override
    protected String doPrintTupleExpression(ASTTupleExpression exp) {
        StringBuilder res = new StringBuilder();
        String sep = "";

        for (Iterator it = exp.getExpressionList().iterator(); it.hasNext(); sep = ", ") {
            res.append(sep);
            res.append(printExpressionType((ASTExpression) it.next()));
        }

        return "<" + res.toString() + ">";
    }

    @Override
    protected String doPrintAsyncExpression(ASTAsyncExpression exp) {
        return null;
    }

    @Override
    protected String doPrintAssignmentExpression(ASTAssignmentExpression exp) {
        String leftType = printExpressionType(exp.getLeft());
        String rightType = printExpressionType(exp.getRight());

        if (leftType.equals(rightType)) {
            return leftType;
        }

        return TYPE_ERROR;
    }

    @Override
    protected String doPrintBooleanAndOpExpression(ASTBooleanAndOpExpression exp) {
        String leftType = printExpressionType(exp.getLeft());
        String rightType = printExpressionType(exp.getRight());

        if (leftType.equals(PureFunPrimitiveType.BOOLEAN.toString()) && rightType.equals(PureFunPrimitiveType.BOOLEAN.toString())) {
            return PureFunPrimitiveType.BOOLEAN.toString();
        }

        return TYPE_ERROR;
    }

    @Override
    protected String doPrintBooleanAndOpExpressionDiff(ASTBooleanAndOpExpressionDiff exp) {
        String leftType = printExpressionType(exp.getLeft());
        String rightType = printExpressionType(exp.getRight());

        if (leftType.equals(PureFunPrimitiveType.BOOLEAN.toString()) && rightType.equals(PureFunPrimitiveType.BOOLEAN.toString())) {
            return PureFunPrimitiveType.BOOLEAN.toString();
        }

        return TYPE_ERROR;
    }

    @Override
    protected String doPrintBooleanOrOpExpression(ASTBooleanOrOpExpression exp) {
        String leftType = printExpressionType(exp.getLeft());
        String rightType = printExpressionType(exp.getRight());

        if (leftType.equals(PureFunPrimitiveType.BOOLEAN.toString()) && rightType.equals(PureFunPrimitiveType.BOOLEAN.toString())) {
            return PureFunPrimitiveType.BOOLEAN.toString();
        }

        return TYPE_ERROR;
    }

    @Override
    protected String doPrintBooleanOrOpExpressionDiff(ASTBooleanOrOpExpressionDiff exp) {
        String leftType = printExpressionType(exp.getLeft());
        String rightType = printExpressionType(exp.getRight());

        if (leftType.equals(PureFunPrimitiveType.BOOLEAN.toString()) && rightType.equals(PureFunPrimitiveType.BOOLEAN.toString())) {
            return PureFunPrimitiveType.BOOLEAN.toString();
        }

        return TYPE_ERROR;
    }

    @Override
    protected String doPrintLengthExpression(ASTLengthExpression exp) {
        if (printExpressionType(exp.getExpression()).startsWith("[")) {
            return PureFunPrimitiveType.INT.toString();
        }

        return TYPE_ERROR;
    }

    @Override
    protected String doPrintInExpression(ASTInExpression exp) {
        // check if right is a list
        String rightType = printExpressionType(exp.getRight());

        if (!(isList(rightType) || isMap(rightType))) {
            // right expression is not a list or map
            return TYPE_ERROR;
        }

        String rightTypeArray[] = rightType.split(", ");
        String rightSubType = rightTypeArray.length > 1 ? rightTypeArray[1] : rightTypeArray[0];
        String leftType = printExpressionType(exp.getLeft());

        if (!leftType.equals(rightSubType)) {
            return TYPE_ERROR;
        }

        return leftType;
    }

    @Override
    protected String doPrintMapValueExpression(ASTMapValueExpression exp) {
        return null;
    }

    @Override
    protected String doPrintMapKeyExpression(ASTMapKeyExpression exp) {
        return null;
    }

    @Override
    protected String doPrintIndexAccessExpression(ASTIndexAccessExpression exp) {
        return null;
    }

    @Override
    protected String doPrintConcatExpression(ASTConcatExpression exp) {
        return null;
    }

    @Override
    protected String doPrintCallExpression(ASTCallExpression exp) {
        return null;
    }

    @Override
    protected String doPrintNameExpression(ASTNameExpression exp) {
        return null;
    }

    @Override
    protected String doPrintPlusExpression(ASTPlusExpression exp) {
        return null;
    }

    @Override
    protected String doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp) {
        return null;
    }

    @Override
    protected String doPrintLogicalNotExpression(ASTLogicalNotExpression exp) {
        return null;
    }

    @Override
    protected String doPrintBooleanNotExpression(ASTBooleanNotExpression exp) {
        return null;
    }

    @Override
    protected String doPrintLitExpression(ASTLitExpression exp) {
        return null;
    }

    @Override
    protected String doPrintAssignmentByRemainderExpression(ASTAssignmentByRemainderExpression exp) {
        return null;
    }

    @Override
    protected String doPrintAssignmentByDivisionExpression(ASTAssignmentByDivisionExpression exp) {
        return null;
    }

    @Override
    protected String doPrintAssignmentByMultiplyExpression(ASTAssignmentByMultiplyExpression exp) {
        return null;
    }

    @Override
    protected String doPrintAssignmentByDecreaseExpression(ASTAssignmentByDecreaseExpression exp) {
        return null;
    }

    @Override
    protected String doPrintBracketExpression(ASTBracketExpression exp) {
        return null;
    }

    @Override
    protected String doPrintSimpleAssignmentExpression(ASTSimpleAssignmentExpression exp) {
        return null;
    }

    @Override
    protected String doPrintConditionalExpression(ASTConditionalExpression exp) {
        return null;
    }

    @Override
    protected String doPrintNotEqualsExpression(ASTNotEqualsExpression exp) {
        return null;
    }

    @Override
    protected String doPrintEqualsExpression(ASTEqualsExpression exp) {
        return null;
    }

    @Override
    protected String doPrintGreaterThanExpression(ASTGreaterThanExpression exp) {
        return null;
    }

    @Override
    protected String doPrintLessThanExpression(ASTLessThanExpression exp) {
        return null;
    }

    @Override
    protected String doPrintGreaterEqualExpression(ASTGreaterEqualExpression exp) {
        return null;
    }

    @Override
    protected String doPrintLessEqualExpression(ASTLessEqualExpression exp) {
        return null;
    }

    @Override
    protected String doPrintMinusExpression(ASTMinusExpression exp) {
        return null;
    }

    @Override
    protected String doPrintModuloExpression(ASTModuloExpression exp) {
        return null;
    }

    @Override
    protected String doPrintDivideExpression(ASTDivideExpression exp) {
        return null;
    }

    @Override
    protected String doPrintMultExpression(ASTMultExpression exp) {
        return null;
    }

    // some helper methods
    protected static boolean isList(String type) {
        String[] typeArray = type.split(", ");

        return typeArray.length == 1 && type.startsWith("[") && type.endsWith("]");
    }

    protected static boolean isMap(String type) {
        String[] typeArray = type.split(", ");
        return typeArray.length == 2 && type.startsWith("[") && type.endsWith("]");
    }

    protected static String getListSubType(String listType) {
        // TODO
        return null;
    }
}
