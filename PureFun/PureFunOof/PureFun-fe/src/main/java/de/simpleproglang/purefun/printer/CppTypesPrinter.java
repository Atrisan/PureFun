package de.simpleproglang.purefun.printer;

import de.simpleproglang.purefun._ast.*;

import java.util.HashMap;

public class CppTypesPrinter {

    HashMap PF2cpp = new HashMap();

    public CppTypesPrinter(){
        PF2cpp.put("Double", "double");
        PF2cpp.put("Float64", "double");
        PF2cpp.put("Float", "float");
        PF2cpp.put("Int", "int");
        PF2cpp.put("Char", "char");
        PF2cpp.put("String", "string");
        PF2cpp.put("Boolean", "bool");
        PF2cpp.put("Int8", "int8_t");
        PF2cpp.put("Int16", "int16_t");
        PF2cpp.put("Int32", "int32_t");
        PF2cpp.put("Int64", "int64_t");
        PF2cpp.put("UInt8", "uint8_t");
        PF2cpp.put("UInt16", "uint16_t");
        PF2cpp.put("UInt32", "uint32_t");
        PF2cpp.put("UInt64", "uint64_t");
        PF2cpp.put("Long", "int64_t");
    }

    public String cppTypePrinter(ASTType type) {
        String result = "";
        if (type instanceof ASTNamedTupleType){
            result = "std::tuple<";
            for (int i = 0; i < ((ASTNamedTupleType) type).getNamedTupleList().size(); i++) {
                result += cppTypePrinter(((ASTNamedTupleType) type).getNamedTuple(i).getType());
                if (i < ((ASTNamedTupleType) type).getNamedTupleList().size() - 1){
                    result += ", ";
                }
            }
            result += ">";
        } else if (type instanceof ASTListType) {
            result = "std::vector<";
            result += cppTypePrinter(((ASTListType) type).getType());
            result += ">";
        } else if (type instanceof ASTMapType) {
            result = "std::map<";
            result += cppTypePrinter(((ASTMapType) type).getKeyType());
            result += ", ";
            result += cppTypePrinter((((ASTMapType) type).getValueType()));
            result += ">";
        } else if (type instanceof ASTTypeName) {
            if (PF2cpp.containsKey(((ASTTypeName) type).getName())){
                result = String.valueOf(PF2cpp.get(((ASTTypeName) type).getName()));
            } else {
                result = ((ASTTypeName) type).getName();
            }
        }
        return  result;
    }
}
