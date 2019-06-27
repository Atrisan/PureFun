package de.simpleproglang.purefun.types;

import de.monticore.expressions.commonexpressions._ast.*;
import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.simpleproglang.purefun._ast.*;
import de.simpleproglang.purefun.printer.AbstractExpressionPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpressionTypesResolver extends AbstractExpressionPrinter<Optional<PureFunType>> {

    private static ExpressionTypesResolver instance;

    private ExpressionTypesResolver() {}

    protected static ExpressionTypesResolver getInstance() {
        if (instance == null) {
            instance = new ExpressionTypesResolver();
        }

        return instance;
    }

    public static Optional<PureFunType> resolveType(ASTExpression expression) {
        return getInstance().doPrintExpression(expression);
    }

    @Override
    protected Optional<PureFunType> doPrintConstructorExpression(ASTConstructorExpression exp) {
        return Optional.of(PureFunCommonType.CUSTOM);
    }

    @Override
    protected Optional<PureFunType> doPrintDecrementExpression(ASTDecrementExpression exp) {
        Optional<PureFunType> leftType = resolveType(exp.getLeft());

        if (leftType.isPresent() && PureFunCommonType.isNumber(leftType.get())) {
            return leftType;
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintPrintExpression(ASTPrintExpression exp) {
        if (exp.isEmptyPrintElements()) {
            return Optional.of(PureFunCommonType.VOID);
        }

        for (ASTPrintElement el : exp.getPrintElementList()) {
            if (el.isPresentExpression()) {

                Optional<PureFunType> expType = resolveType(el.getExpression());
                if (expType.isPresent() && !PureFunCommonType.isPrimitive(expType.get())) {
                    return Optional.empty();
                } else if (!expType.isPresent()) {
                    return Optional.empty();
                }
            }
        }

        return Optional.of(PureFunCommonType.VOID);
    }

    @Override
    protected Optional<PureFunType> doPrintListExpression(ASTListExpression exp) {
        if (exp.isEmptyExpressions()) {
            return Optional.of(PureFunListType.EMPTY_LIST);
        }

        Optional<PureFunType> listSubType = resolveType(exp.getExpression(0));

        if (!listSubType.isPresent()) {
            return Optional.empty();
        }

        for (ASTExpression el : exp.getExpressionList()) {
            Optional<PureFunType> elType = resolveType(el);

            if (!elType.isPresent() || !listSubType.get().equals(elType.get())) {
                return Optional.empty();
            }
        }

        return Optional.of(new PureFunListType(listSubType.get()));
    }

    @Override
    protected Optional<PureFunType> doPrintMapExpression(ASTMapExpression exp) {
        if (exp.isEmptyMapKeyValuePairs()) {
            return Optional.of(PureFunMapType.EMPTY_MAP);
        }

        // first element in map expression defines type for map
        Optional<PureFunType> keyType = resolveType(exp.getMapKeyValuePair(0).getKey());
        Optional<PureFunType> valueType = resolveType(exp.getMapKeyValuePair(0).getValue());

        if (!(keyType.isPresent() && valueType.isPresent())) {
            return Optional.empty();
        }

        for (ASTMapKeyValuePair pair : exp.getMapKeyValuePairList()) {
            Optional<PureFunType> curKeyType = resolveType(pair.getKey());
            Optional<PureFunType> curValueType = resolveType(pair.getValue());

            if (!(curKeyType.isPresent() && curValueType.isPresent())) {
                return Optional.empty();
            }

            if (!(keyType.get().equals(curKeyType.get()) && valueType.get().equals(curValueType.get()))) {
                return Optional.empty();
            }
        }

        return Optional.of(new PureFunMapType(keyType.get(), valueType.get()));
    }

    @Override
    protected Optional<PureFunType> doPrintTupleExpression(ASTTupleExpression exp) {
        if (exp.isEmptyExpressions()) {
            return Optional.of(PureFunTupleType.EMPTY_TUPLE);
        }

        List<PureFunType> subTypes = new ArrayList<>();

        for (ASTExpression el : exp.getExpressionList()) {
            Optional<PureFunType> elType = resolveType(el);

            if (!elType.isPresent()) {
                return Optional.empty();
            } else {
                subTypes.add(elType.get());
            }
        }

        return Optional.of(new PureFunTupleType(subTypes.toArray(new PureFunType[0])));
    }

    @Override
    protected Optional<PureFunType> doPrintAsyncExpression(ASTAsyncExpression exp) {
        return resolveType(exp.getName());
    }

    @Override
    protected Optional<PureFunType> doPrintAssignmentExpression(ASTAssignmentExpression exp) {
        Optional<PureFunType> leftType = resolveType(exp.getLeft());
        Optional<PureFunType> rightType = resolveType(exp.getRight());
        if (!(leftType.isPresent() && rightType.isPresent())) {
            return Optional.empty();
        }

        if (!leftType.get().equals(rightType.get())) {
            return Optional.empty();
        }

        return leftType;
    }

    @Override
    protected Optional<PureFunType> doPrintBooleanAndOpExpression(ASTBooleanAndOpExpression exp) {
        return printBooleanExpressionType(exp);
    }

    @Override
    protected Optional<PureFunType> doPrintBooleanAndOpExpressionDiff(ASTBooleanAndOpExpressionDiff exp) {
        return printBooleanExpressionType(exp);
    }

    @Override
    protected Optional<PureFunType> doPrintBooleanOrOpExpression(ASTBooleanOrOpExpression exp) {
        return printBooleanExpressionType(exp);
    }

    @Override
    protected Optional<PureFunType> doPrintBooleanOrOpExpressionDiff(ASTBooleanOrOpExpressionDiff exp) {
        return printBooleanExpressionType(exp);
    }

    /**
     * Gets the type of a LengthExpression (if exp is a map or a list)
     * @param exp LengthExpression
     * @return
     * Int Type if exp is map or list
     */
    @Override
    protected Optional<PureFunType> doPrintLengthExpression(ASTLengthExpression exp) {
        Optional<PureFunType> expType = resolveType(exp.getExpression());

        if (expType.isPresent() && (expType.get() instanceof PureFunMapType || expType.get() instanceof PureFunListType)) {
            return Optional.of(PureFunPrimitiveType.INT);
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintInExpression(ASTInExpression exp) {
        Optional<PureFunType> elExpType = resolveType(exp.getLeft());
        Optional<PureFunType> rightType = resolveType(exp.getRight());

        if (elExpType.isPresent() && rightType.isPresent()) {
            if (rightType.get() instanceof PureFunListType && ((PureFunListType) rightType.get()).getSubType().equals(elExpType.get())) {
                return Optional.of(PureFunPrimitiveType.BOOLEAN);
            } else if (rightType.get() instanceof PureFunMapType && ((PureFunMapType) rightType.get()).getValueType().equals(elExpType.get())) {
                return Optional.of(PureFunPrimitiveType.BOOLEAN);
            }
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintMapValueExpression(ASTMapValueExpression exp) {
        Optional<PureFunType> expType = resolveType(exp.getExpression());
        Optional<PureFunType> res = Optional.empty();

        if (!expType.isPresent()) {
            return res;
        }

        if (!(expType.get() instanceof PureFunMapType)) {
            return res;
        }

        res = Optional.of(new PureFunListType(((PureFunMapType) expType.get()).getValueType()));

        return res;
    }

    @Override
    protected Optional<PureFunType> doPrintMapKeyExpression(ASTMapKeyExpression exp) {
        Optional<PureFunType> expType = resolveType(exp.getExpression());
        Optional<PureFunType> res = Optional.empty();

        if (!expType.isPresent() || !(expType.get() instanceof PureFunMapType)) {
            return res;
        }

        res = Optional.of(new PureFunListType(((PureFunMapType) expType.get()).getKeyType()));

        return res;
    }

    @Override
    protected Optional<PureFunType> doPrintIndexAccessExpression(ASTIndexAccessExpression exp) {
        // check index
        Optional<PureFunType> indexType = resolveType(exp.getIndex());

        if (!indexType.isPresent()) {
            return Optional.empty();
        }

        // check if subexpression is list, map or tuple
        Optional<PureFunType> expType = resolveType(exp.getExpression());

        if (!expType.isPresent()) {
            return Optional.empty();
        }

        // if index is of type int, expType must be INT. Otherwise expType may be a map
        if (indexType.get().equals(PureFunPrimitiveType.INT)) {
            if (expType.get() instanceof PureFunListType) {
                return Optional.of(((PureFunListType) expType.get()).getSubType());
            } else if (expType.get() instanceof PureFunTupleType) {
                return Optional.of(PureFunCommonType.ARBITRARY);
            }
        } else if (expType.get() instanceof PureFunMapType && ((PureFunMapType) expType.get()).getKeyType().equals(indexType.get())) {
            return Optional.of(((PureFunMapType) expType.get()).getValueType());
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintConcatExpression(ASTConcatExpression exp) {
        Optional<PureFunType> leftType = resolveType(exp.getLeft());

        if (!leftType.isPresent()) {
            return Optional.empty();
        }

        if (!exp.isPresentRight()) {
            // leftType must be a number
            if (PureFunCommonType.isNumber(leftType.get())) {
                return leftType;
            }
        } else {
            Optional<PureFunType> rightType = resolveType(exp.getRight());
            if (rightType.isPresent() && (leftType.get() instanceof PureFunListType || leftType.get() instanceof PureFunMapType)) {
                return leftType.get().equals(rightType.get()) ? leftType : Optional.empty();
            }
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintCallExpression(ASTCallExpression exp) {
        // TODO: need symboltable here for function return types!
        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintNameExpression(ASTNameExpression exp) {
        // TODO: need symboltable here for variable types!
        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintPlusExpression(ASTPlusExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintQualifiedNameExpression(ASTQualifiedNameExpression exp) {
        // not supported yet
        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintLogicalNotExpression(ASTLogicalNotExpression exp) {
        return getTypeForBoolOperation(resolveType(exp.getExpression()));
    }

    @Override
    protected Optional<PureFunType> doPrintBooleanNotExpression(ASTBooleanNotExpression exp) {
        return getTypeForBoolOperation(resolveType(exp.getExpression()));
    }

    @Override
    protected Optional<PureFunType> doPrintLitExpression(ASTLitExpression exp) {
        return Optional.of(LiteralTypesResolver.resolveType(exp.getLiteral()));
    }

    /**
     * provides only simple type checking: checks if the left and right expressions are numbers
     * @param exp AssignmentByReamainderExpression
     * @return returns Number type if right and left expressions are numbers
     */
    @Override
    protected Optional<PureFunType> doPrintAssignmentByRemainderExpression(ASTAssignmentByRemainderExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintAssignmentByDivisionExpression(ASTAssignmentByDivisionExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintAssignmentByMultiplyExpression(ASTAssignmentByMultiplyExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintAssignmentByDecreaseExpression(ASTAssignmentByDecreaseExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintBracketExpression(ASTBracketExpression exp) {
        return resolveType(exp.getExpression());
    }

    @Override
    protected Optional<PureFunType> doPrintSimpleAssignmentExpression(ASTSimpleAssignmentExpression exp) {
        Optional<PureFunType> leftType = resolveType(exp.getLeft());
        Optional<PureFunType> rightType = resolveType(exp.getRight());

        if (!leftType.isPresent() || !rightType.isPresent()) {
            return Optional.empty();
        }

        if (leftType.get().equals(rightType.get())) {
            return leftType;
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintConditionalExpression(ASTConditionalExpression exp) {
        if (!getTypeForBoolOperation(resolveType(exp.getCondition())).isPresent()) {
            return Optional.empty();
        }

        Optional<PureFunType> trueExpType = resolveType(exp.getTrueExpression());

        if (!trueExpType.isPresent()) {
            return Optional.empty();
        }

        Optional<PureFunType> falseExpType = resolveType(exp.getFalseExpression());

        if (!falseExpType.isPresent()) {
            return Optional.empty();
        }

        if (trueExpType.get().equals(falseExpType.get())) {
            return trueExpType;
        }

        return Optional.empty();
    }

    @Override
    protected Optional<PureFunType> doPrintNotEqualsExpression(ASTNotEqualsExpression exp) {
        return getTypeForEqualExpression(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintEqualsExpression(ASTEqualsExpression exp) {
        return getTypeForEqualExpression(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintGreaterThanExpression(ASTGreaterThanExpression exp) {
        return getInequalityExpType(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintLessThanExpression(ASTLessThanExpression exp) {
        return getInequalityExpType(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintGreaterEqualExpression(ASTGreaterEqualExpression exp) {
        return getInequalityExpType(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintLessEqualExpression(ASTLessEqualExpression exp) {
        return getInequalityExpType(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintMinusExpression(ASTMinusExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintModuloExpression(ASTModuloExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintDivideExpression(ASTDivideExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintMultExpression(ASTMultExpression exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    @Override
    protected Optional<PureFunType> doPrintRemainderExpressionDiff(ASTRemainderExpressionDiff exp) {
        return getTypeForNumberOperation(resolveType(exp.getLeft()), resolveType(exp.getRight()));
    }

    private static Optional<PureFunType> printBooleanExpressionType(ASTInfixExpression exp) {
        Optional<PureFunType> leftType = resolveType(exp.getLeft());

        if (!leftType.isPresent()) {
            return Optional.empty();
        }

        if (!PureFunCommonType.isBoolean(leftType.get())) {
            return Optional.empty();
        }

        Optional<PureFunType> rightType = resolveType(exp.getRight());

        if (!rightType.isPresent()) {
            return Optional.empty();
        }

        if (!PureFunCommonType.isBoolean(rightType.get())) {
            return Optional.empty();
        }

        return Optional.of(PureFunPrimitiveType.BOOLEAN);
    }

    @SafeVarargs
    private static Optional<PureFunType> getTypeForBoolOperation(Optional<PureFunType>... types) {
        for (Optional<PureFunType> type : types) {
            if (!(type.isPresent() && type.get().equals(PureFunPrimitiveType.BOOLEAN))) {
                return Optional.empty();
            }
        }

        return Optional.of(PureFunPrimitiveType.BOOLEAN);
    }

    @SafeVarargs
    private static Optional<PureFunType> getTypeForNumberOperation(Optional<PureFunType>... types) {
        for (Optional<PureFunType> type : types) {
            if (!(type.isPresent() && PureFunCommonType.isNumber(type.get()))) {
                return Optional.empty();
            }
        }

        return Optional.of(PureFunPrimitiveType.INT);
    }

    @SafeVarargs
    private static Optional<PureFunType> getTypeForEqualExpression(Optional<PureFunType>... types) {
        if (types.length == 0) {
            return Optional.empty();
        }

        Optional<PureFunType> refType = types[0];
        if (!refType.isPresent()) {
            return Optional.empty();
        }

        for (int i = 1; i < types.length; i++) {
            Optional<PureFunType> curType = types[i];
            if (!(curType.isPresent() && refType.get().equals(curType.get()))) {
                return Optional.empty();
            }
        }

        return Optional.of(PureFunPrimitiveType.BOOLEAN);
    }

    @SafeVarargs
    private static Optional<PureFunType> getInequalityExpType(Optional<PureFunType>... types) {
        if (types.length == 0) {
            return Optional.empty();
        }

        for (Optional<PureFunType> type : types) {
            if (!(type.isPresent() && PureFunCommonType.isNumber(type.get()))) {
                return Optional.empty();
            }
        }

        return Optional.of(PureFunPrimitiveType.BOOLEAN);
    }
}
