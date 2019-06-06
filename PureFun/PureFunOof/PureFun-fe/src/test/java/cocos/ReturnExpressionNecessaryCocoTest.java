package cocos;

import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._cocos.PureFunCoCoChecker;
import de.simpleproglang.purefun.coco.ReturnExpressionNecessaryCoCo;
import lang.AbstractTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReturnExpressionNecessaryCocoTest extends AbstractTest {

    public static final String COCO_MODELS_ROOT_PATH = "./src/test/resources/cocos/";

    @ParameterizedTest
    @CsvSource(
        "Valid/ReturnExpressionNecessary.pf"
    )
    public void test(String modelPath) {
        ASTModule moduleNode = parseModel(COCO_MODELS_ROOT_PATH + modelPath);

        PureFunCoCoChecker checker = new PureFunCoCoChecker();
        ReturnExpressionNecessaryCoCo returnCoco = new ReturnExpressionNecessaryCoCo();

        checker.addCoCo(returnCoco);
        checker.checkAll(moduleNode);

    }
}
