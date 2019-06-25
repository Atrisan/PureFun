package printer;

import de.monticore.expressions.expressionsbasis._ast.ASTExpression;
import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTBlockElement;
import de.simpleproglang.purefun._ast.ASTDefinition;
import de.simpleproglang.purefun._ast.ASTFunction;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun.printer.PureFunExpressionPrinter;
import lang.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PureFunExpressionPrinterTest extends AbstractTest {

    private static final String MODEL_ROOT_PATH = "./src/test/resources/parser/";

    @Before
    public void initTest() {
        System.out.println("Begin PureFunExpressionPrinter Test:");
    }

    @ParameterizedTest
    @CsvSource({
            "Valid/Async.pf",
            "Valid/Branches.pf",
            "Valid/BranchesExpressions.pf",
            "Valid/DataStructures.pf",
            "Valid/FunctionDeclarations.pf",
            "Valid/GlobalVariables.pf",
            "Valid/ListOperands.pf",
            "Valid/Loops.pf",
            "Valid/MapOperands.pf",
            "Valid/Simple.pf",
            "Valid/TupleOperands.pf"
    })
    public void testExpressionPrinter(String modelPath) {
        ASTModule module = parseModel(MODEL_ROOT_PATH + modelPath);

        Assert.assertTrue(module.getDefinitionList().size() > 0);
        for (ASTDefinition definition : module.getDefinitionList()) {
            if (definition instanceof ASTFunction) {
                Assert.assertTrue(((ASTFunction) definition).getBlockStatement().getBlockElementList().size() > 0);
                for (ASTBlockElement blockElement : ((ASTFunction) definition).getBlockStatement().getBlockElementList()) {

                    if (blockElement.getExpressionOpt().isPresent() && blockElement.getExpression() instanceof ASTExpression) {
                        ASTExpression expression = (ASTExpression) blockElement.getExpression();
                        System.out.println("Expression on line " + blockElement.get_SourcePositionStartOpt().get().getLine() + ": " + PureFunExpressionPrinter.printExpression(expression));
                        Assert.assertTrue(PureFunExpressionPrinter.printExpression(expression).length() > 0);
                    }
                }
            }
        }
    }
}
