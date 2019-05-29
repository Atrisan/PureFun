/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTEdge;
import activitydiagram._cocos.ActivityDiagramASTEdgeCoCo;
import activitydiagram._symboltable.ActivitySymbol;
import activitydiagram._symboltable.JunctionSymbol;
import de.se_rwth.commons.logging.Log;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Checks whether a activity referenced in an edge exists
 */
public class ReferencedActivityExists implements ActivityDiagramASTEdgeCoCo {
  
  private static final String ERROR_CODE = "0x0003";
  
  private static final String ERROR_MESSAGE = " Referenced activity or junction '%s' does not exist.";
  
  @Override
  public void check(ASTEdge node) {
    checkArgument(node.isPresentEnclosingScope());
    // for each edge, check, the activity it starts from..
    if (!node.isStart()) {
      check(node, node.getFrom());
    }
    // .. and where it ends
    if (!node.isEnd()) {
      check(node, node.getTo());
    }
  }
  
  private void check(ASTEdge node, String name) {
    // resolve the corresponding symbol and check, whether it is present
    Optional<ActivitySymbol> activitySymbol = node.getEnclosingScope().get().resolve(name, ActivitySymbol.KIND);
    Optional<JunctionSymbol> junctionSymbol = node.getEnclosingScope().get().resolve(name, JunctionSymbol.KIND);
  
    if (!activitySymbol.isPresent() && !junctionSymbol.isPresent()) {
      Log.error(String.format(ERROR_CODE + ERROR_MESSAGE, name), node.get_SourcePositionStart());
    }
  }
}
