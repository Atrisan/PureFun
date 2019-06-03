package ad2java.generator;

import de.purefun._ast.ASTActivityDiagram;
import de.purefun._symboltable.ActivityDiagramLanguage;
import de.purefun._symboltable.ActivityDiagramSymbol;
import de.purefun._symboltable.VariableSymbol;
import de.monticore.ModelingLanguageFamily;
import de.monticore.io.paths.ModelPath;
import de.monticore.java.lang.JavaDSLLanguage;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.logging.Log;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Optional;

public class GeneratorTest {

    @Test
    public void testGenerator() {
        String model = "src/test/resources/ActivityAction.ad";
        ModelPath modelPath = new ModelPath(Paths.get(model.substring(0, model.lastIndexOf("/"))));
        String modelName = model.substring(model.lastIndexOf("/") + 1, model.lastIndexOf("."));

        final ActivityDiagramLanguage adLang = new ActivityDiagramLanguage();
        final JavaDSLLanguage javaLang = new JavaDSLLanguage();

        final ModelingLanguageFamily modelingLanguageFamily = new ModelingLanguageFamily();
        modelingLanguageFamily.addModelingLanguage(adLang);
        modelingLanguageFamily.addModelingLanguage(javaLang);

        Scope globalScope = new GlobalScope(modelPath, modelingLanguageFamily);

        Optional<ActivityDiagramSymbol> activityDiagramSymbol = globalScope.resolve(modelName,
                ActivityDiagramSymbol.KIND);
        if(!activityDiagramSymbol.isPresent()) {
            Log.error("Could not load symbol table for model '" + model + "'.");
            return;
        }

        ASTActivityDiagram ast = activityDiagramSymbol.get().getActivityDiagramNode().get();

        Optional<VariableSymbol> varSymbol = globalScope.resolve("testVar", VariableSymbol.KIND);


        ActivityGenerator generator = new ActivityGenerator();
        generator.generate(activityDiagramSymbol.get());
    }
}