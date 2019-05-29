/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import activitydiagram._ast.ASTActivityDiagram;
import activitydiagram._ast.ASTEdge;
import activitydiagram._ast.ASTJunction;
import activitydiagram._cocos.ActivityDiagramASTActivityDiagramCoCo;
import de.se_rwth.commons.logging.Log;

/**
 * Checks whether all fork nodes are merged at some point
 */
public class ForksMerged implements ActivityDiagramASTActivityDiagramCoCo {
  
  private static final String ERROR_CODE = "0x0004";
  
  private static final String ERROR_MESSAGE = " Not all fork Nodes are joint";
  
  /**
   * @see activitydiagram._cocos.ActivityDiagramASTActivityDiagramCoCo#check(activitydiagram._ast.ASTActivityDiagram)
   */
  @Override
  public void check(ASTActivityDiagram node) {
    Set<String> forkJoin = new HashSet<>();
    int out = 0;
    int in = 0;
    List<ASTJunction> junctions = node.streamElements().filter(f -> f instanceof ASTJunction)
        .map(f -> (ASTJunction) f).collect(Collectors.toList());
    for (ASTJunction ast : junctions) {
      if (ast.isJoin() || ast.isFork()) {
        forkJoin.add(ast.getName());
      }
    }
    for (ASTEdge action : node.getEdgeList()) {
      
      if (action.isPresentFrom() && forkJoin.contains(action.getFrom())) {
        out++;
      }
      
      if (action.isPresentTo() && forkJoin.contains(action.getTo())) {
        in++;
      }
    }
    if (in != out) {
      Log.error(ERROR_CODE + "in Digramm "+  node.getName() +ERROR_MESSAGE);
    }
    
  }
  
}
