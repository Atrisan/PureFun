/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTAction;
import activitydiagram._ast.ASTParameter;
import activitydiagram._ast.ASTParameters;
import activitydiagram._cocos.ActivityDiagramASTActionCoCo;
import activitydiagram._symboltable.VariableSymbol;
import de.monticore.java.symboltable.JavaFieldSymbol;
import de.monticore.java.symboltable.JavaMethodSymbol;
import de.monticore.java.symboltable.JavaTypeSymbolReference;
import de.monticore.java.symboltable.Pair;
import de.monticore.java.types.HCJavaDSLTypeResolver;
import de.monticore.java.types.JavaDSLHelper;
import de.monticore.types.TypesPrinter;
import de.monticore.types.types._ast.ASTType;
import de.se_rwth.commons.Names;
import de.se_rwth.commons.logging.Log;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Checks whether a method referenced in an activity exists
 */
//TODO test and fix
public class ReferencedMethodExists implements ActivityDiagramASTActionCoCo {
  
  private static final String ERROR_CODE = "0x0013";
  
  private static final String ERROR_MESSAGE = " Method %s not found.";
  
  @Override
  public void check(ASTAction node) {
    checkArgument(node.isPresentEnclosingScope());
    
    // no method used, nothing to check
    if (!node.isPresentMethod()) {
      return;
    }
    
    String methodName = node.getMethod().toString();
    
    Collection<JavaMethodSymbol> javaMethodSymbolList = node.getEnclosingScope().get()
        .resolveMany(methodName, JavaMethodSymbol.KIND);
    
    // if methods are found, check whether its parameters are compatible
    // to those of the method invocation
    for (JavaMethodSymbol javaMethodSymbol : javaMethodSymbolList) {
      if (!checkParameterSize(node.getParametersOpt(), javaMethodSymbol.getParameters())) {
        continue;
      }
      
      List<ASTParameter> astParams = node.getParameters().getParameterList();
      List<JavaFieldSymbol> paramSymbols = javaMethodSymbol.getParameters();
      boolean result = true;
      
      for (int i = 0; i < astParams.size(); i++) {
        ASTParameter param = astParams.get(i);
        JavaTypeSymbolReference type = paramSymbols.get(i).getType();
        
        if (!checkTypeCompability(param, type)) {
          result = false;
          break;
        }
      }
      
      if (result) {
        // a compatible method is found, we are finished at this point
        return;
      }
    }
    
    // at this point, no compatible method definiton was found, so we log an error
    if (javaMethodSymbolList.isEmpty()) {
      Log.error(String.format(ERROR_CODE + ERROR_MESSAGE, methodName),
          node.get_SourcePositionEnd());
    }
  }
  
  private boolean checkParameterSize(Optional<ASTParameters> astParams,
      List<JavaFieldSymbol> paramSymbols) {
    // either both the method invocation and the method signature have no parameters...
    if (!astParams.isPresent() && paramSymbols.isEmpty()) {
      return true;
    }
    // .. or they have the exact same number of arguments
    if (astParams.isPresent() && astParams.get().sizeParameters() == paramSymbols.size()) {
      return true;
    }
    // otherwise the invocation and the definition are not compatible
    return false;
  }
  
  private boolean checkTypeCompability(ASTParameter param, JavaTypeSymbolReference type) {
    return (param.isPresentBooleanLiteral() && JavaDSLHelper.isBooleanType(type))
        || (param.isPresentStringLiteral() && JavaDSLHelper.isString(type))
        || (param.isPresentIntLiteral() && JavaDSLHelper.isIntType(type))
        || checkTypeName(param, type);
  }
  
  private boolean checkTypeName(ASTParameter param, JavaTypeSymbolReference type) {
    if (!param.isPresentName()) {
      return false;
    }
    
    // whether a referenced variable exists should be checked in another coco
    VariableSymbol variabelSymbol = (VariableSymbol) param.getEnclosingScope().get()
        .resolve(param.getName(), VariableSymbol.KIND).get();
    
    ASTType astType =variabelSymbol.getVariableNode().get().getType();
    String symbolType = type.getName();
    
    return TypesPrinter.printType(astType).equals(symbolType);
  }
}
