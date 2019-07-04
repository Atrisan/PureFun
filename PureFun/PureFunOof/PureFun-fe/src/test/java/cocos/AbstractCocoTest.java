package cocos;

import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class AbstractCocoTest {

    public static final String COCO_MODELS_ROOT_PATH_VALID = "./src/test/resources/cocos/Valid";
    public static final String COCO_MODELS_ROOT_PATH_INVALID = "./src/test/resources/cocos/Invalid";

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
        Log.init();
    }

    protected static ModuleSymbol parseModel(String modelPath, String modelName) {
        ModelPath path = new ModelPath(Paths.get(modelPath));
        GlobalScope symbolTable = PureFunScopeCreator.createGlobalScope(path);
        Optional<ModuleSymbol> moduleSymbol = symbolTable.resolve(modelName, ModuleSymbol.KIND);
        Assert.assertTrue(moduleSymbol.isPresent());
        Assert.assertTrue(moduleSymbol.get().getModuleNode().isPresent());
        Log.info("module loaded", "CoCoTest");

        return moduleSymbol.get();
    }
}
