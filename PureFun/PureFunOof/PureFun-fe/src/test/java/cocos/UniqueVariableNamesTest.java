/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._ast.ASTActivityDiagram;
import activitydiagram._cocos.ActivityDiagramCoCoChecker;
import de.se_rwth.commons.logging.Finding;
import de.se_rwth.commons.logging.Log;
import lang.AbstractTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static de.monticore.cocos.helper.Assert.assertErrors;
import static org.junit.Assert.assertTrue;

public class UniqueActivityNamesTest extends AbstractTest {
  
  @Before
  public void setup() {
    Log.getFindings().clear();
  }
  
  @Test
  public void testValid() {
    ASTActivityDiagram ast = parseModel("src/test/resources/cocos/uniqueactivitynames/Valid.ad");
  
    ActivityDiagramCoCoChecker checker = new ActivityDiagramCoCoChecker();
    checker.addCoCo(new UniqueActivityNames());
    
    checker.checkAll(ast);
    
    assertTrue(Log.getFindings().isEmpty());
  }
  
  @Test
  public void testInvalid() {
    ASTActivityDiagram ast = parseModel("src/test/resources/cocos/uniqueactivitynames/Invalid.ad");
  
    ActivityDiagramCoCoChecker checker = new ActivityDiagramCoCoChecker();
    checker.addCoCo(new UniqueActivityNames());
  
    checker.checkAll(ast);
  
    Collection<Finding> expectedErrors = Arrays.asList(
        Finding.error("0x0002 Element names have to be unique. 'A' is already defined.")
    );
    
    assertErrors(expectedErrors, Log.getFindings());
  }
}
