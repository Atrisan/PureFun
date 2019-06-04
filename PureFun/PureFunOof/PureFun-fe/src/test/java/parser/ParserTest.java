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

    @ParameterizedTest
    @CsvSource({
            "./src/test/resources/parser/Valid/Simple.pf",
            "./src/test/resources/parser/Valid/Valid_Branches.pf",
            "./src/test/resources/parser/Valid/Valid_Branches_Expressions.pf",
            "./src/test/resources/parser/Valid/Valid_Tupeloperants.pf",
    })
    public void test(String modelStringPath) throws IOException {
        PureFunParser parser = new PureFunParser();
        final Optional<ASTModule> pureFunModule = parser.parse(modelStringPath);
        assertTrue(pureFunModule.isPresent());
    }
}