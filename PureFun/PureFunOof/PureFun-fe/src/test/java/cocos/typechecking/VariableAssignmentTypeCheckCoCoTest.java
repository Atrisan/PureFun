package cocos.typechecking;

import cocos.AbstractCocoTest;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun._symboltable.ModuleSymbol;
import de.simpleproglang.purefun.coco.typechecking.AssignmentExpressionTypeCheckCoCo;
import de.simpleproglang.purefun.coco.typechecking.VariableAssignmentTypeCheckCoCo;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class VariableAssignmentTypeCheckCoCoTest extends AbstractCocoTest {

    public static void check(String modelPath, String modelName) {
        ModuleSymbol moduleSymbol = parseModel(modelPath, modelName);

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        VariableAssignmentTypeCheckCoCo assignmentTypeCheckCoco = new VariableAssignmentTypeCheckCoCo(moduleSymbol);

        checker.addCoCo(assignmentTypeCheckCoco);
        checker.checkAll(moduleSymbol.getModuleNode().get());
    }

    @ParameterizedTest
    @CsvSource(
            "VariableAssignmentTypeCheck"
    )
    public void testValid(String modelName) {
        check(COCO_MODELS_ROOT_PATH_VALID, modelName);
    }

    @ParameterizedTest
    @CsvSource(
            "VariableAssignmentTypeCheck"
    )
    public void testInvalid(String modelName) {
        check(COCO_MODELS_ROOT_PATH_INVALID, modelName);
    }
}
