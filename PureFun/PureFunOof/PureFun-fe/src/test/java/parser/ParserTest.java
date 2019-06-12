package parser;

import de.se_rwth.commons.logging.Log;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._parser.PureFunParser;
import lang.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Pascal Siewert
 */
public class ParserTest extends AbstractTest {

    public static final String MODEL_SOURCE_PATH = "./src/test/resources/parser/";

    @BeforeAll
    public static void disableFailQuick() {
        Log.enableFailQuick(false);
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
    public void testValid(String modelStringPath) {
        PureFunParser parser = new PureFunParser();
        parseModel(MODEL_SOURCE_PATH + modelStringPath);
    }

    @ParameterizedTest
    @CsvSource({
            "Invalid/async_before_line.pf",
            "Invalid/Branch_no_expression.pf",
            "Invalid/Branch_single_else.pf",
            "Invalid/Data_Structures_no_data.pf",
            "Invalid/Data_Structures_no_type.pf",
            "Invalid/Function_no_bracket.pf",
            "Invalid/Function_no_dots.pf",
            "Invalid/Function_no_fun.pf",
            "Invalid/Function_no_name.pf",
            "Invalid/Function_no_return_value.pf",
            "Invalid/Global_Variables_multiple_no_type.pf",
            "Invalid/Global_Variables_multiple_values.pf",
            "Invalid/Global_Variables_no_eq.pf",
            "Invalid/InvalidFunctionType.pf"
    })
    public void testInvalid(String modelStringPath) throws IOException {
        // impossible to detect error?
        PureFunParser parser = new PureFunParser();
        Optional<ASTModule> result = parser.parse(MODEL_SOURCE_PATH + modelStringPath);
        assertTrue(parser.hasErrors());
    }
}