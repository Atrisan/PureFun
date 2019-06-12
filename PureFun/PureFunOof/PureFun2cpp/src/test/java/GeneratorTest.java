
import de.simpleproglang.purefun._ast.ASTModule;
import org.junit.Test;

public class GeneratorTest extends AbstractTest {
    public static final String MODEL_SOURCE_PATH = "./src/test/resources/generator/";

    @Test
    public void testGeneratorModuleWithDataStructures() {
        ASTModule ast = this.parseModel(MODEL_SOURCE_PATH + "module.pf");
        PureFunGenerator gen = new PureFunGenerator();
        gen.generate(ast, "ModuleWithDestructure.cxx");
    }
}