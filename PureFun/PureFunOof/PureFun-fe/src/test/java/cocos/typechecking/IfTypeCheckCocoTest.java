package cocos.typechecking;

import cocos.AbstractCocoTest;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun.coco.typechecking.IfTypeCheckCoCo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IfTypeCheckCocoTest extends AbstractCocoTest {

    public static void check(String modelPath, String modelName) {
        ASTModule module = parseModel(modelPath, modelName);

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        IfTypeCheckCoCo ifTypeCheckCoCo = new IfTypeCheckCoCo();

        checker.addCoCo(ifTypeCheckCoCo);
        checker.checkAll(module);
    }

    @ParameterizedTest
    @CsvSource(
            "IfTypeCheck"
    )
    public void testValid(String modelName) {
        check(COCO_MODELS_ROOT_PATH_VALID, modelName);
    }

    @ParameterizedTest
    @CsvSource(
            "IfTypeCheck"
    )
    public void testInvalid(String modelName) {
        check(COCO_MODELS_ROOT_PATH_INVALID, modelName);
    }
}
