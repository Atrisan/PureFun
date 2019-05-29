/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package lang;

import activitydiagram._ast.ASTActivityDiagram;
import activitydiagram._parser.ActivityDiagramParser;
import activitydiagram._symboltable.ActivityDiagramLanguage;
import de.monticore.ModelingLanguage;
import de.monticore.ModelingLanguageFamily;
import de.monticore.io.paths.ModelPath;
import de.monticore.java.lang.JavaDSLLanguage;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.ResolvingConfiguration;
import de.se_rwth.commons.logging.Log;
import org.junit.BeforeClass;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractTest {
  
  protected static final String MODEL_PATH = "src/test/models";
  
  @BeforeClass
  public static void init() {
    Log.init();
    Log.enableFailQuick(false);
  }
  
  protected ASTActivityDiagram parseModel(String modelFile) {
    Path model = Paths.get(modelFile);
    ActivityDiagramParser parser = new ActivityDiagramParser();
    Optional<ASTActivityDiagram> result;
    try {
      result = parser.parse(model.toString());
      assertFalse(parser.hasErrors());
      assertTrue(result.isPresent());
      return result.get();
    }
    catch (IOException e) {
      e.printStackTrace();
      fail("There was an exception when parsing the model " + modelFile + ": "
          + e.getMessage());
    }
    return null;
  }
  
  protected GlobalScope parseModelWithST(String modelFilePath) {
    ModelPath modelPath = new ModelPath(Paths.get(modelFilePath));
    ModelingLanguage adLang = new ActivityDiagramLanguage();
    ModelingLanguage javaLang = new JavaDSLLanguage();
    ModelingLanguageFamily language = new ModelingLanguageFamily();
    language.addModelingLanguage(adLang);
    language.addModelingLanguage(javaLang);
    return new GlobalScope(modelPath, language);
  }
}
