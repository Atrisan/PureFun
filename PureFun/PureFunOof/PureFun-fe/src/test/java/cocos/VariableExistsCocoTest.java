package cocos;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun.coco.ImmutableGlobalVariableCoCo;
import de.simpleproglang.purefun.coco.VariableExistsCoCo;
import lang.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class VariableExistsCocoTest extends AbstractTest {

    public static final String COCO_MODELS_ROOT_PATH = "./src/test/resources/cocos/";

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
    }

    @ParameterizedTest
    @CsvSource(
        "Invalid/VariableExists.pf"
    )
    public void test(String modelPath) {
        ASTModule moduleNode = parseModel(COCO_MODELS_ROOT_PATH + modelPath);

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        VariableExistsCoCo variableCoco = new VariableExistsCoCo();

        checker.addCoCo(variableCoco);
        checker.checkAll(moduleNode);

    }
}
