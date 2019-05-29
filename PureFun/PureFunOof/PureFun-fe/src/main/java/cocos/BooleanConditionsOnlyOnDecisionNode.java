/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTEdge;
import activitydiagram._cocos.ActivityDiagramASTEdgeCoCo;
import activitydiagram._symboltable.JunctionSymbol;
import de.se_rwth.commons.logging.Log;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Checks whether a boolean condition is only mentioned on an decision node
 */
public class BooleanConditionsOnlyOnDecisionNode implements ActivityDiagramASTEdgeCoCo {
  
  private static final String ERROR_CODE = "0x0015";
  
  private static final String ERROR_MESSAGE = " Boolean conditions are allowed on edges leaving decision nodes only.";
  
  @Override
  public void check(ASTEdge node) {
    checkArgument(node.isPresentEnclosingScope());
    
    if (node.isPresentExpression()) {
      Optional<JunctionSymbol> symbol = node.getEnclosingScope().get().resolve(node.getFrom(), JunctionSymbol.KIND);
      checkArgument(symbol.isPresent());
      checkArgument(symbol.get().getJunctionNode().isPresent());
      
      if (!symbol.get().getJunctionNode().get().isDecision()) {
        Log.error(String.format(ERROR_CODE + ERROR_MESSAGE), node.get_SourcePositionStart());
      }
    }
  }
}
