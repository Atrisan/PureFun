package activitydiagram;

import activitydiagram._ast.ASTActivityDiagram;
import activitydiagram._parser.ActivityDiagramParser;
import activitydiagram._symboltable.ActivityDiagramLanguage;
import activitydiagram._symboltable.ActivityDiagramSymbol;
import cocos.ActivityDiagramCoCos;
import de.monticore.ModelingLanguageFamily;
import de.monticore.io.paths.ModelPath;
import de.monticore.java.lang.JavaDSLLanguage;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.logging.Log;
import org.antlr.v4.runtime.RecognitionException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Main class for the Activity Diagram DSL tool.
 */
public class ActivityDiagramTool {
  
  /**
   * Use the single argument for specifying the single input automaton file.
   *
   * @param args - command line arguments
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      Log.error("Please specify only one single path to the input model.");
      return;
    }
    String model = args[0];
    ModelPath modelPath = new ModelPath(Paths.get(model.substring(0, model.lastIndexOf("/"))));
    String modelName = model.substring(model.lastIndexOf("/") + 1, model.lastIndexOf("."));
    
    final ActivityDiagramLanguage adLang = new ActivityDiagramLanguage();
    final JavaDSLLanguage javaLang = new JavaDSLLanguage();
    
    final ModelingLanguageFamily modelingLanguageFamily = new ModelingLanguageFamily();
    modelingLanguageFamily.addModelingLanguage(adLang);
    modelingLanguageFamily.addModelingLanguage(javaLang);
    
    //  Parsing the model is not needed, because the globalScope does that as soon as he
    //  loads the model
    //final ASTActivityDiagram ast = parse(model);
    //Log.info(model + " parsed successfully!", ActivityDiagramTool.class.getName());
  
    Scope globalScope = new GlobalScope(modelPath, modelingLanguageFamily);
    
    Optional<ActivityDiagramSymbol> activityDiagramSymbol = globalScope.resolve(modelName,
        ActivityDiagramSymbol.KIND);
    if(!activityDiagramSymbol.isPresent()) {
      Log.error("Could not load symbol table for model '" + model + "'.");
      return;
    }
    
    ASTActivityDiagram ast = activityDiagramSymbol.get().getActivityDiagramNode().get();
    
    runDefaultCoCos(ast);
  }
  
  
  /**
   * Parse the model contained in the specified file.
   *
   * @param model - file to parse
   * @return ast
   */
  public static ASTActivityDiagram parse(String model) {
    try {
      ActivityDiagramParser parser = new ActivityDiagramParser() ;
      Optional<ASTActivityDiagram> optAD = parser.parse(model);
      
      if (!parser.hasErrors() && optAD.isPresent()) {
        return optAD.get();
      }
      Log.error("Model could not be parsed.");
    }
    catch (RecognitionException | IOException e) {
      Log.error("Failed to parse " + model, e);
    }
    return null;
  }
  
  /**
   * Runs the default context conditions
   *
   * @param ast - ast to check
   */
  public static void runDefaultCoCos(ASTActivityDiagram ast) {
    new ActivityDiagramCoCos().getCheckerForAllCoCos().checkAll(ast);
  }
}
