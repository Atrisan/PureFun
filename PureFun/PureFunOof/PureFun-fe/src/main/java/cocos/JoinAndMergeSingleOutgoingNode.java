/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTActivityDiagram;
import activitydiagram._ast.ASTEdge;
import activitydiagram._cocos.ActivityDiagramASTActivityDiagramCoCo;
import activitydiagram._symboltable.ActivityDiagramSymbol;
import activitydiagram._symboltable.JunctionSymbol;
import de.se_rwth.commons.logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Checks whether a join/merge node holds at most one outgoing node
 */
public class JoinAndMergeSingleOutgoingNode implements ActivityDiagramASTActivityDiagramCoCo {
  
  private static final String ERROR_CODE = "0x0014";
  
  private static final String ERROR_MESSAGE = " Junction '%s' must yield a single outgoing node.";
  
  @Override
  public void check(ASTActivityDiagram node) {
    checkArgument(node.isPresentEnclosingScope());
    // get the symbol for the whole activity diagram
    Optional<ActivityDiagramSymbol> activityDiagramSymbol = node.getEnclosingScope().get()
        .resolve(node.getName(),
            ActivityDiagramSymbol.KIND);
    checkArgument(activityDiagramSymbol.isPresent());
    
    // initialize a map with all Join and Merge Nodes and a counter for all outgoing nodes
    Map<String, Integer> map = new HashMap<>();
    for (JunctionSymbol symbol : activityDiagramSymbol.get().getJoins()) {
      map.put(symbol.getName(), 0);
    }
    for (JunctionSymbol symbol : activityDiagramSymbol.get().getMerges()) {
      map.put(symbol.getName(), 0);
    }
    
    for (ASTEdge edge : node.getEdgeList()) {
      if(edge.isStart()) {
        continue;
      }
      String name = edge.getFrom();
      // if the map contains the key, and its counter is below 1, everything is fine
      // otherwise, a join/merge with multiple outgoing nodes is defined, which is not allowed
      if (map.containsKey(name)) {
        if (map.get(name) < 1) {
          map.put(name, map.get(name) + 1);
        }
        else {
          Log.error(String.format(ERROR_CODE + ERROR_MESSAGE, name), edge.get_SourcePositionStart());
        }
      }
    }
  }
}
