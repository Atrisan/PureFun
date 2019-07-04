package cocos.typechecking;

import cocos.AbstractCocoTest;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun.coco.typechecking.AssignmentExpressionTypeCheckCoCo;
import de.simpleproglang.purefun.coco.typechecking.IfTypeCheckCoCo;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AssignmentTypeCheckCoCoTest extends AbstractCocoTest {

    public static void check(String modelPath, String modelName) {
        ASTModule module = parseModel(modelPath, modelName).getModuleNode().get();

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        AssignmentExpressionTypeCheckCoCo assignmentTypeCheckCoco = new AssignmentExpressionTypeCheckCoCo();

        checker.addCoCo(assignmentTypeCheckCoco);
        checker.checkAll(module);
    }

    @ParameterizedTest
    @CsvSource(
            "AssignmentTypeCheck"
    )
    public void testValid(String modelName) {
        check(COCO_MODELS_ROOT_PATH_VALID, modelName);
    }

    @ParameterizedTest
    @CsvSource(
            "AssignmentTypeCheck"
    )
    public void testInvalid(String modelName) {
        check(COCO_MODELS_ROOT_PATH_INVALID, modelName);
        Assert.assertTrue(Log.getErrorCount() > 0);
    }
}
