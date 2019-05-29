/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package cocos;

import activitydiagram._cocos.ActivityDiagramCoCoChecker;

public class ActivityDiagramCoCos {
  
  /**
   * Returns all CoCoChecker with all CoCos to check whether a model is well-defined
   *
   * @return CoCoChecker
   */
  public ActivityDiagramCoCoChecker getCheckerForAllCoCos() {
    final ActivityDiagramCoCoChecker checker = new ActivityDiagramCoCoChecker();
    checker.addCoCo(new ActivityStartsWithCapitalLetter());
    checker.addCoCo(new BooleanConditionsOnlyOnDecisionNode());
//    checker.addCoCo(new DecisionNodeMerged());
    checker.addCoCo(new JoinAndMergeSingleOutgoingNode());
    checker.addCoCo(new ForksMerged());
//    checker.addCoCo(new NoLoopingEdges());
    checker.addCoCo(new ReferencedActivityExists());
    checker.addCoCo(new ReferencedMethodExists());
    checker.addCoCo(new UniqueActivityNames());
    return checker;
  }
}
