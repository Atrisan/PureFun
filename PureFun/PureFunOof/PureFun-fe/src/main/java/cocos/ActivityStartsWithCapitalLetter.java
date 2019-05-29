/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTActivity;
import activitydiagram._cocos.ActivityDiagramASTActivityCoCo;
import de.se_rwth.commons.logging.Log;

/**
 * Check whether all Activities start a captial letter
 */
public class ActivityStartsWithCapitalLetter implements ActivityDiagramASTActivityCoCo {
  
  private static final String ERROR_CODE = "0x0012";
  
  private static final String ERROR_MESSAGE = " Activity '%s' must start with a captial letter.";
  
  @Override
  public void check(ASTActivity node) {
    if (Character.isLowerCase(node.getName().charAt(0))) {
      Log.error(String.format(ERROR_CODE + ERROR_MESSAGE, node.getName()),
          node.get_SourcePositionStart());
    }
  }
}
