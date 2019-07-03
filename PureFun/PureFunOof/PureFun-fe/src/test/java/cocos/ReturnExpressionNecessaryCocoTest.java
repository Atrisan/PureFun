package cocos;

import de.monticore.io.paths.ModelPath;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun.coco.ReturnExpressionNecessaryCoCo;
import lang.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;

public class ReturnExpressionNecessaryCocoTest extends AbstractTest {

    public static final String COCO_MODELS_ROOT_PATH_VALID = "./src/test/resources/cocos/Valid";
    public static final String COCO_MODELS_ROOT_PATH_INVALID = "./src/test/resources/cocos/Invalid";

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
    }

    @ParameterizedTest
    @CsvSource(
        "ReturnExpressionNecessary.pf"
    )
    public void setUp(String modelName) {
        test(modelName,COCO_MODELS_ROOT_PATH_INVALID);
        test(modelName,COCO_MODELS_ROOT_PATH_VALID);
    }

    public void test(String modelName, String Path) {
        ASTModule moduleNode = parseModel(Path + modelName);

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        ReturnExpressionNecessaryCoCo returnCoco = new ReturnExpressionNecessaryCoCo();

        checker.addCoCo(returnCoco);
        checker.checkAll(moduleNode);

    }
}
