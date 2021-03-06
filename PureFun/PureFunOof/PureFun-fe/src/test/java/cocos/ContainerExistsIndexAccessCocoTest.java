package cocos;

import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun.coco.ContainerExistsIndexAccessCoCo;
import lang.AbstractTest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;
import java.util.Optional;

public class ContainerExistsIndexAccessCocoTest extends AbstractTest {

    public static final String COCO_MODELS_ROOT_PATH_VALID = "./src/test/resources/cocos/Valid";
    public static final String COCO_MODELS_ROOT_PATH_INVALID = "./src/test/resources/cocos/Invalid";

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
    }

    @ParameterizedTest
    @CsvSource(
        "ContainerExistsIndexAccess"
    )
    public void setUp(String modelName) {
        test(modelName,COCO_MODELS_ROOT_PATH_INVALID);
        test(modelName,COCO_MODELS_ROOT_PATH_VALID);
    }

    public void test(String modelName, String Path) {
        ModelPath modelPath = new ModelPath(Paths.get(Path));
        GlobalScope symbolTable = PureFunScopeCreator.createGlobalScope(modelPath);
        Optional<ModuleSymbol> moduleSymbol = symbolTable.resolve(modelName, ModuleSymbol.KIND);
        Assert.assertTrue(moduleSymbol.isPresent());
        Assert.assertTrue(moduleSymbol.get().getModuleNode().isPresent());
        ASTModule moduleNode = moduleSymbol.get().getModuleNode().get();
        Log.info("module loaded", "ContainerExistsIndexAccessCoco");

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        ContainerExistsIndexAccessCoCo variableCoco = new ContainerExistsIndexAccessCoCo();

        checker.addCoCo(variableCoco);
        checker.checkAll(moduleNode);

        if(Path.equals(COCO_MODELS_ROOT_PATH_INVALID)) {
            Assert.assertTrue(Log.getErrorCount() > 0);
        } else {
            Assert.assertFalse(Log.getErrorCount() > 0);
        }
        Log.init();
    }
}
