package cocos;

import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun._symboltable.PureFunScopeCreator;
import de.simpleproglang.purefun.coco.ImmutableGlobalVariableCoCo;
import lang.AbstractTest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;
import java.util.Optional;

public class ImmutableGlobalVariableCocoTest extends AbstractTest {

    public static final String COCO_MODELS_ROOT_PATH = "./src/test/resources/cocos/";

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
    }

    @ParameterizedTest
    @CsvSource(
        "Invalid/ImmutableGlobalVariable.pf"
    )
    public void test(String modelPath) {
        ASTModule moduleNode = parseModel(COCO_MODELS_ROOT_PATH + modelPath);

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        ImmutableGlobalVariableCoCo returnCoco = new ImmutableGlobalVariableCoCo();

        checker.addCoCo(returnCoco);
        checker.checkAll(moduleNode);

    }
}
