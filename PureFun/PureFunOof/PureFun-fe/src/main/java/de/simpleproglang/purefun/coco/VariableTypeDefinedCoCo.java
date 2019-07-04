package de.simpleproglang.purefun.coco;

import de.monticore.expressions.commonexpressions._ast.ASTNameExpression;
import de.monticore.expressions.commonexpressions._cocos.CommonExpressionsASTNameExpressionCoCo;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTType;
import de.simpleproglang.purefun._ast.ASTTypeName;
import de.simpleproglang.purefun._ast.ASTVariable;
import de.simpleproglang.purefun._cocos.PureFunASTVariableCoCo;
import de.simpleproglang.purefun._symboltable.DataStructureSymbol;
import de.simpleproglang.purefun._symboltable.FunctionSymbol;
import de.simpleproglang.purefun._symboltable.VariableSymbol;
import de.simpleproglang.purefun.printer.TypesPrinter;


public class VariableTypeDefinedCoCo implements PureFunASTVariableCoCo {

    @Override
    public void check(ASTVariable node) {
        if (!isNotClassType(node)) {
            if (node.isPresentEnclosingScope()) {
                if (!node.getEnclosingScope().resolve(TypesPrinter.printType( node.getType()), DataStructureSymbol.KIND).isPresent()) {
                    Log.error("Data Structure not defined " + TypesPrinter.printType( node.getType()));
                }
            }
        }

    }

    public boolean isNotClassType (ASTVariable var) {
        ASTType type = var.getType();
        boolean erg = true;
        String[] Types = new String[17];
        Types[0] = ("Double");
        Types[1] = ("Float64");
        Types[2] = ("Float");
        Types[3] = ("Int");
        Types[4] = ("Char");
        Types[5] = ("String");
        Types[6] = ("Boolean");
        Types[7] = ("Int8");
        Types[8] = ("Int16");
        Types[9] = ("Int32");
        Types[10] = ("Int64");
        Types[11] = ("UInt8");
        Types[12] = ("UInt16");
        Types[13] = ("UInt32");
        Types[14] = ("UInt64");
        Types[15] = ("Long");
        Types[16] = ("Void");
        if (type instanceof ASTTypeName) {
            for (int i = 0; i < Types.length; i++) {
                if (((ASTTypeName) type).getName().equals(Types[i])) {
                    return true;
                }
            }
            erg = false;
        }
        return erg;
    }

}
