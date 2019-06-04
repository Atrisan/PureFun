package parser;

import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._parser.PureFunParser;
import lang.AbstractTest;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author Pascal Siewert
 */
public class ParserTest extends AbstractTest {

    public static final String MODEL_SOURCE_PATH = "./src/test/resources/parser/";

    @ParameterizedTest
    @CsvSource({
            "Valid/Simple.pf",
            "Valid/Valid_Branches.pf",
            "Valid/Valid3.pf",
            "Valid/Valid_async.pf"
    })
    public void test(String modelStringPath) throws IOException {
        PureFunParser parser = new PureFunParser();
        final Optional<ASTModule> pureFunModule = parser.parse(MODEL_SOURCE_PATH + modelStringPath);
        assertTrue(pureFunModule.isPresent());
    }
}