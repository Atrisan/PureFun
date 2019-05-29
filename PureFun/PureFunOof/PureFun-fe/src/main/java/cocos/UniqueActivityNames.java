/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTActivityDiagram;
import activitydiagram._ast.ASTActivity;
import activitydiagram._ast.ASTElement;
import activitydiagram._cocos.ActivityDiagramASTActivityDiagramCoCo;
import de.se_rwth.commons.logging.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Checks whether all activities have unique names
 */
public class UniqueActivityNames implements ActivityDiagramASTActivityDiagramCoCo {
  
  private static final String ERROR_CODE = "0x0002";
  
  private static final String ERROR_MESSAGE = " Element names have to be unique. '%s' is already defined.";
  
  @Override
  public void check(ASTActivityDiagram node) {
    Set<String> names = new HashSet<>();
    for (ASTElement element : node.getElementList()) {
      if(names.contains(element.getName())) {
        Log.error(String.format(ERROR_CODE + ERROR_MESSAGE, element.getName()),
            element.get_SourcePositionStart());
      }
      else {
        names.add(element.getName());
      }
    }
  }
}
